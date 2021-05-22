package com.pedgog.utilities;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class JiraOperations {
	public static Registry<ConnectionSocketFactory> getSSLConnection() throws Exception {
		SSLContextBuilder builder = SSLContexts.custom();
		builder.loadTrustMaterial(null, new TrustStrategy() {
			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				return true;
			}
		});
		SSLContext sslContext = builder.build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("https", sslsf).build();
		return socketFactoryRegistry;
	}

	public static CloseableHttpClient getHttpClient(HttpHost target,
			Registry<ConnectionSocketFactory> socketFactoryRegistry) {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(target.getHostName(), target.getPort()),
				new UsernamePasswordCredentials("muthuselvam.kandhasamy@bluescape.com", "Royal@123"));
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider)
				.setConnectionManager(cm).build();
		return httpclient;
	}

	public static HttpClientContext getClientContext(HttpHost target) {
		AuthCache authCache = new BasicAuthCache();
		BasicScheme basicAuth = new BasicScheme();
		authCache.put(target, basicAuth);
		HttpClientContext localContext = HttpClientContext.create();
		localContext.setAuthCache(authCache);
		return localContext;
	}

	public static HttpHost getJiraHost() {
		return new HttpHost("jira.common.bluescape.com", 443, "https");
	}

	public static void updateTestInJira(String testPlanKey, String cycleName, String testCaseKey, String result)
			throws Exception {
		HttpHost target = getJiraHost();
		CloseableHttpClient httpclient = getHttpClient(target, JiraOperations.getSSLConnection());
		HttpClientContext localContext = getClientContext(target);
		try {
			JSONObject json = new JSONObject();
			json.put("testcaseKey", testCaseKey);
			json.put("result", result);
			json.put("comment", "Updated through REST");
			HttpPost httppost = new HttpPost("https://jira.common.bluescape.com/rest/synapse/latest/public/testPlan/"
					+ testPlanKey + "/cycle/" + cycleName + "/updateTestRun");
			StringEntity params = new StringEntity(json.toString());
			httppost.addHeader("content-type", "application/json");
			httppost.setEntity(params);
			CloseableHttpResponse response = httpclient.execute(target, httppost, localContext);
			try {
				System.out.println(response.getStatusLine());
				System.out.println("Test case result update : "+EntityUtils.toString(response.getEntity()));
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
	}
}
