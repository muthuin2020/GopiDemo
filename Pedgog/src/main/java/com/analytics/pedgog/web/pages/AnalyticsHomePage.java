package com.analytics.pedgog.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pedgog.web.common.BasePageAction;

public class AnalyticsHomePage extends BasePageAction {

	WebDriver driver;

	@FindBy(xpath = "//a[@href=\"/onboarding/facilitators\"]")
	WebElement facilitatorsOnboarding;

	@FindBy(xpath = "//a[@href=\"/onboarding/students\"]")
	WebElement studentsOnboarding;

	public AnalyticsHomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void goToFacilitatorsOnboarding() {
		clickElement(facilitatorsOnboarding);
	}

	public void goToStudentsOnboarding() {
		clickElement(studentsOnboarding);
	}

}
