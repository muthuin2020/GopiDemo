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
		loginPage.enterUserEmail(prop.getProperty("myAppEmailId"));
		loginPage.enterPassword(prop.getProperty("myAppPassword"));
		loginPage.clickLogin();
		sAssert.assertEquals(homePage.getHomePageTtile(), prop.getProperty("myAppHomePageTitle"));
		isLoggedIn = true;
		homePage.clickOnConduct("The World of the StarMaker Associate");
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
			homePage.logout();
			isLoggedIn = false;
		}
	}

}
