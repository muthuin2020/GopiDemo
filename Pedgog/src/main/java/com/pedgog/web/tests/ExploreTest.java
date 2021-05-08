package com.pedgog.web.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pedgog.utilities.ConfigFileReader;
import com.pedgog.web.common.TestBase;
import com.pedgog.web.pages.ExplorePage;
import com.pedgog.web.pages.PedgogHomePage;
import com.pedgog.web.pages.PedgogLoginPage;

public class ExploreTest extends TestBase {
	PedgogLoginPage loginPage;
	ExplorePage explorePage;
	PedgogHomePage homePage;

	@BeforeClass
	public void loginSetup() {
		prop = new ConfigFileReader().getConfig();
		explorePage = new ExplorePage(driver);
		if (!isLoggedIn) {
			homePage = loginToPedgog();
		} else
			homePage = new PedgogHomePage(driver);

		try {
			Assert.assertEquals(homePage.getHomePageTtile(), prop.getProperty("pageTitle"));
		} catch (Exception e) {
			homePage.gotoExplore();
		}
	}

	@Test (groups = {"ExplorePage"})
	public void verifyExplorePageHeader() {
		Assert.assertEquals(explorePage.getExplorePageHeader(), prop.getProperty("header"));
	}

	@Test (groups = {"ExplorePage"})
	public void verifyExplorePageSummary() {
		Assert.assertEquals(explorePage.getExplorePageSummary(), prop.getProperty("summary"));
	}

	@Test (groups = {"ExplorePage"})
	public void verifyModuleSectionsCount() {
		Assert.assertEquals(explorePage.getExplorePageSectionsCount(),
				Integer.parseInt(prop.getProperty("moduleSectionCount")));
	}

}
