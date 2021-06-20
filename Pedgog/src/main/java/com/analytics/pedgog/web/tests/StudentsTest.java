package com.analytics.pedgog.web.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.analytics.pedgog.web.pages.AnalyticsFacilatorsPage;
import com.analytics.pedgog.web.pages.AnalyticsHomePage;
import com.analytics.pedgog.web.pages.AnalyticsLoginPage;
import com.analytics.pedgog.web.pages.AnalyticsStudentsPage;
import com.coaching.pedgog.web.pages.ExplorePage;
import com.coaching.pedgog.web.pages.PedgogHomePage;
import com.coaching.pedgog.web.pages.PedgogLoginPage;
import com.coaching.pedgog.web.pages.PedgogRegisterPage;
import com.pedgog.utilities.ConfigFileReader;
import com.pedgog.web.common.TestBase;

public class StudentsTest extends TestBase {
	AnalyticsLoginPage loginPage;
	AnalyticsHomePage homePage;
	AnalyticsStudentsPage analyticsStudentsPage;

	@BeforeClass
	public void loginSetup() {
		prop = new ConfigFileReader().getConfig();
		analyticsStudentsPage = new AnalyticsStudentsPage(driverTwo);
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
	public void verifyNewStudentsRegistrations() throws InterruptedException {
		homePage.goToStudentsOnboarding();
		Thread.sleep(3000);
		int initialCount = analyticsStudentsPage.getNewStudentsRegistrations();
		System.out.println(initialCount);
//		registerToPedgog(prop.getProperty("pedgogRegisterName"), prop.getProperty("pedgogRegisterEmail"),
//				prop.getProperty("pedgogRegisterPassword"), prop.getProperty("pedgogRegisterAccessCode"));
		driverTwo.navigate().refresh();
		int afterCount = analyticsStudentsPage.getNewStudentsRegistrations();
		System.out.println(afterCount);
		sAssert.assertEquals(initialCount + 1, afterCount);
	}

	@Test(priority = 2)
	public void verifyStudentsRegistrationsOnSelectedPeriod() throws InterruptedException {
		homePage.goToStudentsOnboarding();
		Thread.sleep(3000);

		//analyticsStudentsPage.filterLastMonth();
		System.err.println("Last Month count : " + analyticsStudentsPage.getNewStudentsRegistrations() + " "
				+ analyticsStudentsPage.getCummulativeStudentsRegistrations() + " "
				+ analyticsStudentsPage.getTotalStudentsRegistrations());
		
		analyticsStudentsPage.filterLastThreetMonth();
		System.err.println("Last 3 Months count : " + analyticsStudentsPage.getNewStudentsRegistrations() + " "
				+ analyticsStudentsPage.getCummulativeStudentsRegistrations() + " "
				+ analyticsStudentsPage.getTotalStudentsRegistrations());

		analyticsStudentsPage.filterLastYear();
		System.err.println("Last Year count : " + analyticsStudentsPage.getNewStudentsRegistrations() + " "
				+ analyticsStudentsPage.getCummulativeStudentsRegistrations() + " "
				+ analyticsStudentsPage.getTotalStudentsRegistrations());

	}

}
