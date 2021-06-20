package com.analytics.pedgog.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pedgog.web.common.BasePageAction;

public class AnalyticsFacilatorsPage extends BasePageAction{

	WebDriver driver;
	
	@FindBy(xpath = "//span[text()=\"New Faculty Registrations\"]/parent::div/following-sibling::div//span")
	WebElement newFacultyRegistrations;
	
	
	@FindBy(xpath = "//span[text()=\"Cummulative Faculty Registrations\"]/parent::div/following-sibling::div//span")
	WebElement cummulativeFacultyRegistrations;
	
	@FindBy(xpath = "//span[text()=\"Total Faculty Registrations\"]/parent::div/following-sibling::div//span")
	WebElement totalFacultyRegistrations;
	
	@FindBy(xpath = "//div[@class=\"filter-wrapper\"]")
	WebElement registrationsGraphFilter;
	
	@FindBy(xpath = "//ul[@role=\"listbox\"]/li[1]")
	WebElement filterLastMonth;
	
	@FindBy(xpath = "//ul[@role=\"listbox\"]/li[2]")
	WebElement filterLastThreeMonths;
	
	@FindBy(xpath = "//ul[@role=\"listbox\"]/li[3]")
	WebElement filterLastYear;
	
	public AnalyticsFacilatorsPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	public int getNewFacultyRegistrations()
	{
		scrollToElement(newFacultyRegistrations);
		return Integer.parseInt(getText(newFacultyRegistrations));
	}
	
	public int getCummulativeFacultyRegistrations()
	{
		scrollToElement(cummulativeFacultyRegistrations);
		return Integer.parseInt(getText(cummulativeFacultyRegistrations));
	}
	public int getTotalFacultyRegistrations()
	{
		scrollToElement(totalFacultyRegistrations);
		return Integer.parseInt(getText(totalFacultyRegistrations));
	}
	
	public void filterLastMonth()
	{
		scrollToElementAndClick(registrationsGraphFilter);
		clickElement(filterLastMonth);
	}
	
	public void filterLastThreetMonth()
	{
		scrollToElementAndClick(registrationsGraphFilter);
		clickElement(filterLastThreeMonths);
	}
	
	public void filterLastYear()
	{
		scrollToElementAndClick(registrationsGraphFilter);
		clickElement(filterLastYear);
	}
}
