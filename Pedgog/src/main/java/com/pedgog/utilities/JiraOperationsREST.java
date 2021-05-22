
package com.pedgog.utilities;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

public class JiraOperationsREST {
	private static JSONObject response;

	public static void updateJira(String testPlanId, String cycleName, String testCaseId, String result) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		String auth = "muthuselvam.kandhasamy@bluescape.com" + ":" + "Royal@123";
		byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
		HttpHeaders requestAuthHeaders = new HttpHeaders();
		requestAuthHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestAuthHeaders.set("Authorization", "Basic " + new String(encodedAuth));
		Map<String, String> requestBody = new HashMap<String, String>();
		requestBody.put("testcaseKey", testCaseId);
		requestBody.put("result", result);
		requestBody.put("comment", "Rest");
		HttpEntity<Map<String, String>> requestEntity = new HttpEntity<Map<String, String>>(requestBody,
				requestAuthHeaders);
		try {
			ResponseEntity<String> jiraResultUpdate = restTemplate
					.exchange(
							"https://jira.common.bluescape.com/rest/synapse/latest/public/testPlan/" + testPlanId
									+ "/cycle/" + cycleName + "/updateTestRun",
							HttpMethod.POST, requestEntity, String.class);
		} catch (HttpClientErrorException e) {
			System.err.println("jira status update failed with error code : " + e.getStatusCode());
			response = new JSONObject(e.getMessage());
			System.err.println("error message : " + response.toString());
		} catch (HttpServerErrorException e) {
			System.err.println("jira status update failed with error code : " + e.getStatusCode());
			response = new JSONObject(e.getMessage());
			System.err.println("error message : " + response.toString());
		}
	}
}
