package com.selenium.session;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandlingJavaScriptAlertPopUps {

	static WebDriver driver;
	static WebDriverWait wait;
	
	static Logger log = Logger.getLogger("devpinoyLogger");
	
	public static void main(String[] args) throws InterruptedException {
		
		
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 60);
		
		driver.get("https://www.w3schools.com/code/tryit.asp?filename=FNOSHZZMYCVO");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		
		By runBtn = By.xpath("//button[contains(text(),'Run')]");
		By iframeR=By.xpath("//iframe[@id='iframeResult']");
		driver.findElement(runBtn).click();
		
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeR));
		
		By alertBox = By.xpath("//button[contains(text(),'Alert')]");
		By confirmBox = By.xpath("//button[contains(text(),'Confirm')]");
		By promptBox = By.xpath("//button[contains(text(),'Prompt')]");
		By mess = By.xpath("//p[@id='demo']");
				
		wait.until(ExpectedConditions.elementToBeClickable(alertBox));
		driver.findElement(alertBox).click();
		
		//Switching control on alert
		if(isAlertPresent()){
		Alert alert = driver.switchTo().alert();
		log.debug("Alert Message : "+alert.getText());
		Thread.sleep(3000);
		alert.accept();
		}
		
		//Switching control on confirmation Alert
		
		driver.findElement(confirmBox).click();
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		Thread.sleep(3000);
		alert.accept();
		String m = driver.findElement(mess).getText();
		log.debug(m);
		log.debug("Confirn Box accepted");
		
		driver.findElement(confirmBox).click();
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert1 = driver.switchTo().alert();
		Thread.sleep(3000);
		alert1.dismiss();
		String mm = driver.findElement(mess).getText();
		log.debug(mm);
		log.debug("Confirn Box Dismissed");
		
		//Switching control on Promt Alert
		driver.findElement(promptBox).click();
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert2 = driver.switchTo().alert();
		alert2.sendKeys("Pawan Awasthi");
		Thread.sleep(3000);
		alert2.accept();
		String mmm = driver.findElement(mess).getText();
		log.debug(mmm);
		log.debug("Prompt Box accepted");
		driver.close();
	}
	
	public static boolean isAlertPresent(){
		try{
			driver.switchTo().alert();
			return true;
		}catch(NoAlertPresentException e){
			log.debug("Alert Is Not Present");
			e.printStackTrace();
			return false;
		}
	}

}
