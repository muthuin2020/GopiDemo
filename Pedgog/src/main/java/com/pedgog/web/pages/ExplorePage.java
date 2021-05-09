package com.pedgog.web.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.pedgog.web.common.BasePageAction;

public class ExplorePage extends BasePageAction {

	WebDriver driver;

	@FindBy(xpath = "//a[@href=\"/application/explore\"]")
	WebElement explore;

	@FindBy(xpath = "//a[@href=\"/application/prepare\"]")
	WebElement prepare;

	@FindBy(xpath = "//a[@href=\"/application/conduct\"]")
	WebElement conduct;

	@FindBy(xpath = "//a[@href=\"/application/Projects\"]")
	WebElement project;

	@FindBy(xpath = "//div[@class=\"last-div\"]//*[text()=\"Help\"]")
	WebElement help;

	@FindBy(xpath = "//div[@class=\"last-div\"]//a[@href=\"/auth/logout\"]")
	WebElement logout;

	@FindBy(xpath = "//*[text()=\"Explore Zone\"]")
	WebElement explorePageTtile;

	@FindBy(xpath = "//h1")
	WebElement explorePageHeader;

	@FindBy(xpath = "//div[@class=\"-headerTitle\"]//p")
	WebElement explorePageSummary;

	@FindBy(xpath = "//section")
	List<WebElement> explorePageModuleSections;

	String moduleSections = "//section";

	@FindBy(xpath = "//section//h3")
	private List<WebElement> moduleSectionTitles;

	@FindBy(xpath = "//section//h3/following-sibling::p")
	private List<WebElement> moduleSectionSummary;

	String modulesPerSection = "//div[@class=\"module-card\"]";
	
	@FindBy(xpath = "//section//div[@class=\"module-card\"]//a[@class=\"button-label\"]")
	private List<WebElement> exploreButton;

	@FindBy(xpath = "//img[@alt=\"Illumine\"]")
	private WebElement exitFromExplore;

	@FindBy(xpath = "//p/following-sibling::h2")
	private WebElement exploreModuleTitle;

	
	int elementCount;

	public ExplorePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	void gotoExplore() {
		clickElement(explore);
	}

	public void gotoPrepare() {
		clickElement(prepare);
	}

	public void gotoConduct() {
		clickElement(conduct);
	}

	public void gotoProject() {
		clickElement(project);
	}

	public void gotoHelp() {
		clickElement(help);
	}

	public void logout() {
		clickElement(logout);
	}

	public String getExplorePageTtile() {
		return getText(explorePageTtile);
	}

	public String getExplorePageHeader() {
		return getText(explorePageHeader);
	}

	public String getExplorePageSummary() {
		return getText(explorePageSummary);
	}

	public int getExplorePageSectionsCount() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getElementCountByXpath(moduleSections);
	}

	public List<String> getExplorePageModuleSectionsTitle() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> titles = new ArrayList<String>();
		for (WebElement ele : moduleSectionTitles) {
			titles.add(getText(ele));
		}
		return titles;
	}

	public List<String> getExplorePageModuleSectionsSummary() {
		List<String> titles = new ArrayList<String>();
		for (WebElement ele : moduleSectionSummary) {
			titles.add(getText(ele));
		}
		return titles;
	}

	public int getListedModulesCountInTheSection(int i) {
		return getElementCountByXpath(moduleSections + "[" + i + "]" + modulesPerSection);
	}

	public String getListedModulesTitle(int i, int j) {
		return getTextByXpath(moduleSections + "[" + i + "]" + modulesPerSection+ "[" + j + "]//h6");
	}
	
	public String getListedModulesSummary(int i, int j) {
		return getTextByXpath(moduleSections + "[" + i + "]" + modulesPerSection+ "[" + j + "]//p");
	}
	
	public void clickOnExploreModule(int i, int j) {
		 clickElementByXpath(moduleSections + "[" + i + "]" + modulesPerSection+ "[" + j + "]//a[@class=\"button-label\"]");
	}
	
	public String getExploringModuleTitle()
	{
		return getText(exploreModuleTitle);
	}
	
	public void exitFromExploringModule()
	{
		clickElement(exitFromExplore);
	}
}
