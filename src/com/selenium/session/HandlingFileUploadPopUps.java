package com.selenium.session;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandlingFileUploadPopUps {
	
	static WebDriver driver;
	static WebDriverWait wait;

	static Logger log = Logger.getLogger("devpinoyLogger");

	public static void main(String[] args) {
		
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 60);

		driver.get("https://www.w3schools.com/code/tryit.asp?filename=FNOSHZZMYCVO");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);

		By runBtn = By.xpath("//button[contains(text(),'Run')]");
		By iframeR = By.xpath("//iframe[@id='iframeResult']");
		By fileUpload = By.xpath("//input[@type='file']");
		
		driver.findElement(runBtn).click();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeR));

		driver.findElement(fileUpload).sendKeys("C:\\MyWorkSpace\\Selenium\\log4j.properties");
		
		driver.close();
		
	}

}
