package com.coaching.myApp.web.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.coaching.myApp.web.pages.CoachingAppHomePage;
import com.coaching.myApp.web.pages.CoachingAppLoginPage;
import com.coaching.pedgog.web.pages.PedgogHomePage;
import com.coaching.pedgog.web.pages.PedgogLoginPage;
import com.pedgog.utilities.ConfigFileReader;
import com.pedgog.web.common.TestBase;

public class LoginTest extends TestBase {
	CoachingAppLoginPage loginPage;
	CoachingAppHomePage homePage;

	@BeforeClass
	public void loginSetup() {
		loginPage = new CoachingAppLoginPage(driver);
		homePage = new CoachingAppHomePage(driver);
		prop = new ConfigFileReader().getConfig();
	}

	@Test(priority = 1)
	public void loginWithCorrectCredentials() {
		loginToCoachingApp(prop.getProperty("coachingAppEmailId"), prop.getProperty("coachingAppPassword"));
		sAssert.assertEquals(homePage.getHomePageTtile(), prop.getProperty("coachingAppHomePageTitle"));
		isLoggedIn = true;
		sAssert.assertAll();
	}

	@Test(priority = 2)
	public void loginWithWrongEmail() throws InterruptedException {
		loginToCoachingApp("abcd" + prop.getProperty("emailId"),prop.getProperty("pwd"));
		sAssert.assertEquals(loginPage.getLoginErrorMsg(), prop.getProperty("coachingAppLoginErrorMsg"));
		sAssert.assertAll();
	}

	@Test(priority = 3)
	public void loginWithWrongPassword() throws InterruptedException {
		loginToCoachingApp(prop.getProperty("emailId"),"abcd"+prop.getProperty("pwd"));
		sAssert.assertEquals(loginPage.getLoginErrorMsg(), prop.getProperty("coachingAppLoginErrorMsg"));
		sAssert.assertAll();
	}
	
	public void loginToCoachingApp(String userName, String password) {
		loginPage.enterUserEmail(userName);
		loginPage.enterPassword(password);
		loginPage.clickLogin();
		homePage=new CoachingAppHomePage(driver);
	}

	@AfterMethod
	public void logout() throws InterruptedException {
		if (isLoggedIn) {
			homePage.logout();
			isLoggedIn = false;
		}
	}

}
