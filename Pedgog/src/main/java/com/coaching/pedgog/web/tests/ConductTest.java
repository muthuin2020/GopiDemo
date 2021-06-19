package com.coaching.pedgog.web.tests;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.coaching.pedgog.web.pages.ConductPage;
import com.coaching.pedgog.web.pages.PedgogHomePage;
import com.coaching.pedgog.web.pages.PedgogLoginPage;
import com.pedgog.utilities.ConfigFileReader;
import com.pedgog.web.common.TestBase;

public class ConductTest extends TestBase {
	PedgogLoginPage loginPage;
	ConductPage conductPage;
	PedgogHomePage homePage;

	@BeforeClass
	public void loginSetup() {
		prop = new ConfigFileReader().getConfig();
		conductPage = new ConductPage(driver);
		if (!isLoggedIn) {
			homePage = loginToPedgog();
		} else
			homePage = new PedgogHomePage(driver);

		try {
			sAssert.assertEquals(conductPage.getConductPageTtile(), prop.getProperty("pageTitle"));
		} catch (Exception e) {
			System.out.println("I am not in Conduct Page, navigating to Conduct page...");
			homePage.gotoConduct();
			currentPage = conductPage.getConductPageTtile();
		}
	}

	@Test(priority = 20)
	public void verifyConductPageTitle() {
		sAssert.assertEquals(conductPage.getConductPageTtile(), prop.getProperty("pageTitle"));
		sAssert.assertAll();
	}

	@Test(priority = 21)
	public void verifyConductPageHeader() {
		sAssert.assertEquals(conductPage.getPageHeader(), prop.getProperty("header"));
		sAssert.assertAll();
	}

	@Test(priority = 22)
	public void verifyModuleSectionsCount() {
		sAssert.assertEquals(conductPage.getModuleSectionsCount(),
				Integer.parseInt(prop.getProperty("moduleSectionCount")));
		sAssert.assertAll();
	}

	@Test(priority = 23)
	public void verifyModuleSectionsTitles() {
		List<String> titles = conductPage.getModuleSectionsTitle();
		for (int i = 0; i < titles.size(); i++) {
			sAssert.assertEquals(titles.get(i), prop.getProperty("moduleSectionTitle" + (i + 1)));
		}
		sAssert.assertAll();
	}

	@Test(priority = 24)
	public void verifyAllModulesTitle() {
		int count = conductPage.getModuleSectionsCount();
		for (int i = 0; i < count; i++) {
			int listedCount = conductPage.getListedModulesCountInTheSection(i + 1);
			for (int j = 0; j < listedCount; j++) {
				int a = i + 1, b = j + 1;
				String title = conductPage.getListedModulesTitle(a, b);
				try {
					sAssert.assertEquals(title, prop.get("moduleTitle" + a + b));
				} catch (Exception e) {
					System.err.println("Module titles NOT matching : Section " + a + ", module " + b);
				}
			}
		}
		sAssert.assertAll();
	}

	@Test(priority = 25)
	public void verifyConductButtonOnModules() {
		sAssert.assertEquals(conductPage.getConductPageTtile(), prop.getProperty("pageTitle"));
		int count = conductPage.getModuleSectionsCount();
		for (int i = 0; i < count; i++) {
			int listedCount = conductPage.getListedModulesCountInTheSection(i + 1);
			for (int j = 0; j < listedCount; j++) {
				sAssert.assertEquals(conductPage.getConductPageTtile(), prop.getProperty("pageTitle"));
				int a = i + 1, b = j + 1;
				conductPage.clickConductOnModule(a, b);
				String title = conductPage.getCreateSessionPageTitle();
				sAssert.assertEquals(title.toLowerCase(), prop.get("CreateSessionPageTitle").toString().toLowerCase());
				conductPage.closeCreateSessionPage();
			}
		}
		sAssert.assertAll();
	}

	@Test(priority = 26)
	public void verifyClickOnConductLaunchesTheCorrectModule() {
		sAssert.assertEquals(conductPage.getConductPageTtile(), prop.getProperty("pageTitle"));
		int count = conductPage.getModuleSectionsCount();
		for (int i = 0; i < count; i++) {
			int listedCount = conductPage.getListedModulesCountInTheSection(i + 1);
			for (int j = 0; j < listedCount; j++) {
				sAssert.assertEquals(conductPage.getConductPageTtile(), prop.getProperty("pageTitle"));
				int a = i + 1, b = j + 1;
				conductPage.clickConductOnModule(a, b);
				sAssert.assertEquals(conductPage.getCreateSessionPageTitle().toLowerCase(),
						prop.get("CreateSessionPageTitle").toString().toLowerCase());
				sAssert.assertEquals(conductPage.getCreateSessionPageCourse().toLowerCase(),
						prop.get("moduleSectionTitle" + a).toString().toLowerCase());
				sAssert.assertEquals(conductPage.getCreateSessionPageTopic().toLowerCase(),
						prop.get("moduleTitle" + a + b).toString().toLowerCase());

				conductPage.closeCreateSessionPage();
			}
		}
		sAssert.assertAll();
	}

	@Test(priority = 27)
	public void createNewConductSession() {
		sAssert.assertEquals(conductPage.getConductPageTtile(), prop.getProperty("pageTitle"));
		int count = conductPage.getModuleSectionsCount();
		for (int i = 0; i < count; i++) {
			int listedCount = conductPage.getListedModulesCountInTheSection(i + 1);
			for (int j = 0; j < listedCount; j++) {
				sAssert.assertEquals(conductPage.getConductPageTtile(), prop.getProperty("pageTitle"));
				int a = i + 1, b = j + 1;
				conductPage.clickConductOnModule(a, b);
				conductPage.enterSessionName(prop.get("moduleTitle" + a + b) + " testing");
				conductPage.clickSessionCreateButton();
				System.out.println("conducting module is : " + conductPage.getConductingModuleTitle());
				sAssert.assertEquals(conductPage.getConductingModuleTitle().toLowerCase(),
						(currentPage + " | " + prop.get("moduleTitle" + a + b)).toString().toLowerCase());
				conductPage.exitFromConducting();
			}
		}
		sAssert.assertAll();
	}

	@Test(priority = 28)
	public void verifySearchOnConductPage() {
		String searchTexts[] = { "Become", "your", "Conduct", "Recognize", "Destiny", "of", "to", "Trust", "Creator",
				"Think", "Solution", "Dynamic", "Our", "Success" };
		sAssert.assertEquals(conductPage.getConductPageTtile(), prop.getProperty("pageTitle"));
		conductPage.clickOnSearchButton();
		int randomNumber = (int) (Math.random() * (searchTexts.length - 0 + 1) + 0);
		conductPage.enterSearchText(searchTexts[randomNumber]);
		logger.log(Status.INFO, "searching for text : " + searchTexts[randomNumber]);
		System.out.println("searching for text : " + searchTexts[randomNumber]);
		List<String> titles = conductPage.getModuleTitles();
		for (String title : titles) {
			sAssert.assertTrue(title.toLowerCase().contains(searchTexts[randomNumber]));
		}
		conductPage.closeSearchBar();
	}

}
