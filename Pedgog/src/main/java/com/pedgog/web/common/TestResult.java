package com.pedgog.web.common;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.pedgog.utilities.JiraOperationsREST;

public class TestResult extends TestBase {

	public static void testPassed(ITestResult result, String testName) {
		System.out.println("Test case " + testName + " is Passed");
		logger.log(Status.PASS, "Test case " + testName + " is Passed");
		try {
			JiraOperationsREST.updateJira("AD-7863", "Listener_Regression_UAT1", "AD-3302", "Passed");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public static void testFailed(ITestResult result, String testName) {
		System.err.println("Test case " + testName + " is Failed");
		logger.log(Status.FAIL, "Test case " + testName + " is Failed");
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String filePath = System.getProperty("user.dir") + "\\screenshots\\" + testName + System.currentTimeMillis()
					+ "_" + ".png";
			File destFile = new File(filePath);
			FileHandler.copy(scrFile, destFile);
			logger.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(filePath).build());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void testSkipped(ITestResult result, String testName) {
		logger.log(Status.SKIP, "Test case " + testMethodName + " is Skipped");
		System.out.println("Test case " + testMethodName + " is Skipped");
	}

}