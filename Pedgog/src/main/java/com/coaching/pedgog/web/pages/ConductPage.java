package com.coaching.pedgog.web.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pedgog.web.common.BasePageAction;

public class ConductPage extends BasePageAction {

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

	@FindBy(xpath = "//h6[text()=\"Conduct Zone\"]")
	WebElement conductPageTtile;

	@FindBy(xpath = "//h1")
	WebElement pageHeader;

	@FindBy(xpath = "//section")
	List<WebElement> pageModuleSections;

	String moduleSections = "//section";

	@FindBy(xpath = "//section//h3")
	private List<WebElement> moduleSectionTitles;

	String modulesPerSection = "//div[@class=\"module-card\"]";

	@FindBy(xpath = "//section//div[@class=\"module-card\"]//div[@class=\"button-label\"]")
	private List<WebElement> conductButton;

	@FindBy(xpath = "//section//div[@class=\"module-card\"]//h6")
	private List<WebElement> conductModuleTitles;

	@FindBy(xpath = "//div//h5")
	private WebElement createSessionPageTitle;

	@FindBy(xpath = "//div//h5/following-sibling::button")
	private WebElement closeCreateSessionPage;

	@FindBy(xpath = "//div[text()=\"COURSE:\"]/following-sibling::p")
	private WebElement createSessionCourse;

	@FindBy(xpath = "//div[text()=\"TOPIC:\"]/following-sibling::p")
	private WebElement createSessionTopic;

	@FindBy(xpath = "//a/following-sibling::div")
	private WebElement condutingModulePageTitle;

	@FindBy(xpath = "//div//input")
	private WebElement sessionNameField;

	@FindBy(xpath = "//button//*[text()=\"create session\"]")
	private WebElement createSessionButton;

	@FindBy(xpath = "//a//img")
	private WebElement exitFromConducting;

	@FindBy(xpath = "//div[text()=\"Find Module\"]/following-sibling::div")
	private WebElement searchButton;

	@FindBy(xpath = "//input[@id=\"search-field\"]")
	private WebElement searchBar;

	@FindBy(xpath = "//button[@type=\"button\"]//*[text()=\"Close\"]")
	private WebElement closeSearchBar;

	public ConductPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getConductPageTtile() {
		return getText(conductPageTtile);
	}

	public void gotoExplore() {
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

	public String getPageHeader() {
		return getText(pageHeader);
	}

	public int getModuleSectionsCount() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getElementCountByXpath(moduleSections);
	}

	public List<String> getModuleSectionsTitle() {
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

	public List<String> getModuleTitles() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> titles = new ArrayList<String>();
		for (WebElement ele : conductModuleTitles) {
			titles.add(getText(ele));
		}
		return titles;
	}
	
	public int getListedModulesCountInTheSection(int i) {
		return getElementCountByXpath(moduleSections + "[" + i + "]" + modulesPerSection);
	}

	public String getListedModulesTitle(int i, int j) {
		return getTextByXpath(moduleSections + "[" + i + "]" + modulesPerSection + "[" + j + "]//h6");
	}

	public void clickConductOnModule(int i, int j) {
		clickElementByXpath(
				moduleSections + "[" + i + "]" + modulesPerSection + "[" + j + "]//div[@class=\"button-label\"]");
	}

	public String getCreateSessionPageTitle() {
		return getText(createSessionPageTitle);
	}

	public void closeCreateSessionPage() {
		clickElement(closeCreateSessionPage);
	}

	public String getCreateSessionPageTopic() {
		return getText(createSessionTopic);
	}

	public String getCreateSessionPageCourse() {
		return getText(createSessionCourse);
	}

	public String getConductingModuleTitle() {
		return getText(condutingModulePageTitle);
	}

	public void enterSessionName(String sessionName) {
		enterText(sessionNameField, sessionName);
	}

	public void clickSessionCreateButton() {
		clickElement(createSessionButton);
	}

	public void exitFromConducting() {
		clickElement(exitFromConducting);
	}

	public void clickOnSearchButton() {
		clickElement(searchButton);
	}

	public void enterSearchText(String searchText) {
		enterText(searchBar, searchText);
	}

	public void closeSearchBar() {
		clickElement(closeSearchBar);
	}
}
