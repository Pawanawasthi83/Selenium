package com.selenium.session;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandlingSynchronizationImplicitWait {

	public static void main(String[] args) {
		
	WebDriver driver = new FirefoxDriver();
		
	driver.get("https://www.facebook.com/");
	
	By loginBtn1=By.xpath("//*[@id='loginbutton']");
	By loginBtn2=By.xpath("//input[@id='100_0_2']");
	
	
	//when default implicit wait is not defined for finding non existing element
	calculateImplicitWaitTime(driver,loginBtn2,60);
	//Command duration or timeout: 56 milliseconds
	
	//when default implicit wait is defined for finding non existing element
	//calculateImplicitWaitTime(driver,loginBtn2,60);
	//Command duration or timeout: 60.05 seconds
	
	//when implicit wait is altered for finding non existing element
	//calculateImplicitWaitTime(driver,loginBtn2,80);
	
	//when default implicit wait is not defined for finding existing element
	//calculateImplicitWaitTime(driver,loginBtn1,0);
	
	//when default implicit wait is  defined for finding existing element
	//calculateImplicitWaitTime(driver,loginBtn1,60);
	
	}
	
	public static void calculateImplicitWaitTime(WebDriver driver,By locator, int time) {
		if(time>0){
			System.out.println("Time out Defined");
			driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		}
		long now = System.currentTimeMillis();
		try{
			new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(locator));
			driver.findElement(locator);
		}catch(NoSuchElementException e){
			System.out.println("In Side Catch Block Time Taken : "+(System.currentTimeMillis()-now));
		}finally {
			System.out.println("In Side Finally Block Time Taken : "+(System.currentTimeMillis()-now));
		}
		System.out.println("Time Taken : "+(System.currentTimeMillis()-now));
	}

}
