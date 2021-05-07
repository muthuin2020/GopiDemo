package com.pedgog.web.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pedgog.utilities.ConfigFileReader;
import com.pedgog.web.common.TestBase;
import com.pedgog.web.pages.PedgogHomePage;
import com.pedgog.web.pages.PedgogLoginPage;

public class LoginTest extends TestBase {
	PedgogLoginPage loginPage;
	PedgogHomePage homePage;

	@BeforeTest
	public void loginSetup() {
		loginPage = new PedgogLoginPage(driver);
		homePage = new PedgogHomePage(driver);
		prop = new ConfigFileReader().getConfig();
	}

	@Test(priority = 1)
	public void loginWithCorrectCredentials() {
		loginPage.enterUserEmail(prop.getProperty("emailId"));
		loginPage.enterPassword(prop.getProperty("pwd"));
		loginPage.clickLogin();
		Assert.assertEquals(homePage.getHomePageTtile(), prop.getProperty("title"));
		isLoggedIn = true;
	}

	@Test(priority = 2)
	public void loginWithWrongEmail() throws InterruptedException {
		loginPage.enterUserEmail("abcd" + prop.getProperty("emailId"));
		loginPage.enterPassword(prop.getProperty("pwd"));
		loginPage.clickLogin();
		Assert.assertEquals(loginPage.getLoginErrorMsg(), prop.getProperty("loginErrorMsg"));
	}

	@Test(priority = 3)
	public void loginWithWrongPassword() throws InterruptedException {
		loginPage.enterUserEmail(prop.getProperty("emailId"));
		loginPage.enterPassword("abcd" + prop.getProperty("pwd"));
		loginPage.clickLogin();
		Assert.assertEquals(loginPage.getLoginErrorMsg(), prop.getProperty("loginErrorMsg"));
	}

	@AfterMethod
	public void logout() throws InterruptedException {
		if (isLoggedIn) {
			homePage.logout();
			isLoggedIn = false;
		}
	}

}
