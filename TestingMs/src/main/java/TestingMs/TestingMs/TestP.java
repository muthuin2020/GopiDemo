package TestingMs.TestingMs;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestP extends TestBase{

	@Test(priority = 1)
	void logoutTest() throws InterruptedException {
		System.out.println("Inside LogoutTest");

		Thread.sleep(5000);
		String logoutLoc = "//div[@class=\"last-div\"]//a[@href=\"/auth/logout\"]";
		String loginLoc = "//input[@id=\"user-email\"]";

		driver.findElement(By.xpath(logoutLoc)).click();

		if (driver.findElements(By.xpath(loginLoc)).size() > 0)
			System.out.println("Logout Test Passed");
		else
			System.out.println("Logout Test Failed");
	}
	@Test(priority = 0)
	void loginTest() throws InterruptedException {

		System.out.println("Inside LoginTest");
		
		String emailId = "ibecomedemo@illumine.in";
		String pwd = "illumine123";
		String loginLoc = "//input[@id=\"user-email\"]";
		String pwdLoc = "//input[@id=\"user-password\"]";
		String submitLoc = "//span[text()=\"Login\"]";
		String firtsPage = "//*[text()=\"Explore Zone\"]";

		driver.findElement(By.xpath(loginLoc)).sendKeys(emailId);
		driver.findElement(By.xpath(pwdLoc)).sendKeys(pwd);
		driver.findElement(By.xpath(submitLoc)).click();
		Thread.sleep(5000);
		if (driver.findElements(By.xpath(firtsPage)).size() > 0)
			System.out.println("Login Test Passed");
		else
			System.out.println("Login Test Failed");

	}

	@Test(priority = 2)
	void loginAgain() throws InterruptedException {

		System.out.println("Inside LoginAgain");
		Thread.sleep(5000);
		String emailId = "ibecomedemo@illumine.in";
		String pwd = "illumine123";
		String loginLoc = "//input[@id=\"user-email\"]";
		String pwdLoc = "//input[@id=\"user-password\"]";
		String submitLoc = "//span[text()=\"Login\"]";
		String firtsPage = "//*[text()=\"Explore Zone\"]";

		driver.findElement(By.xpath(loginLoc)).sendKeys(emailId);
		driver.findElement(By.xpath(pwdLoc)).sendKeys(pwd);
		driver.findElement(By.xpath(submitLoc)).click();
		Thread.sleep(5000);
		System.out.println("Page Title is : " + driver.findElement(By.xpath(firtsPage)).getText());

	}
}
