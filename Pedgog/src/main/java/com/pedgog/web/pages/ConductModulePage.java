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

	@FindBy(xpath = "//div[@class=\"btn\"]")
	private WebElement nextButton;

	public ConductModulePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnNextButton() {
		clickElement(nextButton);
	}
	
}
