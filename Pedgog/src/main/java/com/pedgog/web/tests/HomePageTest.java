package com.pedgog.web.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.pedgog.utilities.ConfigFileReader;
import com.pedgog.web.common.TestBase;
import com.pedgog.web.pages.ConductPage;
import com.pedgog.web.pages.ExplorePage;
import com.pedgog.web.pages.HelpPage;
import com.pedgog.web.pages.PedgogHomePage;
import com.pedgog.web.pages.PedgogLoginPage;
import com.pedgog.web.pages.PreparePage;
import com.pedgog.web.pages.ProjectsPage;

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
