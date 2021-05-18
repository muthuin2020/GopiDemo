package com.pedgog.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

	private Properties prop;
	private String propertyFilePath;

	public ConfigFileReader() {
		String testClassQualifiedName = new Exception().getStackTrace()[1].getClassName();
		System.out.println("Class name : " + testClassQualifiedName);
		String testClassName = testClassQualifiedName.split("tests")[1].substring(1);

		System.out.println(
				"--------------------------------- Running Test : " + testClassName + " -------------------------");

		propertyFilePath = System.getProperty("user.dir") + "\\config\\" + testClassName + ".properties";
		System.out.println("config file path : " + propertyFilePath);

		FileInputStream fileInput;
		if (new File(propertyFilePath).exists())
			try {
				fileInput = new FileInputStream(new File(propertyFilePath));
				prop = new Properties();
				try {
					prop.load(fileInput);
					fileInput.close();

				} catch (IOException e) {
					System.err.println("problem loading config file");
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();

				throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
			}
		else
			System.out.println("Config file is not present fo this Test....");

	}

	public Properties getConfig() {
		return prop;
	}
}
