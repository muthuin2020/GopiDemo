package com.myApp.web.tests;

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

public class LoginTest extends TestBase {
	MyAppLoginPage loginPage;
	MyAppHomePage homePage;

	@BeforeClass
	public void loginSetup() {
		loginPage = new MyAppLoginPage(driverTwo);
		homePage = new MyAppHomePage(driverTwo);
		prop = new ConfigFileReader().getConfig();
	}


	@Test(priority = 1)
	public void loginWithCorrectCredentials() {
		loginPage.enterUserEmail(prop.getProperty("myAppLoginEmail"));
		loginPage.enterPassword(prop.getProperty("myAppLoginPassword"));
		loginPage.clickLogin();
		sAssert.assertEquals(homePage.getHomePageTitle(), prop.getProperty("myAppHomePageTitle"));
		isLoggedIn = true;
		sAssert.assertAll();
	}

	@Test(priority = 2)
	public void loginWithWrongEmail() throws InterruptedException {
		loginPage.enterUserEmail("abcd" + prop.getProperty("emailId"));
		loginPage.enterPassword(prop.getProperty("pwd"));
		loginPage.clickLogin();
		sAssert.assertEquals(loginPage.getLoginErrorMsg(), prop.getProperty("myAppLoginErrorMsg"));
		sAssert.assertAll();
	}

	@Test(priority = 3)
	public void loginWithWrongPassword() throws InterruptedException {
		loginPage.enterUserEmail(prop.getProperty("emailId"));
		loginPage.enterPassword("abcd" + prop.getProperty("pwd"));
		loginPage.clickLogin();
		sAssert.assertEquals(loginPage.getLoginErrorMsg(), prop.getProperty("myAppLoginErrorMsg"));
		sAssert.assertAll();
	}

	@AfterMethod
	public void logout() throws InterruptedException {
		if (isLoggedIn) {
			homePage.clickOnClassRoomLearning();
			MyAppSessionPage myAppSessionpage = new MyAppSessionPage(driverTwo);
			myAppSessionpage.clickOnLogout();
			isLoggedIn = false;
		}
	}

}
