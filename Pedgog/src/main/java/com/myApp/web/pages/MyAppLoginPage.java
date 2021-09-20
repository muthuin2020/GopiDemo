package com.myApp.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pedgog.web.common.BasePageAction;

public class MyAppLoginPage extends BasePageAction {

	@FindBy(xpath = "//label[text()=\"Email\"]/parent::div//input")
	WebElement email;

	@FindBy(xpath = "//label[text()=\"Password\"]/parent::div//input")
	WebElement password;

	@FindBy(xpath = "//label[text()=\"Password\"]/parent::div//button")
	WebElement showPassword;
	
	@FindBy(xpath = "//span[text()=\"Login\"]")
	WebElement login;

	@FindBy(xpath = "//div[@class=\"errorMsg\"]")
	WebElement loginErrorMsg;
	
	@FindBy(xpath = "//*[text()=\"Register\"]")
	WebElement register;


	WebDriver driver;
	
	public MyAppLoginPage(WebDriver driver) {
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


	public String getLoginErrorMsg() {
		return getText(loginErrorMsg);
	}

}