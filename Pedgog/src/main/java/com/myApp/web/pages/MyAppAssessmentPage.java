package com.myApp.web.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pedgog.web.common.BasePageAction;

public class MyAppAssessmentPage extends BasePageAction {

	WebDriver driver;

	@FindBy(xpath = "//span[text()=\" Next\"]")
	WebElement firstNextButton;

	@FindBy(xpath = "//span[text()=\"Next\"]")
	WebElement nextButton;

	@FindBy(xpath = "//button[@type=\"button\"]//span[text()=\"Previous\"]")
	WebElement previousButton;

	@FindBy(xpath = "//span[@class=\"assessment-heading\"]")
	WebElement assessmentPageTitle;

	@FindBy(xpath = "//div[@role=\"radiogroup\"]")
	WebElement selectAnswer;

	@FindBy(xpath = "//button[@type=\"button\"]//span[text()=\"Submit\"]")
	WebElement submitButton;

	@FindBy(xpath = "//a[text()=\"Next\"]")
	WebElement afterSubmitNextButton;

	@FindBy(xpath = "//div[@role=\"slider\"]")
	List<WebElement> ratingSliders;

	@FindBy(xpath = "//span[text()=\"Submit\"]")
	WebElement submitRating;

	public MyAppAssessmentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getAssessmentPageTitle() {
		return getText(assessmentPageTitle);
	}

	public void clickOnFirstNext() {
		clickElement(firstNextButton);
	}

	public void clickOnNext() {
		clickElement(nextButton);
	}

	public void clickOnPrevious() {
		clickElement(previousButton);
	}

	public void clickOnSubmit() {
		clickElement(submitButton);
	}

	public void clickOnAfterSubmitNextButton() {
		clickElement(afterSubmitNextButton);
	}

	public void selectAnyAnswer() {
		mouseOverElement(selectAnswer);
		moveByOffsetAndClick(0, (int) Math.floor(Math.random() * (100 + 100 + 1) - 100));
	}

	public void selectRatings() {
		for (WebElement we : ratingSliders) {
			mouseOverElement(we);
			moveByOffsetAndClick((int) Math.floor(Math.random() * (100 - 0 + 1) + 0), 0);
		}
	}

	public void clickOnSubmitRatings() {
		clickElement(submitRating);
	}

}
