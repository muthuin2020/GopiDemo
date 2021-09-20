package com.pedgog.web.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.analytics.pedgog.web.pages.AnalyticsHomePage;
import com.analytics.pedgog.web.pages.AnalyticsLoginPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.coaching.myApp.web.pages.CoachingAppHomePage;
import com.coaching.myApp.web.pages.CoachingAppLoginPage;
import com.coaching.pedgog.web.pages.PedgogHomePage;
import com.coaching.pedgog.web.pages.PedgogLoginPage;
import com.coaching.pedgog.web.pages.PedgogRegisterPage;
import com.pedgog.utilities.JiraOperationsREST;

public class TestBase {
	public static WebDriver driver, driverTwo;
	public static boolean isLoggedIn = false, isLoggedInToAnalytics = false, analyticsTest, myAppTesting;
	public static String userName, userPassword, homePageTitle, testMethodName, currentPage, pedgogURL, analyticsURL, coachingAppUserName, coachingAppUserPassword, coachingAppHomePageTitle, coachingAppURL;
	public static String analyticsLoginEmail, analyticsLoginPassword, myAppLoginEmail, myAppLoginPassword, myAppURL;
	public static String otpLink, otpNumber;
	public static Properties prop;
	PedgogLoginPage loginPage;
	CoachingAppHomePage coachingAppHomePage;
	CoachingAppLoginPage coachingAppLoginPage;
	PedgogRegisterPage registerPage;
	PedgogHomePage homePage;
	AnalyticsLoginPage analyticsLoginPage;
	AnalyticsHomePage analyticsHomePage;
	ITestResult result;
	public static SoftAssert sAssert;
	public static ExtentHtmlReporter reporter;
	public static ExtentReports extent;
	public static ExtentTest logger;

	@BeforeSuite
	protected void setDrivers() throws InterruptedException {
		setLoginData();
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
		Map<String, Object> pref = new HashMap<String, Object>();
		pref.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
		options.addArguments("–disable-notifications");
		options.setExperimentalOption("prefs", pref);
		driver = new ChromeDriver(options);

//		enable the below line to run in firefox browser
//		 System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +
//		 "\\drivers\\geckodriver.exe");
//		driver = new FirefoxDriver();

		System.out.println("Browser is launched...");
		driver.manage().window().maximize();
		driver.get(pedgogURL);
		System.out.println("Launched Pedgog website : " + pedgogURL);
		Thread.sleep(2000);
		System.out.println("Page title is : " + driver.getTitle());

		if (analyticsTest) {
			driverTwo = new ChromeDriver(options);
			driverTwo.manage().window().maximize();
		}
		
		if (myAppTesting) {
			driverTwo = new ChromeDriver(options);
			driverTwo.manage().window().maximize();
			driverTwo.get(myAppURL);
		}

		reporter = new ExtentHtmlReporter("./reports/learn_automation1.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		logger = extent.createTest("Starting logger...");
	}

	@BeforeMethod
	protected void nameBefore(Method method) {
		System.out.println("========================================================================================");
		testMethodName = method.getName();
		logger = extent.createTest(testMethodName);
		logger.log(Status.INFO, "starting test method " + testMethodName);
		System.out.println("Starting Test Method : " + testMethodName);
		sAssert = new SoftAssert();
	}

	public void registerToPedgog(String name, String emailId, String password, String accessCode) {
		registerPage = new PedgogRegisterPage(driver);
		loginPage = new PedgogLoginPage(driver);
		loginPage.clickRegister();
		registerPage.enterUserName(name);
		registerPage.enterUserEmail(emailId);
		registerPage.enterPassword(password);
		registerPage.enterAccessCode(accessCode);
		registerPage.clickRegister();
	}

	public PedgogHomePage loginToPedgog() {
		loginPage = new PedgogLoginPage(driver);
		homePage = new PedgogHomePage(driver);
		driver.get(pedgogURL);
		System.out.println("Logging into : " + pedgogURL);
		loginPage.enterUserEmail(userName);
		loginPage.enterPassword(userPassword);
		loginPage.clickLogin();
		if (homePage.getHomePageTtile().equals(homePageTitle)) {
			isLoggedIn = true;
			System.out.println("Logged in to Pedgog");
		} else
			System.err.println(
					"------------------------------ Login to Pedgog failed -----------------------------------");
		return PageFactory.initElements(driver, PedgogHomePage.class);
	}

	public CoachingAppHomePage loginToCoachingApp() {
		coachingAppLoginPage = new CoachingAppLoginPage(driver);
		coachingAppHomePage = new CoachingAppHomePage(driver);
		driver.get(coachingAppURL);
		System.out.println("Logging into : " + coachingAppURL);
		coachingAppLoginPage.enterUserEmail(coachingAppUserName);
		coachingAppLoginPage.enterPassword(coachingAppUserPassword);
		coachingAppLoginPage.clickLogin();
		if (coachingAppHomePage.getHomePageTtile().equals(coachingAppHomePageTitle)) {
			isLoggedIn = true;
			System.out.println("Logged in to Pedgog");
		} else
			System.err.println(
					"------------------------------ Login to Pedgog failed -----------------------------------");
		return PageFactory.initElements(driver, CoachingAppHomePage.class);
	}

	public AnalyticsHomePage loginToAnalytics() {
//		String uName = "sagar@gamil.com";
//		String pWord = "Sagar@123";
		String PageTitle = "React Apa";
		analyticsLoginPage = new AnalyticsLoginPage(driverTwo);
		driverTwo.get(analyticsURL);
		System.out.println("Logging into : " + analyticsURL);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driverTwo.findElement(By.xpath("//input[@name=\"email\"]")).sendKeys(analyticsLoginEmail);
		// analyticsLoginPage.enterUserEmail("sagar@gamil.com");
		analyticsLoginPage.enterPassword(analyticsLoginPassword);
		analyticsLoginPage.clickLogin();
		System.out.println("Page title is : " + driverTwo.getTitle());
		if (driverTwo.getTitle().equals(PageTitle)) {
			isLoggedInToAnalytics = true;
			System.out.println("Logged in to Pedgog");
		} else
			System.err.println(
					"------------------------------ Login to Pedgog failed -----------------------------------");

		return PageFactory.initElements(driverTwo, AnalyticsHomePage.class);

	}
	
	public void setLoginData() {
		String propertyFilePath = System.getProperty("user.dir") + "\\config\\config.properties";
		Properties prop = new Properties();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));

