package com.myApp.web.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.coaching.myApp.web.pages.CoachingAppConductPage;
import com.coaching.myApp.web.pages.CoachingAppHomePage;
import com.coaching.myApp.web.pages.CoachingAppLoginPage;
import com.coaching.pedgog.web.pages.PedgogHomePage;
import com.coaching.pedgog.web.pages.PedgogLoginPage;
import com.myApp.web.pages.MyAppHomePage;
import com.myApp.web.pages.MyAppLoginPage;
import com.myApp.web.pages.MyAppRegisterPage;
import com.myApp.web.pages.MyAppSessionPage;
import com.pedgog.utilities.ConfigFileReader;
import com.pedgog.web.common.TestBase;

public class MyAppConductSessionTest extends TestBase {
	CoachingAppLoginPage coachingAppLoginPage;
	CoachingAppHomePage coachingAppHomePage;
	MyAppLoginPage myAppLoginPage;
	MyAppHomePage myAppHomePage;
	MyAppRegisterPage myAppRegisterPage;
	RegisterTest registerTest;
	CoachingAppConductPage coachingAppConductPage;

	@BeforeClass
	public void loginSetup() {
		coachingAppLoginPage = new CoachingAppLoginPage(driver);
		coachingAppHomePage = new CoachingAppHomePage(driver);
		myAppLoginPage = new MyAppLoginPage(driverTwo);
		myAppHomePage = new MyAppHomePage(driverTwo);
		myAppRegisterPage = new MyAppRegisterPage(driverTwo);
		registerTest = new RegisterTest();
		prop = new ConfigFileReader().getConfig();
	}

	@Test(priority = 1)
	public void attendSessionWithOtp() throws InterruptedException {
		WebDriver driver = null;
		int studentNumber;
		coachingAppLoginPage.enterUserEmail(prop.getProperty("coachingAppEmailId"));
		coachingAppLoginPage.enterPassword(prop.getProperty("coachingAppPassword"));
		coachingAppLoginPage.clickLogin();
		sAssert.assertEquals(coachingAppHomePage.getHomePageTtile(), prop.getProperty("coachingAppHomePageTitle"));
		coachingAppHomePage.clickOnConduct(prop.getProperty("moduleToConduct"));
		Thread.sleep(2000);
		otpNumber = coachingAppHomePage.getOtpNumber();
		System.out.println("Generated Otp is : " + otpNumber);

		if (registerAndTest) {
			studentsList.clear();
			for (int i = 2; i < numberOfStudents + 2; i++) {
				driver = multipleStudents.getCurrentStudentsWindow(i);
				studentNumber = i - 1;
				registeredUserEmailCount++;
				registerTest.registerStudent(registerUserEmail + registeredUserEmailCount + "@gmail.com",
						registerUserPassword, registerAccessCode, registerUserEmail + registeredUserEmailCount, driver);
				studentsList.add(registerUserEmail + registeredUserEmailCount);
				joinSession(driver);
			}

		} else {

			for (int i = 2; i < numberOfStudents + 2; i++) {
				driver = multipleStudents.getCurrentStudentsWindow(i);
				studentNumber = i - 1;
				loginToMyApp(prop.getProperty("myAppLoginEmail" + studentNumber),
						prop.getProperty("myAppLoginPassword" + studentNumber), driver);
				joinSession(driver);

			}
			

		}
	}

	@Test(priority = 2)
	public void verifyTotalNumberOfStudents() throws InterruptedException {
		Thread.sleep(2000);
		sAssert.assertEquals(coachingAppHomePage.getTotalParticipants(), numberOfStudents);
		sAssert.assertAll();
	}

	@Test(priority = 3)
	public void verifyStudentsAreListedToTeacher() throws InterruptedException {
		Thread.sleep(2000);
		sAssert.assertTrue(coachingAppHomePage.getParticipantNames().equals(studentsList));
		sAssert.assertAll();
	}

	@Test(enabled=false)
	public void verifyAssessmentLinkIsPresent() throws InterruptedException {
		coachingAppHomePage.clickOnBeginSession();
		Thread.sleep(2000);
		coachingAppConductPage = new CoachingAppConductPage(driver);
		coachingAppConductPage.gotoAssessmentLink();
		assessmentLink = coachingAppConductPage.getAssessmentLink();
		sAssert.assertTrue(!assessmentLink.isEmpty());
		sAssert.assertAll();

	}

	@Test(enabled=false)
	public void takeAssessment() throws InterruptedException {
		for (int i = 2; i < numberOfStudents + 2; i++) {
			driver = multipleStudents.getCurrentStudentsWindow(i);
			driver.get(assessmentLink);
			System.out.println(driver.getTitle());
		}

	}

	public void loginToMyApp(String userName, String password, WebDriver driver) {
		myAppLoginPage = new MyAppLoginPage(driver);
		myAppLoginPage.enterUserEmail(userName);
		myAppLoginPage.enterPassword(password);
		myAppLoginPage.clickLogin();
		myAppHomePage = new MyAppHomePage(driver);
	}

	public void joinSession(WebDriver driver) throws InterruptedException {
		myAppHomePage = new MyAppHomePage(driver);
		myAppHomePage.clickOnClassRoomLearning();
		MyAppSessionPage myAppSessionpage = new MyAppSessionPage(driver);
		myAppSessionpage.clickOnJoinSessionButton();
		myAppSessionpage.enterOtp(otpNumber);
		Thread.sleep(2000);
		myAppSessionpage.clickOnJoinButton();
		Thread.sleep(13000);

	}

}
