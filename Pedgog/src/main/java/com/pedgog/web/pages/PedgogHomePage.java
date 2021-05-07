package com.pedgog.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pedgog.web.common.BasePageAction;

public class PedgogHomePage extends BasePageAction{

	WebDriver driver;

	@FindBy(xpath = "//a[@href=\"/application/explore\"]")
	WebElement explore;

	@FindBy(xpath = "//a[@href=\"/application/prepare\"]")
	WebElement prepare;

	@FindBy(xpath = "//a[@href=\"/application/conduct\"]")
	WebElement conduct;

	@FindBy(xpath = "//a[@href=\"/application/Projects\"]")
	WebElement project;

	@FindBy(xpath = "//div[@class=\"last-div\"]//*[@title=\"Play Tutorial\"]")
	WebElement help;

	@FindBy(xpath = "//div[@class=\"last-div\"]//a[@href=\"/auth/logout\"]")
	WebElement logout;

	@FindBy(xpath = "//*[text()=\"Explore Zone\"]")
	WebElement homePageTtile;

	public PedgogHomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getHomePageTtile() {
		return getText(homePageTtile);
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

	public void gotoProjects() {
		clickElement(project);
	}

	public void gotoHelp() {
		clickElement(help);
	}

	public void logout() {
		clickElement(logout);
	}

}
