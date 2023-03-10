package com.coaching.pedgog.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pedgog.web.common.BasePageAction;

public class PedgogRegisterPage extends BasePageAction {

	@FindBy(xpath = "//input[@id=\"fullname\"]")
	WebElement fullname;

	@FindBy(xpath = "//input[@id=\"user-email\"]")
	WebElement email;

	@FindBy(xpath = "//input[@id=\"user-password\"]")
	WebElement password;

	@FindBy(xpath = "//input[@id=\"accesscode\"]")
	WebElement accessCode;

	@FindBy(xpath = "//*[text()=\"Register\"]")
	WebElement register;

	@FindBy(xpath = "//*[text()=\"Login\"]")
	WebElement login;

	@FindBy(xpath = "//div[@class=\"rightBoxLogin\"]//h4")
	WebElement loginPageTitle;

	@FindBy(xpath = "//div[@class=\"errorMsg\"]//label[text()=\"Incorrect email or password\"]")
	WebElement loginErrorMsg;
	
	

	WebDriver driver;

	public PedgogRegisterPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterUserName(String name) {
		enterText(fullname, name);

	}

	public void enterUserEmail(String userEmail) {
		enterText(email, userEmail);

	}

	public void enterPassword(String pwd) {
		enterText(password, pwd);
	}

	public void enterAccessCode(String code) {
		enterText(accessCode, code);
	}
	
	public void clickRegister() {
		clickElement(register);
	}
	
	public void clickLogin() {
		clickElement(login);
	}

	public String getLoginPageTitle() {
		return loginPageTitle.getText();
	}

	public String getLoginErrorMsg() {
		return getText(loginErrorMsg);
	}

}