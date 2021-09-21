package com.pedgog.web.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MultipleStudents extends TestBase {

	public void openWindowForStudents(int i) {

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

}
