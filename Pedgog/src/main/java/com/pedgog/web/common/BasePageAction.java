package com.pedgog.web.common;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;

import javax.lang.model.element.Element;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageAction {

	WebDriver driver;

	public BasePageAction(WebDriver driver) {
		this.driver = driver;

	}

	protected void waitForElementToBeClickable(WebElement element) {
		new WebDriverWait(driver, 60).withMessage("element is not clickable").ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(element));
	}

	protected void waitForElementToBeVisible(WebElement element) {
		new WebDriverWait(driver, 30).withMessage("element is not visible").ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
	}

	protected void clickElement(WebElement element) {
		waitForElementToBeVisible(element);
		waitForElementToBeClickable(element);
		// ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
		// element);
		element.click();
		System.out.println("I " + Thread.currentThread().getStackTrace()[2].getMethodName());

	}

	protected void scrollToElementAndClick(WebElement element) {
		scrollToElement(element);
		waitForElementToBeClickable(element);
		element.click();

	}

	public void clearElement(WebElement element) {
		new WebDriverWait(driver, 60).withMessage("element is not clickable")
				.until(ExpectedConditions.elementToBeClickable(element));
		element.clear();
	}

	public void enterText(WebElement element, String text) {
		new WebDriverWait(driver, 60).withMessage("element is not visible")
				.until(ExpectedConditions.visibilityOf(element));
		element.clear();
		System.out.println("I " + Thread.currentThread().getStackTrace()[2].getMethodName());
		element.sendKeys(text);
	}

	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	public int getElementCount(List<WebElement> element) {
		int count = 0;
		int i = 0;
		System.out.println("I " + Thread.currentThread().getStackTrace()[2].getMethodName());
		do {

			if (element.size() > 0) {
				count = element.size();
				System.out.println("Elements found");
				break;
			} else {
				try {
					System.out.println("Element not found, waiting for it...");
					Thread.sleep(2000);
				} catch (InterruptedException e1) {

					e1.printStackTrace();
				}
				i++;
			}
		} while (i < 30);

		if (count == 0)
			System.err.println("Element not found ");
		return count;
	}

	public int getElementCountByXpath(String xpath) {
		int count = 0;
		int i = 0;
		System.out.println("I " + Thread.currentThread().getStackTrace()[2].getMethodName());

		do {
			if (driver.findElements(By.xpath(xpath)).size() > 0) {
				count = driver.findElements(By.xpath(xpath)).size();
				System.out.println("Elements found");
				break;
			} else {
				try {
					System.out.println("Elements not found, waiting for it..."+xpath);
					Thread.sleep(2000);
				} catch (InterruptedException e1) {

					e1.printStackTrace();
				}
				i++;
			}
		} while (i < 30);

		if (count == 0)
			System.err.println("Elements not found ");
		return count;
	}

	public boolean isElementVisible(WebElement element) {
		return new WebDriverWait(driver, 60).withMessage("element is not visible")
				.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
	}

	public void isTextOnPage(String pattern) {
		new WebDriverWait(driver, 15).withMessage("body is not located")
				.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
		Document doc = Jsoup.parse(driver.getPageSource());
		assertTrue(doc.text().contains(pattern), pattern + " cannot be found");
	}

	public void isTextNotOnPage(String pattern) {
		new WebDriverWait(driver, 15).withMessage("body is not located")
				.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
		Document doc = Jsoup.parse(driver.getPageSource());
		assertFalse(doc.text().contains(pattern), pattern + " can be found");
	}

	public boolean isTextOnPage(String pattern, int timeout) {
		new WebDriverWait(driver, timeout).withMessage("body is not located")
				.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
		Document doc = Jsoup.parse(driver.getPageSource());
		return doc.text().contains(pattern);
	}

	public String getText(WebElement element) {
		System.out.println("I " + Thread.currentThread().getStackTrace()[2].getMethodName());
		new WebDriverWait(driver, 60).withMessage("element is not present").ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
		return element.getText();
	}
	
	public String getTextByXpath(String xpath) {
		String text=null;
		int i = 0;
		System.out.println("I " + Thread.currentThread().getStackTrace()[2].getMethodName());

		do {
			if (driver.findElement(By.xpath(xpath)).getText().length()>0) {
				text = driver.findElement(By.xpath(xpath)).getText();
				System.out.println("Element found");
				break;
			} else {
				try {
					System.out.println("Element not found, waiting for it..."+xpath);
					Thread.sleep(2000);
				} catch (InterruptedException e1) {

					e1.printStackTrace();
				}
				i++;
			}
		} while (i < 30);

		if (text == null)
			System.err.println("Elements not found ");
		return text;
	}

	public Boolean isElementPresent(String xpath) {
		boolean isPresent = false;
		int i = 0;
		System.out.println("I " + Thread.currentThread().getStackTrace()[2].getMethodName());
		do {
			try {
				driver.findElement(By.xpath(xpath));
				isPresent = true;
			} catch (Exception e) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				i++;
			}

			if (isPresent)
				break;
		} while (i < 30);

		if (!isPresent)
			System.err.println("Element no found -> " + xpath);
		return isPresent;
	}

	public boolean elementHasText(WebElement we, String text) {
		new WebDriverWait(driver, 15).withMessage("element is not visible").until(ExpectedConditions.visibilityOf(we));
		return we.getText().contains(text);
	}

	public void insertKeyPress(WebElement element, Keys key) {
		new WebDriverWait(driver, 15).withMessage("element is not clickable")
				.until(ExpectedConditions.elementToBeClickable(element));
		element.sendKeys(key);
	}

	public void mouseOverElement(WebElement element) {
		Actions actions = new Actions(driver);
		new WebDriverWait(driver, 15).withMessage("element is not visible")
				.until(ExpectedConditions.visibilityOf(element));
		actions.moveToElement(element);
		actions.build().perform();
	}

	public BasePageAction hitKey(Keys key) {
		Actions actions = new Actions(driver);
		actions.sendKeys(key).build().perform();
		return this;
	}
}
