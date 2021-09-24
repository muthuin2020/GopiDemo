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
import com.myApp.web.pages.MyAppRegisterPage;
import com.myApp.web.pages.MyAppSessionPage;
import com.pedgog.utilities.ConfigFileReader;
import com.pedgog.web.common.TestBase;

public class RegisterTest extends TestBase {
	MyAppRegisterPage myAppRegisterPage;
	MyAppHomePage myAppHomePage;
	MyAppLoginPage loginPage;
	String homePageTitle = "classroom learning";

	@BeforeClass
	public void loginSetup() {
		myAppRegisterPage = new MyAppRegisterPage(driverTwo);
		myAppHomePage = new MyAppHomePage(driverTwo);
		loginPage = new MyAppLoginPage(driverTwo);
		prop = new ConfigFileReader().getConfig();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(priority = 1)
	public void loginWithCorrectCredentials() {
//		sAssert.assertTrue(registerStudent("test_student74@gmail.com", "Royal@123", "STARMAKERAP", "seventyOne"));
//		isLoggedIn = true;
//		sAssert.assertAll();
	}

	public boolean registerStudent(String userEmail, String pwd, String accessCode, String fullName, WebDriver driver) {
		myAppRegisterPage = new MyAppRegisterPage(driver);
		myAppHomePage = new MyAppHomePage(driver);
		loginPage = new MyAppLoginPage(driver);
		loginPage.clickRegister();
		myAppRegisterPage.enterUserEmail(userEmail);
		myAppRegisterPage.enterPassword(pwd);
		myAppRegisterPage.enterAccessCode(accessCode);
		myAppRegisterPage.enterFullName(fullName);
		myAppRegisterPage.clickRegister();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (myAppHomePage.getHomePageTitle().equals(homePageTitle))
			return true;
		else
			return false;
	}

}
