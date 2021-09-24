package com.myApp.web.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pedgog.web.common.BasePageAction;

public class MyAppHomePage extends BasePageAction{

	WebDriver driver;

	@FindBy(xpath = "//div[@class=\"card\"][1]")
	WebElement classRoomLearning;

	@FindBy(xpath = "//span[@class=\"card-score\"]")
	WebElement classRoomLearningScore;

	@FindBy(xpath = "//div[text()=\"total score\"]/parent::div/following-sibling::div//span")
	WebElement totalScore;
	
	@FindBy(xpath = "//div[@class=\"card-title\"][1]")
	WebElement homePageTitle;

	
	public MyAppHomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	public void clickOnClassRoomLearning() {
		clickElement(classRoomLearning);
	}
	
	public String getClassRoomLearningScore()
	{
		return getText(classRoomLearningScore);
	}
	
	public String getTotalScore()
	{
		return getText(totalScore);
	}
	
   public String getHomePageTitle()
   {
	   return getText(homePageTitle);
   }
}
