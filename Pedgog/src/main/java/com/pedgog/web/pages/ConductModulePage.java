package com.pedgog.web.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pedgog.web.common.BasePageAction;

public class ConductModulePage extends BasePageAction {

	WebDriver driver;

	@FindBy(xpath = "//a[@href=\"/application/conduct\"]")
	WebElement conduct;

	@FindBy(xpath = "//h6[text()=\"Conduct Zone\"]")
	WebElement conductPageTtile;

	@FindBy(xpath = "//section//div[@class=\"module-card\"]//div[@class=\"button-label\"]")
	private List<WebElement> conductButton;

	@FindBy(xpath = "//section//div[@class=\"module-card\"]//h6")
	private List<WebElement> conductModuleTitles;

	@FindBy(xpath = "//div//input")
	private WebElement sessionNameField;

	@FindBy(xpath = "//button//*[text()=\"create session\"]")
	private WebElement createSessionButton;

	@FindBy(xpath = "//div[@class=\"btn\"]")
	private WebElement nextButton;

	
	
	public ConductModulePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getConductPageTtile() {
		return getText(conductPageTtile);
	}

	public void clickOnNextButton() {
		clickElement(nextButton);
	}
	
}
