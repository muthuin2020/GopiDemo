package com.pedgog.web.tests;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.pedgog.utilities.ConfigFileReader;
import com.pedgog.web.common.TestBase;
import com.pedgog.web.pages.ConductPage;
import com.pedgog.web.pages.ExplorePage;
import com.pedgog.web.pages.PedgogHomePage;
import com.pedgog.web.pages.PedgogLoginPage;

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
			System.out.println("User is not in Conduct Page, navigating to Conduct page...");
			homePage.gotoConduct();
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
				sAssert.assertEquals(conductPage.getCreateSessionPageTitle().toLowerCase(), prop.get("CreateSessionPageTitle").toString().toLowerCase());
				sAssert.assertEquals(conductPage.getCreateSessionPageCourse().toLowerCase(), prop.get("moduleSectionTitle"+a).toString().toLowerCase());
				sAssert.assertEquals(conductPage.getCreateSessionPageTopic().toLowerCase(), prop.get("moduleTitle"+a+b).toString().toLowerCase());
				
				conductPage.closeCreateSessionPage();
			}
		}
		sAssert.assertAll();
	}
}
