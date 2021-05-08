package com.pedgog.web.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
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

	@Test(priority = 11)
	public void verifyExplorePageHeader() {
		Assert.assertEquals(explorePage.getExplorePageHeader(), prop.getProperty("header"));
	}

	@Test(priority = 12)
	public void verifyExplorePageSummary() {
		Assert.assertEquals(explorePage.getExplorePageSummary(), prop.getProperty("summary"));
	}

	@Test(priority = 13)
	public void verifyModuleSectionsCount() {
		Assert.assertEquals(explorePage.getExplorePageSectionsCount(),
				Integer.parseInt(prop.getProperty("moduleSectionCount")));
	}

	@Test(priority = 14)
	public void verifyModuleSectionsTitles() {
		List<String> titles = explorePage.getExplorePageModuleSectionsTitle();
		for (int i = 0; i < titles.size(); i++) {
			Assert.assertEquals(titles.get(i), prop.getProperty("moduleSectionTitle" + (i + 1)));
		}
	}

}
