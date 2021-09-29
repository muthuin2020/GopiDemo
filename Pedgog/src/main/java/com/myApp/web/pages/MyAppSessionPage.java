package com.myApp.web.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pedgog.web.common.BasePageAction;

public class MyAppSessionPage extends BasePageAction {

	WebDriver driver;

	@FindBy(xpath = "//div[@class=\"session-button\"]")
	WebElement logout;

	@FindBy(xpath = "//button[@type=\"button\"]")
	WebElement joinSessionButton;

	@FindBy(xpath = "//span[text()=\"ENTER OTP\"]/parent::div//button")
	WebElement joinButton;

	String otpInputBox = "//div[@class=\"otp\"]";

	@FindBy(xpath = "//div[contains(text(),\"Hi \")]")
	WebElement loggedInStudent;

	@FindBy(xpath = "//span[text()=\"How to become a StarMaker Associate?\"]/ancestor::tr[1]//td[3]//span[1]")
	WebElement assessmentMark;

	public MyAppSessionPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnLogout() {
		clickElement(logout);
	}

	public void clickOnJoinSessionButton() {
		clickElement(joinSessionButton);
	}

	public void clickOnJoinButton() {
		clickElement(joinButton);
	}

	public void enterOtp(String otp) {
		char[] otpNumbers = otp.toCharArray();
		for (int i = 1; i < 5; i++) {
			enterText(driver.findElement(By.xpath(otpInputBox + "[" + i + "]//input")),
					Character.toString(otpNumbers[i - 1]));
		}
	}

	public String getLoggedInStudentName() {
		return getText(loggedInStudent).substring(3);
	}

	public int getAssessmentMark() {
		return Integer.parseInt(getText(assessmentMark));
	}
}
