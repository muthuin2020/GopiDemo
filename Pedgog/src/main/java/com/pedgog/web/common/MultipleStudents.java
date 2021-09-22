package com.pedgog.web.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MultipleStudents extends TestBase {

	public void openWindowForStudents(int i) {

		String browser = "ch";
		if (browser.equalsIgnoreCase("ch")) {
			switch (i) {
			case 2:
				driverTwo = new ChromeDriver(options);
				driverTwo.manage().window().maximize();
				driverTwo.get(myAppURL);
				break;

			case 3:
				driverThree = new ChromeDriver(options);
				driverThree.manage().window().maximize();
				driverThree.get(myAppURL);
				break;

			case 4:
				driverFour = new ChromeDriver(options);
				driverFour.manage().window().maximize();
				driverFour.get(myAppURL);
				break;

			case 5:
				driverFive = new ChromeDriver(options);
				driverFive.manage().window().maximize();
				driverFive.get(myAppURL);
				break;

			case 6:
				driverSix = new ChromeDriver(options);
				driverSix.manage().window().maximize();
				driverSix.get(myAppURL);
				break;

			case 7:
				driverSeven = new ChromeDriver(options);
				driverSeven.manage().window().maximize();
				driverSeven.get(myAppURL);
				break;

			case 8:
				driverEight = new ChromeDriver(options);
				driverEight.manage().window().maximize();
				driverEight.get(myAppURL);
				break;

			case 9:
				driverNine = new ChromeDriver(options);
				driverNine.manage().window().maximize();
				driverNine.get(myAppURL);
				break;

			case 10:
				driverTen = new ChromeDriver(options);
				driverTen.manage().window().maximize();
				driverTen.get(myAppURL);
				break;

			case 11:
				driverEleven = new ChromeDriver(options);
				driverEleven.manage().window().maximize();
				driverEleven.get(myAppURL);
				break;

			case 12:
				driverTwelve = new ChromeDriver(options);
				driverTwelve.manage().window().maximize();
				driverTwelve.get(myAppURL);
				break;

			case 13:
				driverThirteen = new ChromeDriver(options);
				driverThirteen.manage().window().maximize();
				driverThirteen.get(myAppURL);
				break;

			case 14:
				driverFourteen = new ChromeDriver(options);
				driverFourteen.manage().window().maximize();
				driverFourteen.get(myAppURL);
				break;

			case 15:
				driverFive = new ChromeDriver(options);
				driverFive.manage().window().maximize();
				driverFive.get(myAppURL);
				break;

			case 16:
				driverSix = new ChromeDriver(options);
				driverSix.manage().window().maximize();
				driverSix.get(myAppURL);
				break;

			case 17:
				driverSeven = new ChromeDriver(options);
				driverSeven.manage().window().maximize();
				driverSeven.get(myAppURL);
				break;

			case 18:
				driverEight = new ChromeDriver(options);
				driverEight.manage().window().maximize();
				driverEight.get(myAppURL);
				break;

			case 19:
				driverNineteen = new ChromeDriver(options);
				driverNineteen.manage().window().maximize();
				driverNineteen.get(myAppURL);
				break;
			case 20:
				driverTwenty = new ChromeDriver(options);
				driverTwenty.manage().window().maximize();
				driverTwenty.get(myAppURL);
				break;
			default:
				break;
			}
		}
		if (browser.equalsIgnoreCase("ff")) {
			switch (i) {
			case 2:
				driverTwo = new FirefoxDriver();
				driverTwo.manage().window().maximize();
				driverTwo.get(myAppURL);
				break;

			case 3:
				driverThree = new FirefoxDriver();
				driverThree.manage().window().maximize();
				driverThree.get(myAppURL);
				break;

			case 4:
				driverFour = new FirefoxDriver();
				driverFour.manage().window().maximize();
				driverFour.get(myAppURL);
				break;

			case 5:
				driverFive = new FirefoxDriver();
				driverFive.manage().window().maximize();
				driverFive.get(myAppURL);
				break;

			case 6:
				driverSix = new FirefoxDriver();
				driverSix.manage().window().maximize();
				driverSix.get(myAppURL);
				break;

			case 7:
				driverSeven = new FirefoxDriver();
				driverSeven.manage().window().maximize();
				driverSeven.get(myAppURL);
				break;

			case 8:
				driverEight = new FirefoxDriver();
				driverEight.manage().window().maximize();
				driverEight.get(myAppURL);
				break;

			case 9:
				driverNine = new FirefoxDriver();
				driverNine.manage().window().maximize();
				driverNine.get(myAppURL);
				break;

			case 10:
				driverTen = new FirefoxDriver();
				driverTen.manage().window().maximize();
				driverTen.get(myAppURL);
				break;

			case 11:
				driverEleven = new FirefoxDriver();
				driverEleven.manage().window().maximize();
				driverEleven.get(myAppURL);
				break;

			case 12:
				driverTwelve = new FirefoxDriver();
				driverTwelve.manage().window().maximize();
				driverTwelve.get(myAppURL);
				break;

			case 13:
				driverThirteen = new FirefoxDriver();
				driverThirteen.manage().window().maximize();
				driverThirteen.get(myAppURL);
				break;

			case 14:
				driverFourteen = new FirefoxDriver();
				driverFourteen.manage().window().maximize();
				driverFourteen.get(myAppURL);
				break;

			case 15:
				driverFive = new FirefoxDriver();
				driverFive.manage().window().maximize();
				driverFive.get(myAppURL);
				break;

			case 16:
				driverSix = new FirefoxDriver();
				driverSix.manage().window().maximize();
				driverSix.get(myAppURL);
				break;

			case 17:
				driverSeven = new FirefoxDriver();
				driverSeven.manage().window().maximize();
				driverSeven.get(myAppURL);
				break;

			case 18:
				driverEight = new FirefoxDriver();
				driverEight.manage().window().maximize();
				driverEight.get(myAppURL);
				break;

			case 19:
				driverNineteen = new FirefoxDriver();
				driverNineteen.manage().window().maximize();
				driverNineteen.get(myAppURL);
				break;
			case 20:
				driverTwenty = new FirefoxDriver();
				driverTwenty.manage().window().maximize();
				driverTwenty.get(myAppURL);
				break;
			default:
				break;
			}
		}

	}

	public WebDriver getCurrentStudentsWindow(int i) {

		switch (i) {
		case 2:
			return driverTwo;
		case 3:
			return driverThree;

		case 4:
			return driverFour;

		case 5:
			return driverFive;

		case 6:
			return driverSix;

		case 7:
			return driverSeven;

		case 8:
			return driverEight;

		case 9:
			return driverNine;
		case 10:
			return driverTen;

		case 11:
			return driverEleven;

		case 12:
			return driverTwelve;

		case 13:
			return driverThirteen;

		case 14:
			return driverFourteen;

		case 15:
			return driverFifteen;

		case 16:
			return driverSixteen;

		case 17:
			return driverSeventeen;

		case 18:
			return driverEighteen;

		case 19:
			return driverNineteen;
		case 20:
			return driverTwenty;
		default:
			return null;
		}
	}

	public void closeStudentsWindow(int i) {

		switch (i) {
		case 2:
			driverTwo.quit();
			break;
		case 3:
			driverTwo.quit();
			break;

		case 4:
			driverFour.quit();
			break;

		case 5:
			driverFive.quit();
			break;

		case 6:
			driverSix.quit();
			break;

		case 7:
			driverSeven.quit();
			break;

		case 8:
			driverEight.quit();
			break;

		case 9:
			driverNine.quit();
			break;
		case 10:
			driverTen.quit();
			break;
		case 11:
			driverEleven.quit();
			break;

		case 12:
			driverTwelve.quit();
			break;

		case 13:
			driverThirteen.quit();
			break;

		case 14:
			driverFourteen.quit();
			break;

		case 15:
			driverFifteen.quit();
			break;

		case 16:
			driverSixteen.quit();
			break;

		case 17:
			driverSeventeen.quit();
			break;

		case 18:
			driverEighteen.quit();
			break;

		case 19:
			driverNineteen.quit();
			break;
		case 20:
			driverTwenty.quit();
			break;
		default:
			break;
		}
	}
}
