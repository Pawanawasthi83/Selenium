package com.selenium.session;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandlingIframesByIndex {
	static WebDriver driver;
	static WebDriverWait wait;
	static Logger log = Logger.getLogger("devpinoyLogger");

	public static void main(String[] args) {
		
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 60);
		driver.get("URL");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		
		By fname = By.xpath("XPATH");
		int frameIndex = getFrameIndex(fname);
		
		System.out.println("Element Found at "+frameIndex+"th Index");

	}
	
	public static int getFrameIndex(By locator){
		int frameCount=driver.findElements(By.tagName("iframe")).size();
		int myFrame=frameCount+1;
		for(int i=0;i<frameCount;i++){
			driver.switchTo().frame(i);
			int f = driver.findElements(locator).size();
			System.out.println(f);
			if(f==1){
				myFrame=i;
				driver.switchTo().defaultContent();
				break;
			}
			driver.switchTo().defaultContent();
		}
		return myFrame;
	}

}
