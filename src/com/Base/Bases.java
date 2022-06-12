package com.Base;
// responsible for browser and Properties file

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Bases {

	public WebDriver driver;
	public Properties prop;

	public WebDriver init_Driver(String browserName) {
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./Browser/chromedriver.exe");
			if (prop.getProperty("headless").equals("yes")) {
				// headless mode
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				driver = new ChromeDriver(options);
			}

			else {
				driver = new ChromeDriver();
			}
		}
		return driver;

	}

	public Properties init_Properties() throws IOException {
		prop = new Properties();

		FileInputStream ip = new FileInputStream("D:\\Automation_Java\\KeyDataDrivenExample\\src\\com\\config\\config.properties");
		prop.load(ip);
		return prop;
	}
}
