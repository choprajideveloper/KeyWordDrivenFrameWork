package com.Engine;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.Base.Bases;

public class KeywordEngine {

	public WebDriver driver;
	public Properties prop;
	public static Workbook book;
	public static Sheet sheet;
	public Bases base;
	public WebElement element;

	public final String Scenrio_Sheet_Path = "D:\\Automation_Java\\KeyDataDrivenExample\\src\\com\\scenrio\\file.xlsx";

	public void StartExecution(String SheetName) throws IOException {

		FileInputStream file = null;
		try {
			file = new FileInputStream(Scenrio_Sheet_Path);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		try {
			book = WorkbookFactory.create(file);
		} catch (EncryptedDocumentException | IOException e) {

			e.printStackTrace();
		}

		sheet = book.getSheet(SheetName);

		int k = 0;
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			try {
				String locatorType = sheet.getRow(i + 1).getCell(k + 1).toString().trim();
				String locatorValue = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
				String action = sheet.getRow(i + 1).getCell(k + 3).toString().trim();
				String value = sheet.getRow(i + 1).getCell(k + 4).toString().trim();

				switch (action) {
				case "open browser":
					base = new Bases();
					prop = base.init_Properties();
					if (value.isEmpty() || value.equals("NA")) {
						driver = base.init_Driver(prop.getProperty("browser"));
					} else {
						driver = base.init_Driver(value);
					}

					break;

				case "enter url":
					if (value.isEmpty() || value.equals("NA")) {
						driver.get(prop.getProperty("url"));
					} else {
						driver.get(value);
					}
					break;

				case "quit":

					driver.quit();
					break;

				default:
					break;
				}

				switch (locatorType) {
				case "id":
					element = driver.findElement(By.id(locatorValue));
					if (action.equalsIgnoreCase("sendKeys")) {
						element.sendKeys(value);
						element.clear();
					} else if (action.equalsIgnoreCase("click")) {
						element.click();
					} else if (action.equalsIgnoreCase("isDisplayed")) {
						element.isDisplayed();
					}
					locatorType = null;
					break;

				case "Name":
					element = driver.findElement(By.id(locatorValue));
					if (action.equalsIgnoreCase("sendKeys")) {
						element.sendKeys(value);
						element.clear();
					} else if (action.equalsIgnoreCase("click")) {
						element.click();
					} else if (action.equalsIgnoreCase("isDisplayed")) {
						element.isDisplayed();
					}
					locatorType = null;
					break;

				case "cssSelector":
					element = driver.findElement(By.cssSelector(locatorValue));
					if (action.equalsIgnoreCase("sendKeys")) {
						element.sendKeys(value);
						element.clear();
					} else if (action.equalsIgnoreCase("click")) {
						element.click();
					} else if (action.equalsIgnoreCase("isDisplayed")) {
						element.isDisplayed();
					}
					locatorType = null;
					break;

				case "xpath":
					element = driver.findElement(By.xpath(locatorValue));
					if (action.equalsIgnoreCase("sendKeys")) {
						element.sendKeys(value);
						element.clear();
					} else if (action.equalsIgnoreCase("click")) {
						element.click();
					} else if (action.equalsIgnoreCase("isDisplayed")) {
						element.isDisplayed();
					}

					locatorType = null;
					break;

				case "className":
					element = driver.findElement(By.className(locatorValue));
					if (action.equalsIgnoreCase("sendKeys")) {
						element.sendKeys(value);
						element.clear();
					} else if (action.equalsIgnoreCase("click")) {
						element.click();
					} else if (action.equalsIgnoreCase("isDisplayed")) {
						element.isDisplayed();
					} else if (action.equalsIgnoreCase("getText")) {
						String elementText = element.getText();
						System.out.println("text from element" + elementText);
					}

					locatorType = null;
					break;

				case "linkText":
					element = driver.findElement(By.linkText(locatorValue));
					System.out.println(element.getText());
					element.click();

					locatorType = null;
					break;

				case "partialLinkText":
					element = driver.findElement(By.partialLinkText(locatorValue));
					System.out.println(element.getText());
					element.click();

					locatorType = null;
					break;
				default:
					break;

				}
			}

			catch (Exception e) {

			}
		}
	}
}
