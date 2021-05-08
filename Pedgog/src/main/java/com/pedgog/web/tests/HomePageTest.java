package com.pedgog.web.tests;

import java.util.Properties;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
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
		Assert.assertEquals(preparePage.getPreparePageTtile(), prop.getProperty("preparePageTitle"));
	}

	@Test(priority = 7)
	public void navigateToConductPage() {
		homePage.gotoConduct();
		conductPage = new ConductPage(driver);
		Assert.assertEquals(conductPage.getConductPageTtile(), prop.getProperty("conductPageTitle"));
	}

	@Test(priority = 8)
	public void navigateToProjectsPage() {
		homePage.gotoProjects();
		projectsPage = new ProjectsPage(driver);
		Assert.assertEquals(projectsPage.getProjectsPageTtile(), prop.getProperty("projectsPageTitle"));
	}

	@Test(priority = 9)
	public void navigateToExplorePage() {
		homePage.gotoExplore();
		explorePage = new ExplorePage(driver);
		Assert.assertEquals(explorePage.getExplorePageTtile(), prop.getProperty("explorePageTitle"));
	}
	
	@Test(priority = 10)
	public void navigateToHelpPage() throws InterruptedException {
		homePage.gotoHelp();
		helpPage = new HelpPage(driver);
		Assert.assertTrue(helpPage.isVideoAvailable());
		helpPage.closeHelpPage();
	}
}
