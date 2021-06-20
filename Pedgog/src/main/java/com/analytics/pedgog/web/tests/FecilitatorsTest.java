package com.analytics.pedgog.web.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.analytics.pedgog.web.pages.AnalyticsFecilatorsPage;
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
	AnalyticsFecilatorsPage analyticsFecilatorsPage;

	@BeforeClass
	public void loginSetup() {
		prop = new ConfigFileReader().getConfig();
		analyticsFecilatorsPage = new AnalyticsFecilatorsPage(driverTwo);
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
		int initialCount = analyticsFecilatorsPage.getNewFacultyRegistrations();
		System.out.println(initialCount);
		registerToPedgog(prop.getProperty("pedgogRegisterName"), prop.getProperty("pedgogRegisterEmail"),
				prop.getProperty("pedgogRegisterPassword"), prop.getProperty("pedgogRegisterAccessCode"));
		driverTwo.navigate().refresh();
		int afterCount = analyticsFecilatorsPage.getNewFacultyRegistrations();
		System.out.println(afterCount);
		sAssert.assertEquals(initialCount + 1, afterCount);

	}

}
