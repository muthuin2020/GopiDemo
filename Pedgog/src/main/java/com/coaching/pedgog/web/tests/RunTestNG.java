package com.coaching.pedgog.web.tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class RunTestNG {

	public static void main(String[] args) {
		TestNG runner = new TestNG();
		List<String> suitefiles = new ArrayList<String>();
		suitefiles.add(System.getProperty("user.dir") + "\\testng.xml");
		runner.setTestSuites(suitefiles);
		runner.run();
	}

}