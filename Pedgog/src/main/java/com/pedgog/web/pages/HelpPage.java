package com.pedgog.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pedgog.web.common.BasePageAction;

public class HelpPage extends BasePageAction {

	WebDriver driver;

	@FindBy(xpath = "//button[@type=\"button\"]//*[text()=\"Play Video\"]")
	WebElement playVideo;

	@FindBy(xpath = "//button[@title=\"Close\"]")
	WebElement close;

	@FindBy(xpath = "//a[@href=\"/application/conduct\"]")
	WebElement conduct;

	@FindBy(xpath = "//a[@href=\"/application/Projects\"]")
	WebElement project;

	@FindBy(xpath = "//div[@class=\"last-div\"]//*[text()=\"Help\"]")
	WebElement help;

	@FindBy(xpath = "//div[@class=\"last-div\"]//a[@href=\"/auth/logout\"]")
	WebElement logout;

	@FindBy(xpath = "//h6[text()=\"Conduct Zone\"]")
	WebElement conductPageTtile;

	public HelpPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void playVideo() {
		clickElement(playVideo);
	}

	public void closeHelpPage() {
		clickElement(close);
	}
	
	public boolean isVideoAvailable() {
		return isElementVisible(playVideo);
	}
	

}
