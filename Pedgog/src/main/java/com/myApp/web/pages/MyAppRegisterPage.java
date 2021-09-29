package com.myApp.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pedgog.web.common.BasePageAction;

public class MyAppRegisterPage extends BasePageAction {

	@FindBy(xpath = "//input[@type=\"email\"]")
	WebElement email;

	@FindBy(xpath = "//input[@type=\"password\"]")
	WebElement password;

	@FindBy(xpath = "//label[text()=\"Password\"]/parent::div//button")
	WebElement showPassword;

	@FindBy(xpath = "//input[@type=\"text\"]")
	WebElement accessCode;
	
	@FindBy(xpath = "//label[text()=\"Full Name\"]/parent::div//input")
	WebElement fullName;
	
	@FindBy(xpath = "//*[text()=\"Register\"]")
	WebElement register;
	
	@FindBy(xpath = "//a[text()=\"Login\"]")
	WebElement login;
	
	@FindBy(xpath = "//div[@class=\"errorMsg\"]")
	WebElement registerErrorMsg;

	WebDriver driver;
	
	public MyAppRegisterPage(WebDriver driver) {
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


	public void enterAccessCode(String aCode) {
		enterText(accessCode, aCode);
	}
	

	public void enterFullName(String fName) {
		enterText(fullName, fName);
	}
	

	public void clickRegister() {
		scrollToElementAndClick(register);
	}


	public void clickLogin() {
		clickElement(login);
	}
	
	public String getRegistrationErrorMessage()
	{
		return getText(registerErrorMsg);
	}
	
}