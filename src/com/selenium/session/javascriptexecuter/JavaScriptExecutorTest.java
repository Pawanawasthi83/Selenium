package com.selenium.session.javascriptexecuter;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class JavaScriptExecutorTest {

	
	public static void main(String[] args) {
		
		By email = By.xpath(".//*[@id='email']");
		By pass = By.xpath("//*[@id='pass']");
		By loginBtn = By.xpath("//*[@id='loginbutton']");
		
		WebDriver driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		
		JavaScriptExecutorHelper jsHelper = new JavaScriptExecutorHelper(driver);
		
		jsHelper.jsGetUrl("http://facebook.com");
		
		WebElement emailTxtBox= driver.findElement(email);
		WebElement password = driver.findElement(pass);
		WebElement loginBt = driver.findElement(loginBtn);
		
		
		jsHelper.jsSendKeys(emailTxtBox, "pawanawasthi1983@gmail.com");
		jsHelper.jsSendKeys(password, "Vivalv@1983");
		jsHelper.jsClick(loginBt);
		
		driver.quit();
		
		

	}

}
