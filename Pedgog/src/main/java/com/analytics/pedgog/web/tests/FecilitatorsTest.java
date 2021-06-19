package com.analytics.pedgog.web.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.analytics.pedgog.web.pages.AnalyticsHomePage;
import com.analytics.pedgog.web.pages.AnalyticsLoginPage;
import com.coaching.pedgog.web.pages.ExplorePage;
import com.coaching.pedgog.web.pages.PedgogHomePage;
import com.coaching.pedgog.web.pages.PedgogLoginPage;
import com.coaching.pedgog.web.pages.PedgogRegisterPage;
import com.pedgog.utilities.ConfigFileReader;
import com.pedgog.web.common.TestBase;

public class FecilitatorsTest extends TestBase {
	AnalyticsLoginPage loginPage;
	AnalyticsHomePage homePage;
	PedgogLoginPage pedgogLoginPage;
	PedgogRegisterPage pedgogRegisterPage;

	@BeforeClass
	public void loginSetup() {
		prop = new ConfigFileReader().getConfig();

		if (!isLoggedInToAnalytics) {
			homePage = loginToAnalytics();
		} else
			homePage = new AnalyticsHomePage(driverTwo);

		try {
			sAssert.assertEquals(driverTwo.getTitle(), prop.getProperty("React App"));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Test(priority = 1)
	public void verifyNewFacultyRegistrations() throws InterruptedException {
		homePage.goToFecilitators();
		Thread.sleep(3000);
		pedgogLoginPage = new PedgogLoginPage(driver);
		pedgogRegisterPage = new PedgogRegisterPage(driver);
		pedgogLoginPage.clickRegister();
		pedgogRegisterPage.enterUserName("test user");
		pedgogRegisterPage.enterUserEmail("test10_bputfrcp1@illumine.in");
		pedgogRegisterPage.enterPassword("Testing@123");
		pedgogRegisterPage.enterAccessCode("BPUTFRCP1");
		pedgogRegisterPage.clickRegister();

	}

}
