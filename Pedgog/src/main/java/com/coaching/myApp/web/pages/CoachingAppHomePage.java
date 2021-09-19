package com.coaching.myApp.web.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pedgog.web.common.BasePageAction;

public class CoachingAppHomePage extends BasePageAction{

	WebDriver driver;
	Actions act;

	@FindBy(xpath = "//label[text()=\"Logout\"]")
	WebElement logout;

	@FindBy(xpath = "//div[@class=\"ap-program-username\"]")
	WebElement homePageTtile;

	@FindBy(xpath = "//div[@class=\"d-flex w-45 h-60\"]")
	List<WebElement> numberOfModules;
	
	@FindBy(xpath = "//div[@class=\"ap-module-card-title\"]")
	List<WebElement> moduleTitles;
	
	@FindBy(xpath = "//div[@class=\"ap-module-card-img\"]")
	List<WebElement> availableModules;
	
	public CoachingAppHomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	public String getHomePageTtile() {
		return getText(homePageTtile);
	}

	public int getNumberOfModules() {
		return getElementCount(numberOfModules);
	}

	
	public void logout() {
		clickElement(logout);
	}
	
	public void clickOnConduct(String moduleName)
	{
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement moduleToSelect=null;
		for(int i=0; i<moduleTitles.size();i++)
		{
			if(moduleTitles.get(i).getText().equals(moduleName))
			{
				moduleToSelect=availableModules.get(i);
				break;
			}
		}
		act=new Actions(driver);
		act.moveToElement(moduleToSelect).build().perform();
		act.moveByOffset(0, 50).build().perform();
		act.click().build().perform();
		
	}

}
