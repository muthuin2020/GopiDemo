package com.coaching.myApp.web.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pedgog.web.common.BasePageAction;

public class CoachingAppConductPage extends BasePageAction {

	WebDriver driver;
	Actions act;

	@FindBy(xpath = "//div[@class=\"btn\"]")
	WebElement previousSlideButton;

	@FindBy(xpath = "//div[contains(@class,\"btn\")]/following-sibling::div[not(contains(@class,'btn-inactive'))]")
	WebElement nextSlideButton;

	@FindBy(xpath = "//label[text()=\"Next Section\"]")
	WebElement nextSectionButton;

	@FindBy(xpath = "//div[text()=\"Next Unit\"]")
	WebElement nextUnitButton;

	@FindBy(xpath = "//span[text()=\"copy link \"]")
	WebElement copyAssessmentLink;

	@FindBy(xpath = "//span[text()=\"Link copied \"]/following-sibling::div")
	WebElement assessmentLink;

	String assLink = "//span[text()=\"copy link \"]";

	public CoachingAppConductPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnPreviousSlideButton() {
		clickElement(previousSlideButton);
	}

	public void clickOnNextSlideButton() {
		clickElement(nextSlideButton);
	}

	public void clickOnNextSectionButton() {
		clickElement(nextSectionButton);
	}

	public void clickOnNextUnitButton() {
		clickElement(nextUnitButton);
	}

	public String getAssessmentLink()
	{
		return getText(assessmentLink);
	}
	
	public void clickOnCopyAssessmentLink()
	{
		clickElement(copyAssessmentLink);
	}
	
	public void gotoAssessmentLink() throws InterruptedException {
		for (int i = 0; i < 100; i++) {
			try {
				clickOnNextSlideButton();
			} catch (Exception e) {
				try {
					clickOnNextUnitButton();
				} catch (Exception err) {
					clickOnNextSectionButton();
				}
			}
			Thread.sleep(1000);
			if(isElementPresentWithoutWait(assLink))
				break;
		}

	}

}
