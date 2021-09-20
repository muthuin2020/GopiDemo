package com.myApp.web.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pedgog.web.common.BasePageAction;

public class MyAppSessionPage extends BasePageAction{

	WebDriver driver;

	@FindBy(xpath = "//div[@class=\"session-button\"]")
	WebElement logout;

	
	public MyAppSessionPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	public void clickOnLogout() {
		clickElement(logout);
	}

}
