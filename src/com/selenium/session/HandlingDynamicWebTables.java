package com.selenium.session;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandlingDynamicWebTables {

	public static void main(String[] args) {

		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

		driver.get("http://freecrm.com/");

		By username = By.xpath("//input[@name='username']");
		By password = By.xpath("//input[@name='password']");
		By loginBtn = By.xpath("//input[@value='Login']");
		By contactLink = By.xpath("//a[contains(text(),'Contacts')]");
		By rowLocator = By.xpath("//form[@id='vContactsForm']/table/tbody/tr");
		By colsLocator = By.xpath("//form[@id='vContactsForm']/table/tbody/tr[4]/td");
		By iFrameMainPanel = By.xpath("//frame[@name='mainpanel']");

		driver.findElement(username).sendKeys("pawanawasthi83");
		driver.findElement(password).sendKeys("Vivalv@1983");

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
		driver.findElement(loginBtn).click();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iFrameMainPanel));

		driver.findElement(contactLink).click();

		int rows = driver.findElements(rowLocator).size();

		int cols = driver.findElements(colsLocator).size();

		System.out.println("Number Of Rows : " + rows);
		System.out.println("Number Of Colums : " + cols);
		String _start = "//form[@id='vContactsForm']/table/tbody/tr[";
		String _middle = "]/td[2]";
		//Method_1
		
		for (int i = 4; i < rows; i++) {
			String name =driver.findElement(By.xpath(_start + i + _middle)).getText();
			if(name.contains("Janny Rotala")){
				driver.findElement(By.xpath(_start+i+"]/td[1]//input[@name='contact_id']")).click();
			}
		}
		
		//intercom-borderless-frame
		//intercom-borderless-dismiss-button
		
		//Method_2
		
		driver.findElement(By.xpath("//a[contains(text(),'Johnathan Decoste')]/parent::td/preceding-sibling::td//input[@name='contact_id']")).click();
		

	}

}
