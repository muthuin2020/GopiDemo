package TestingMs.TestingMs;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestP2 extends TestBase {

	@Test(enabled = true)
	void pageTitle() throws InterruptedException {
		System.out.println("variable : "+s1);
		System.out.println("inside page title");
		System.out.println("page title : "+driver.getTitle());
	}

}
