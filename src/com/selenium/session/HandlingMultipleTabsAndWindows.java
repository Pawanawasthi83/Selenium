package com.selenium.session;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandlingMultipleTabsAndWindows {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new FirefoxDriver();
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver.manage().window().maximize();
		driver.get("https://www.icicibank.com/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		By beUpdateModelCloseBtn = By.xpath("//div[@id='push-modal-close']");
		By virtualAssistantCloseBtn = By.xpath("//button[@class='widget-side__close']");
		By activeIBCloseBtn = By.xpath("//a[@class='closeVisit']");
		By privateLink = By.xpath("//a[contains(text(),'Private')]");
		By aboutUs = By.xpath("//a[contains(text(),'About Us')]");

		String homePageTitle = "Personal Banking, Online Banking Services - ICICI Bank";
		String privateBankingPageTitle = "ICICI Bank Private Banking";
		String aboutUsPageTitle = "About Us - ICICI Bank";

		Thread.sleep(4000);
		System.out.println(driver.findElements(beUpdateModelCloseBtn).size() + " "
				+ driver.findElements(virtualAssistantCloseBtn).size() + " "
				+ driver.findElements(activeIBCloseBtn).size());
		
		if(driver.findElements(beUpdateModelCloseBtn).size()!=0){
			wait.until(ExpectedConditions.elementToBeClickable(beUpdateModelCloseBtn));
			driver.findElement(beUpdateModelCloseBtn).click();
		}
		
		if(driver.findElements(virtualAssistantCloseBtn).size()!=0 && driver.findElement(virtualAssistantCloseBtn).isDisplayed() ){
			wait.until(ExpectedConditions.elementToBeClickable(virtualAssistantCloseBtn));
			driver.findElement(virtualAssistantCloseBtn).click();
		}
		
		if(driver.findElements(activeIBCloseBtn).size()!=0){
			wait.until(ExpectedConditions.elementToBeClickable(activeIBCloseBtn));
			driver.findElement(activeIBCloseBtn).click();
		}
		
		
		if(driver.getTitle().equals(homePageTitle)){
			System.out.println("Control is On Home Page");
		}
		
		Thread.sleep(2000);
		
		driver.findElement(privateLink).click();
		
		Thread.sleep(2000);
		
		Set<String> handles = driver.getWindowHandles();
		
		Iterator<String> itr = handles.iterator();
		
		String parentWin =  itr.next();
		String childWin_1= itr.next();
		
		driver.switchTo().window(childWin_1);
		
		if(driver.getTitle().equals(privateBankingPageTitle)){
			System.out.println("Control is On Private Banking Page");
		}
		
		driver.findElement(aboutUs).click();
		
		Thread.sleep(2000);
		
		handles = driver.getWindowHandles();
		
		itr = handles.iterator();
		
		parentWin =  itr.next();
		childWin_1= itr.next();
		String child_2=itr.next();
		
		driver.switchTo().window(child_2);
		Thread.sleep(2000);
		
		if(driver.getTitle().equals(aboutUsPageTitle)){
			System.out.println("Control is On aboutUs Page");
		}
		
		
		System.out.println("Closing About Window and moving control to Private Banking ");
		
		driver.close();
		
		driver.switchTo().window(childWin_1);
		
		if(driver.getTitle().equals(privateBankingPageTitle)){
			System.out.println("Control is On Private Banking Page");
		}
		
		System.out.println("Closing Private Banking Paget Window and moving control to Home Page ");
		
		driver.close();
		
		driver.switchTo().window(parentWin);
		
		if(driver.getTitle().equals(homePageTitle)){
			System.out.println("Control is On Home Page");
		}
		
		
	}

}