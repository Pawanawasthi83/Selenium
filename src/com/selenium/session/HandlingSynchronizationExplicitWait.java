package com.selenium.session;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandlingSynchronizationExplicitWait {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		WebDriver driver = new FirefoxDriver();
		
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		By selectedTextArea=By.xpath("//div[@id='ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel']/span");
		
		By dateToBeSelected=By.xpath("//*[contains(@class,'rcWeekend')]/a");
	
				
		/*It checks the element presence on the DOM of a page. 
		This does not necessarily mean that the element is visible.*/
		//wait.until(ExpectedConditions.presenceOfElementLocated(loginBtn));
		/*It is for the element present in the DOM of a page is visible.*/
		//wait.until(ExpectedConditions.visibilityOfElementLocated(loginBtn));
		/*It is for the element present in the DOM of a page is invisible.*/
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(loginBtn));	
		/*It is for the element to be clickable.*/
		//wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
		
		By dateGrid = By.xpath("//table[@id='ctl00_ContentPlaceholder1_RadCalendar1_Top']");
		
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(dateGrid));
		
		String textbeforeAjaxCall = driver.findElement(selectedTextArea).getText();
		
		System.out.println("Text Before AJAX Call : "+textbeforeAjaxCall);
		
		
		WebElement element = driver.findElement(dateToBeSelected);
		element.click();
		
		By loader = By.xpath("//div[@class='raDiv']");
		
		new WebDriverWait(driver, 1)
		.pollingEvery(500, TimeUnit.MILLISECONDS)
		.withTimeout(30, TimeUnit.SECONDS)
		.ignoring(NoSuchElementException.class)
		.withMessage("Timed Out After 5 Second")
		.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		
		
		
		String textAeforeAjaxCall = driver.findElement(selectedTextArea).getText();
		
		System.out.println("Text Before AJAX Call : "+textAeforeAjaxCall);
		
		if(textAeforeAjaxCall.equalsIgnoreCase("Saturday, February 03, 2018")){
			System.out.println("Pass");
		}else{
			System.out.println("Fail");
		}
		
		
		
		
		
		
	}

}
