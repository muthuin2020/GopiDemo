package com.coaching.pedgog.web.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.coaching.pedgog.web.pages.ConductModulePage;
import com.coaching.pedgog.web.pages.ConductPage;
import com.coaching.pedgog.web.pages.PedgogHomePage;
import com.coaching.pedgog.web.pages.PedgogLoginPage;
import com.pedgog.utilities.ConfigFileReader;
import com.pedgog.web.common.TestBase;

public class ConductModuleTest extends TestBase {
	PedgogLoginPage loginPage;
	ConductPage conductPage;
	PedgogHomePage homePage;
	ConductModulePage conductModulePage;

	@BeforeClass
	public void loginSetup() {
		prop = new ConfigFileReader().getConfig();
		conductPage = new ConductPage(driver);
		if (!isLoggedIn) {
			homePage = loginToPedgog();
		} else
			homePage = new PedgogHomePage(driver);

		System.out.println("Navigating to Conduct page...");
		homePage.gotoConduct();
		currentPage = conductPage.getConductPageTtile();
		conductModulePage = createNewConductSession();
	}

	public ConductModulePage createNewConductSession() {
		int totalSections = conductPage.getModuleSectionsCount();
		int section = (int) (Math.random() * (totalSections - 1 + 1) + 1);
		int totalModules = conductPage.getListedModulesCountInTheSection(section);
		int module = (int) (Math.random() * (totalModules - 1 + 1) + 1);
		conductPage.clickConductOnModule(section, module);
		conductPage.enterSessionName("conduct testing");
		conductPage.clickSessionCreateButton();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		sAssert.assertTrue(!conductPage.getConductingModuleTitle().isEmpty(),
//				"Assert failed, conduct module title is empty..");
		return new ConductModulePage(driver);
	}

	@Test
	public void vetrifyNextButton() throws InterruptedException {
		System.out.println("verify next button done");
		Thread.sleep(5000);
		conductModulePage.clickOnNextButton();
		Thread.sleep(10000);
	}

}
