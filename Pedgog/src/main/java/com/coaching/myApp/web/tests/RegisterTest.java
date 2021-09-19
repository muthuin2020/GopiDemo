package com.coaching.myApp.web.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.coaching.pedgog.web.pages.PedgogLoginPage;
import com.coaching.pedgog.web.pages.PedgogRegisterPage;
import com.pedgog.utilities.ConfigFileReader;
import com.pedgog.web.common.TestBase;

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
		registerToPedgog(prop.getProperty("fullname"),prop.getProperty("emailId"), prop.getProperty("pwd"),prop.getProperty("accessCode")  );
		//Assert.assertEquals(homePage.homePageTtile(), title);
	}

}
