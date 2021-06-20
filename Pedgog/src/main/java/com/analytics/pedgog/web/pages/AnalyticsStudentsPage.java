package com.analytics.pedgog.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pedgog.web.common.BasePageAction;

public class AnalyticsStudentsPage extends BasePageAction{

	WebDriver driver;
	
	@FindBy(xpath = "//span[text()=\"New Student Registrations\"]/parent::div/following-sibling::div//span")
	WebElement newStudentsRegistrations;
	
	@FindBy(xpath = "//span[text()=\"Cummulative Student Registrations\"]/parent::div/following-sibling::div//span")
	WebElement cummulativeStudentsRegistrations;
	
	@FindBy(xpath = "//span[text()=\"Total Student Registrations\"]/parent::div/following-sibling::div//span")
	WebElement totalStudentsRegistrations;
	
	@FindBy(xpath = "//div[@class=\"filter-wrapper\"]")
	WebElement registrationsGraphFilter;
	
	@FindBy(xpath = "//ul[@role=\"listbox\"]/li[1]")
	WebElement filterLastMonth;
	
	@FindBy(xpath = "//ul[@role=\"listbox\"]/li[2]")
	WebElement filterLastThreeMonths;
	
	@FindBy(xpath = "//ul[@role=\"listbox\"]/li[3]")
	WebElement filterLastYear;
	
	public AnalyticsStudentsPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	public int getNewStudentsRegistrations()
	{
		scrollToElement(newStudentsRegistrations);
		return Integer.parseInt(getText(newStudentsRegistrations));
	}
	
	public int getCummulativeStudentsRegistrations()
	{
		scrollToElement(cummulativeStudentsRegistrations);
		return Integer.parseInt(getText(cummulativeStudentsRegistrations));
	}
	public int getTotalStudentsRegistrations()
	{
		scrollToElement(totalStudentsRegistrations);
		return Integer.parseInt(getText(totalStudentsRegistrations));
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
