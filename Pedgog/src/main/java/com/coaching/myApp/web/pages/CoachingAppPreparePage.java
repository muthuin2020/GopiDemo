package com.coaching.myApp.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pedgog.web.common.BasePageAction;

public class CoachingAppPreparePage extends BasePageAction{

	WebDriver driver;

	@FindBy(xpath = "//a[@href=\"/application/explore\"]")
	WebElement explore;

	@FindBy(xpath = "//a[@href=\"/application/prepare\"]")
	WebElement prepare;

	@FindBy(xpath = "//a[@href=\"/application/conduct\"]")
	WebElement conduct;

	@FindBy(xpath = "//a[@href=\"/application/Projects\"]")
	WebElement project;

	@FindBy(xpath = "//div[@class=\"last-div\"]//*[text()=\"Help\"]")
	WebElement help;

	@FindBy(xpath = "//div[@class=\"last-div\"]//a[@href=\"/auth/logout\"]")
	WebElement logout;

	@FindBy(xpath = "//h6[text()=\"Prepare Zone\"]")
	WebElement preparePageTtile;

	public CoachingAppPreparePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getPreparePageTtile() {
		return getText(preparePageTtile);
	}

	public void gotoExplore() {
		clickElement(explore);
	}

	public void gotoPrepare() {
		clickElement(prepare);
	}

	public void gotoConduct() {
		clickElement(conduct);
	}

	public void gotoProject() {
		clickElement(project);
	}

	public void gotoHelp() {
		clickElement(help);
	}

	public void logout() {
		clickElement(logout);
	}

}
