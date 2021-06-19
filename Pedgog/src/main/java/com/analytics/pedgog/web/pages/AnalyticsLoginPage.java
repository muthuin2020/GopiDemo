package com.analytics.pedgog.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pedgog.web.common.BasePageAction;

public class AnalyticsLoginPage extends BasePageAction {

	@FindBy(xpath = "//input[@name=\"email\"]")
	WebElement email;

	@FindBy(xpath = "//input[@name=\"password\"]")
	WebElement password;

	@FindBy(xpath = "//button[@type=\"submit\"]")
	WebElement login;

	@FindBy(xpath = "//*[text()=\"Register\"]")
	WebElement register;
	


	WebDriver driver;
	
	public AnalyticsLoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterUserEmail(String userEmail) {
		enterText(email, userEmail);

	}

	public void enterPassword(String pwd) {
		enterText(password, pwd);
	}

	public void clickLogin() {
		clickElement(login);
	}
	

	public void clickRegister() {
		scrollToElementAndClick(register);
	}


}