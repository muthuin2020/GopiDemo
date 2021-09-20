package com.coaching.myApp.web.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.coaching.myApp.web.pages.CoachingAppHomePage;
import com.coaching.myApp.web.pages.CoachingAppLoginPage;
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

	CoachingAppHomePage homePage;

	@BeforeClass
	public void loginSetup() {
		prop = new ConfigFileReader().getConfig();
		if (!isLoggedIn) {
			homePage = loginToCoachingApp();
		}
	}

	@Test(priority = 6)
	public void clickOnConductModule() {
		String currentModule = "The World of the StarMaker Associate";
		homePage.clickOnConduct(currentModule);
		sAssert.assertEquals(homePage.getConductingModuleTitle(), currentModule);
		sAssert.assertAll();
	}

	@Test(priority = 7)
	public void getOtpLink() {
		sAssert.assertEquals(homePage.getOtpLink(),
				"https://uatmyapp.pedgog.in/auth?accesscode=STARMAKERAP&&otp=" + homePage.getOtpNumber());
		sAssert.assertAll();
		otpLink=homePage.getOtpLink();
	}
	
	@Test(priority = 8)
	public void getOtpNumber() {
		String otpLink=homePage.getOtpLink();
		sAssert.assertEquals(otpLink.substring(otpLink.length()-4),
				homePage.getOtpNumber());
		sAssert.assertAll();
		otpNumber=homePage.getOtpNumber();
	}

}
