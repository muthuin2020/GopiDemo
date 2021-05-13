package com.pedgog.web.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.pedgog.web.pages.PedgogHomePage;
import com.pedgog.web.pages.PedgogLoginPage;
import com.pedgog.web.tests.HomePageTest;

public class TestBase {
	public static WebDriver driver;
	public static boolean isLoggedIn = false;
	public static String userName, userPassword, homePageTitle, testMethodName;
	public static Properties prop;
	PedgogLoginPage loginPage;
	PedgogHomePage homePage;
	ITestResult result;
	public static SoftAssert sAssert;
	public static ExtentHtmlReporter reporter;
	public static ExtentReports extent;
	public static ExtentTest logger;

	@BeforeSuite
	protected void setDrivers() throws InterruptedException {
		setLoginData();
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println("Launched Google Chrome");
		driver.manage().window().maximize();
		driver.get("https://coaching.pedgog.in/");
		System.out.println("Opened Pedgog website");
		Thread.sleep(2000);

		reporter = new ExtentHtmlReporter("./reports/learn_automation1.html");

		// Create object of ExtentReports class- This is main class which will create
		// report
		extent = new ExtentReports();

		// attach the reporter which we created in Step 1
		extent.attachReporter(reporter);
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

	public PedgogHomePage loginToPedgog() {
		loginPage = new PedgogLoginPage(driver);
		homePage = new PedgogHomePage(driver);
		driver.get("https://coaching.pedgog.in/");
		System.out.println("Logging into Pedgog");
		loginPage.enterUserEmail(userName);
		System.out.println("Entered user email ");
		loginPage.enterPassword(userPassword);
		System.out.println("Entered user password ");
		loginPage.clickLogin();
		System.out.println("Clicked Login button ");
		if (homePage.getHomePageTtile().equals(homePageTitle)) {
			isLoggedIn = true;
			System.out.println("Logged in to Pedgog");
		} else
			System.err.println(
					"------------------------------ Login to Pedgog failed -----------------------------------");
		return PageFactory.initElements(driver, PedgogHomePage.class);
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

	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		switch (result.getStatus()) {
		case ITestResult.SUCCESS:
			System.out.println("Test case " + testMethodName + " is Passed");
			logger.log(Status.PASS, "Test case " + testMethodName + " is Passed");
			break;

		case ITestResult.FAILURE:
			System.err.println("Test case " + testMethodName + " is Failed");
			logger.log(Status.FAIL, "Test case " + testMethodName + " is Failed");
			try {
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				String filePath = System.getProperty("user.dir") + "\\screenshots\\" + testMethodName
						+ System.currentTimeMillis() + "_" + ".png";
				File destFile = new File(filePath);
				FileHandler.copy(scrFile, destFile);
				logger.fail(result.getThrowable().getMessage(),
						MediaEntityBuilder.createScreenCaptureFromPath(filePath).build());

			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		case ITestResult.SKIP:
			logger.log(Status.SKIP, "Test case " + testMethodName + " is Skipped");
			System.out.println("Test case " + testMethodName + " is Skipped");
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
	}

}