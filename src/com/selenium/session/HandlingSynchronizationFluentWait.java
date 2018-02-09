package com.selenium.session;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandlingSynchronizationFluentWait {

	public static void main(String[] args) {

		WebDriver driver = new FirefoxDriver();

		driver.get(
				"https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		By selectedTextArea = By
				.xpath("//div[@id='ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel']/span");

		By dateToBeSelected = By.xpath("//*[contains(@class,'rcWeekend')]/a");

		By dateGrid = By.xpath("//table[@id='ctl00_ContentPlaceholder1_RadCalendar1_Top']");

		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(dateGrid));

		String textbeforeAjaxCall = driver.findElement(selectedTextArea).getText();

		System.out.println("Text Before AJAX Call : " + textbeforeAjaxCall);

		WebElement element = driver.findElement(dateToBeSelected);
		element.click();

		By loader = By.xpath("//div[@class='raDiv']");

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).pollingEvery(100, TimeUnit.MILLISECONDS)
				.withTimeout(30, TimeUnit.SECONDS).ignoring(NotFoundException.class)
				.withMessage("Timed Out After 30 sec");
		
		wait.until(new ExpectedCondition<Boolean>() {		
			@Override
			public Boolean apply(WebDriver driver) {
				System.out.println("In Side Apply Method");
				return driver.findElement(selectedTextArea).getText().contains("2018");
			}
		});

		String textAeforeAjaxCall = driver.findElement(selectedTextArea).getText();

		System.out.println("Text Before AJAX Call : " + textAeforeAjaxCall);

		if (textAeforeAjaxCall.equalsIgnoreCase("Saturday, February 03, 2018")) {
			System.out.println("Pass");
		} else {
			System.out.println("Fail");
		}

	}

}
