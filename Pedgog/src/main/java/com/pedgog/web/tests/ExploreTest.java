package com.pedgog.web.tests;

import java.util.ArrayList;
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
			System.out.println("User is not in Explore Page, navigating to Explore page...");
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

	@Test(priority = 15)
	public void verifyModuleSectionsSummary() {
		List<String> titles = explorePage.getExplorePageModuleSectionsSummary();
		for (int i = 0; i < titles.size(); i++) {
			Assert.assertEquals(titles.get(i), prop.getProperty("moduleSectionSummary" + (i + 1)));
		}
	}

	@Test(priority = 16)
	public void verifyTitlesCountWithListedModulesCount() {
		List<String> titles = explorePage.getExplorePageModuleSectionsTitle();
		int count = explorePage.getExplorePageSectionsCount();
		for (int i = 0; i < count; i++) {
			String title = titles.get(i);
			int titleCount = Integer.parseInt(title.substring(title.lastIndexOf('(') + 1).split("\\)")[0]);
			int listedCount = explorePage.getListedModulesCountInTheSection(i + 1);
			Assert.assertEquals(titleCount, listedCount);
			System.out.println(i + 1 + " module's title count matches with listed Modules");
		}
	}

	@Test(priority = 17)
	public void verifyAllModulesTitle() {
		int count = explorePage.getExplorePageSectionsCount();
		for (int i = 0; i < count; i++) {
			int listedCount = explorePage.getListedModulesCountInTheSection(i + 1);
			for (int j = 0; j < listedCount; j++) {
				int a = i + 1, b = j + 1;
				String title = explorePage.getListedModulesTitle(a, b);
				System.out.println("page module : "+title);
				System.out.println("config module : "+prop.get("moduleTitle" + a + b));
				Assert.assertEquals(title, prop.get("moduleTitle" + a + b));
			}
		}
	}

}