			try {
				prop.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		userName = prop.getProperty("emailId");
		userPassword = prop.getProperty("pwd");
		homePageTitle = prop.getProperty("homePageTitle");
		pedgogURL = prop.getProperty("pedgogURL");
		
		coachingAppUserName = prop.getProperty("coachingAppEmailId");
		coachingAppUserPassword = prop.getProperty("coachingAppPassword");
		coachingAppHomePageTitle = prop.getProperty("coachingAppHomePageTitle");
		coachingAppURL = prop.getProperty("coachingAppURL");
		
		analyticsTest = Boolean.parseBoolean(prop.getProperty("analyticsTest"));
		if (analyticsTest) {
			analyticsLoginEmail = prop.getProperty("analyticsLoginEmail");
			analyticsLoginPassword = prop.getProperty("analyticsLoginPassword");
			analyticsURL = prop.getProperty("analyticsURL");
		}

		myAppTesting = Boolean.parseBoolean(prop.getProperty("myAppTesting"));
		if (myAppTesting) {
			myAppLoginEmail = prop.getProperty("myAppLoginEmail");
			myAppLoginPassword = prop.getProperty("myAppLoginPassword");
			myAppURL = prop.getProperty("myAppURL");
		}
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		switch (result.getStatus()) {
		case ITestResult.SUCCESS:
			TestResult.testPassed(result, testMethodName);
			break;

		case ITestResult.FAILURE:
			TestResult.testFailed(result, testMethodName);
			break;

		case ITestResult.SKIP:
			TestResult.testSkipped(result, testMethodName);
			break;

		default:
			throw new RuntimeException("Invalid status");
		}
		extent.flush();
	}

	@AfterSuite
	protected void closeDriver() {
		System.out.println("========================================================================================");
		driver.quit();
		isLoggedIn = false;
		if (analyticsTest) {
			driverTwo.quit();
			analyticsTest = false;
		}
	}

}