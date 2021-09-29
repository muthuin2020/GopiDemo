package com.coaching.myApp.web.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pedgog.web.common.BasePageAction;

public class CoachingAppHomePage extends BasePageAction {

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

	@FindBy(xpath = "//div[@class=\"ap-conduct-popup-title\"]")
	WebElement conductingModuleTitle;

	@FindBy(xpath = "//span[text()=\"copy link \"]")
	WebElement otpLinkCopyButton;

	@FindBy(xpath = "//span[text()=\"copy link \"]/following-sibling::div[1]")
	WebElement otpLink;

	@FindBy(xpath = "//span[@class=\"otp-text\"]/following-sibling::span")
	WebElement otpNumber;

	@FindBy(xpath = "//span[text()=\"Begin Session\"]")
	WebElement beginSession;

	@FindBy(xpath = "//div[contains(@class, 'ap-conduct-dialog-participant')]")
	WebElement totalParticipants;

	@FindBy(xpath = "//div[contains(@class, 'ap-conduct-dialog-participant')]")
	List<WebElement> participantNames;

	@FindBy(xpath = "//div[text()=\"CANCEL SESSION\"]")
	List<WebElement> cancelSession;

	public CoachingAppHomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnBeginSession() {
		clickElement(beginSession);
	}

	public String getHomePageTtile() {
		return getText(homePageTtile);
	}

	public String getConductingModuleTitle() {
		return getText(conductingModuleTitle);
	}

	public String getOtpLink() {
		return getText(otpLink);
	}

	public String getOtpNumber() {
		return getText(otpNumber);
	}

	public List<String> getParticipantNames() {
		List<String> participantList = new ArrayList<String>();
		for (int i = 1; i < participantNames.size(); i++) {
			participantList.add(participantNames.get(i).getText());
			System.out.println("Participant " + i + " : " + participantNames.get(i).getText());
		}
		return participantList;
	}

	public int getTotalParticipants() {
		String partipantsText = getText(totalParticipants);	
		System.out.println("total participants : " + partipantsText);
		return Integer.parseInt(partipantsText.split(" ")[0]);
	}

	public int getNumberOfModules() {
		return getElementCount(numberOfModules);
	}

	public void logout() {
		clickElement(logout);
	}

	public void clickOnConduct(String moduleName) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement moduleToSelect = null;
		for (int i = 0; i < moduleTitles.size(); i++) {
			if (moduleTitles.get(i).getText().equals(moduleName)) {
				moduleToSelect = availableModules.get(i);
				break;
			}
		}
		act = new Actions(driver);
		scrollToElement(moduleToSelect);
		act.moveToElement(moduleToSelect).build().perform();
		act.moveByOffset(0, 50).build().perform();
		act.click().build().perform();

	}

	public void waitForSomeTime(int seconds) {
		implicitWait(driver, seconds * 1000);
	}

}
