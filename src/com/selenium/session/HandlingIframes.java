package com.selenium.session;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandlingIframes {
	static WebDriver driver;
	static WebDriverWait wait;
	static Logger log = Logger.getLogger("devpinoyLogger");

	public static void main(String[] args) throws InterruptedException {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 60);
		driver.get("https://www.w3schools.com/code/tryit.asp?filename=FNXN09W4KXHX");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		By runBtn = By.xpath("//button[contains(text(),'Run')]");
		By iframeR = By.xpath("//iframe[@id='iframeResult']");
		driver.findElement(runBtn).click();
		Thread.sleep(2000);
		
		driver.switchTo().frame("iframeResult");//By Id Or Name
		driver.findElement(By.xpath("//input[@id='fname']")).sendKeys("Pawan");
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		
		Thread.sleep(2000);
		driver.switchTo().frame(driver.findElement(iframeR));
		driver.findElement(By.xpath("//input[@id='fname']")).sendKeys("Pawan Awasthi");
	}
	
}
