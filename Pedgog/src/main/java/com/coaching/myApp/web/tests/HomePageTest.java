package com.coaching.myApp.web.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.coaching.pedgog.web.pages.ConductPage;
import com.coaching.pedgog.web.pages.ExplorePage;
import com.coaching.pedgog.web.pages.HelpPage;
import com.coaching.pedgog.web.pages.PedgogHomePage;
import com.coaching.pedgog.web.pages.PedgogLoginPage;
import com.coaching.pedgog.web.pages.PreparePage;
import com.coaching.pedgog.web.pages.ProjectsPage;
import com.pedgog.utilities.ConfigFileReader;
import com.pedgog.web.common.TestBase;

public class HomePageTest extends TestBase {
	PedgogLoginPage loginPage;
	PedgogHomePage homePage;
	ExplorePage explorePage;
	PreparePage preparePage;
	ConductPage conductPage;
	ProjectsPage projectsPage;
	HelpPage helpPage;

	@BeforeClass
	public void loginSetup() {
		prop = new ConfigFileReader().getConfig();
		if (!isLoggedIn) {
			homePage = loginToPedgog();
		}
	}

	@Test(priority = 6)
	public void navigateToPreparePage() {
		homePage.gotoPrepare();
		preparePage = new PreparePage(driver);
		sAssert.assertEquals(preparePage.getPreparePageTtile(), prop.getProperty("preparePageTitle"));
		sAssert.assertAll();
	}

	@Test(priority = 7)
	public void navigateToConductPage() {
		homePage.gotoConduct();
		conductPage = new ConductPage(driver);
		sAssert.assertEquals(conductPage.getConductPageTtile(), prop.getProperty("conductPageTitle"));
		sAssert.assertAll();
	}

	@Test(priority = 8)
	public void navigateToProjectsPage() {
		homePage.gotoProjects();
		projectsPage = new ProjectsPage(driver);
		sAssert.assertEquals(projectsPage.getProjectsPageTtile(), prop.getProperty("projectsPageTitle"));
		sAssert.assertAll();
	}

	@Test(priority = 9)
	public void navigateToExplorePage() {
		homePage.gotoExplore();
		explorePage = new ExplorePage(driver);
		sAssert.assertEquals(explorePage.getExplorePageTtile(), prop.getProperty("explorePageTitle"));
		sAssert.assertAll();
	}
	
	@Test(priority = 10)
	public void navigateToHelpPage() throws InterruptedException {
		homePage.gotoHelp();
		helpPage = new HelpPage(driver);
		sAssert.assertTrue(helpPage.isVideoAvailable());
		helpPage.closeHelpPage();
		sAssert.assertAll();
	}
}
