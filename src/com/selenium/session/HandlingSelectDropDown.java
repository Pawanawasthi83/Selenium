package com.selenium.session;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandlingSelectDropDown {
	
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
		
		By select_1 = By.xpath("//select[@id='SelectID_One']");
		By select_2 = By.xpath("//select[@id='SelectID_Two']");
		By select_3 = By.xpath("//select[@id='SelectID_Three']");
		WebElement selectColor = driver.findElement(select_1);
		WebElement selectFruits = driver.findElement(select_2);
		WebElement selectAnimal = driver.findElement(select_3);
		
		Select selectOne = new Select(selectColor);
		Select selectTwo = new Select(selectAnimal);
		log.debug(selectColor.getTagName());
		log.debug("Checking Multiple Attribute : "+selectColor.getAttribute("multiple"));
		log.debug(selectOne.isMultiple());
		
		
		
		List<WebElement> selectedOption = selectOne.getAllSelectedOptions();
		
		for(WebElement element : selectedOption){
			log.debug(element.getText());
		}
		
		selectOne.selectByIndex(0);
		selectOne.selectByValue("greenvalue");
		selectOne.selectByVisibleText("Yellow");
		List<WebElement> Options = selectOne.getOptions();
		for(WebElement element : Options){
			if(element.isSelected()){
			log.debug("Selected Option : "+element.getText());
		}
			}
		
		log.debug("IS Multiple : "+selectTwo.isMultiple()); 
		
		selectTwo.selectByIndex(0);
		selectTwo.selectByValue("elephantvalue");
		selectTwo.selectByVisibleText("Dog");
		
		List<WebElement> OptionsMultiple = selectTwo.getOptions();
		for(WebElement element : OptionsMultiple){
			if(element.isSelected()){
			log.debug("Selected Option : "+element.getText());
		}
			}
		driver.quit();

	}

}
