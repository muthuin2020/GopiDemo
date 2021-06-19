package com.coaching.pedgog.web.pages;

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
