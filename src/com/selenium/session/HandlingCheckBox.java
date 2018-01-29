package com.selenium.session;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandlingCheckBox {
	static WebDriver driver;
	static WebDriverWait wait;

	static Logger log = Logger.getLogger("devpinoyLogger");
	public static void main(String[] args) {
		
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 60);
		driver.get("https://www.w3schools.com/code/tryit.asp?filename=FNQUS4N7MYNS");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		By runBtn = By.xpath("//button[contains(text(),'Run')]");
		By iframeR = By.xpath("//iframe[@id='iframeResult']");
		driver.findElement(runBtn).click();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeR));
		
		By checkVehicle = By.xpath("//input[@name='vehicle']");
		List<WebElement> vehicle = driver.findElements(checkVehicle);
		
		log.debug(vehicle.size());
		
		vehicle.get(0).click();
		
		for(WebElement e : vehicle){
			log.debug(e.getAttribute("value"));
			if(e.getAttribute("value").equals("Car")){
				if(!e.isSelected()){
					e.click();
				}
			}
			
		}
		
	}

}
