package com.pedgog.web.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pedgog.utilities.ConfigFileReader;
import com.pedgog.web.common.TestBase;
import com.pedgog.web.pages.PedgogLoginPage;
import com.pedgog.web.pages.PedgogRegisterPage;

public class RegisterTest extends TestBase{
	PedgogRegisterPage registerPage;
	PedgogLoginPage loginPage;

	@BeforeClass
	public void registerSetup() {
		registerPage = new PedgogRegisterPage(driver);
		loginPage = new PedgogLoginPage(driver);
		prop = new ConfigFileReader().getConfig();
	}

	@Test(priority = 5)
	public void registerWithCorrectData() {
		loginPage.clickRegister();
		registerPage.enterUserName(prop.getProperty("fullname"));
		registerPage.enterUserEmail(prop.getProperty("emailId"));
		registerPage.enterPassword(prop.getProperty("pwd"));
		registerPage.enterAccessCode(prop.getProperty("accessCode"));
		registerPage.clickRegister();
		// Assert.assertEquals(homePage.homePageTtile(), title);
	}

}
