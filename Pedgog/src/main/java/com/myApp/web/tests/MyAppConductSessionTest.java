package com.myApp.web.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.coaching.myApp.web.pages.CoachingAppHomePage;
import com.coaching.myApp.web.pages.CoachingAppLoginPage;
import com.coaching.pedgog.web.pages.PedgogHomePage;
import com.coaching.pedgog.web.pages.PedgogLoginPage;
import com.myApp.web.pages.MyAppHomePage;
import com.myApp.web.pages.MyAppLoginPage;
import com.myApp.web.pages.MyAppSessionPage;
import com.pedgog.utilities.ConfigFileReader;
import com.pedgog.web.common.TestBase;

public class MyAppConductSessionTest extends TestBase {
	CoachingAppLoginPage coachingAppLoginPage;
	CoachingAppHomePage coachingAppHomePage;
	MyAppLoginPage myAppLoginPage;
	MyAppHomePage myAppHomePage;

	@BeforeClass
	public void loginSetup() {
		coachingAppLoginPage = new CoachingAppLoginPage(driver);
		coachingAppHomePage = new CoachingAppHomePage(driver);
		myAppLoginPage = new MyAppLoginPage(driverTwo);
		myAppHomePage = new MyAppHomePage(driverTwo);
		prop = new ConfigFileReader().getConfig();
	}

	@Test(priority = 1)
	public void attendSessionWithOtp() throws InterruptedException {
		coachingAppLoginPage.enterUserEmail(prop.getProperty("coachingAppEmailId"));
		coachingAppLoginPage.enterPassword(prop.getProperty("coachingAppPassword"));
		coachingAppLoginPage.clickLogin();
		sAssert.assertEquals(coachingAppHomePage.getHomePageTtile(), prop.getProperty("coachingAppHomePageTitle"));
		coachingAppHomePage.clickOnConduct(prop.getProperty("moduleToConduct"));
		Thread.sleep(2000);
		otpNumber = coachingAppHomePage.getOtpNumber();
		System.out.println("Generated Otp is : " + otpNumber);

		loginToMyApp(prop.getProperty("myAppLoginEmail"), prop.getProperty("myAppLoginPassword"), driverTwo);
		Thread.sleep(2000);
		joinSession(driverTwo);

	}

	public void loginToMyApp(String userName, String password, WebDriver driver) {
		myAppLoginPage.enterUserEmail(userName);
		myAppLoginPage.enterPassword(password);
		myAppLoginPage.clickLogin();
		myAppHomePage = new MyAppHomePage(driver);
	}

	public void joinSession(WebDriver driver) throws InterruptedException {
		myAppHomePage = new MyAppHomePage(driver);
		myAppHomePage.clickOnClassRoomLearning();
		MyAppSessionPage myAppSessionpage = new MyAppSessionPage(driverTwo);
		myAppSessionpage.clickOnJoinSessionButton();
		myAppSessionpage.enterOtp(otpNumber);
		Thread.sleep(2000);
		myAppSessionpage.clickOnJoinButton();
		Thread.sleep(13000);

	}

}
