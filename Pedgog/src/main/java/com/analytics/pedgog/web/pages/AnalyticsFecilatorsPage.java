package com.analytics.pedgog.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pedgog.web.common.BasePageAction;

public class AnalyticsFecilatorsPage extends BasePageAction{

	WebDriver driver;
	@FindBy(xpath = "//span[text()=\"New Faculty Registrations\"]/parent::div/following-sibling::div//span")
	WebElement newFacultyRegistrations;
	
	public AnalyticsFecilatorsPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	public int getNewFacultyRegistrations()
	{
		return Integer.parseInt(getText(newFacultyRegistrations));
	}
	
}
