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

public class HandlingRadioButton {
	static WebDriver driver;
	static WebDriverWait wait;

	static Logger log = Logger.getLogger("devpinoyLogger");

	public static void main(String[] args) {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 60);

		driver.get("https://www.w3schools.com/code/tryit.asp?filename=FNXN09W4KXHX");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);

		By runBtn = By.xpath("//button[contains(text(),'Run')]");
		By iframeR = By.xpath("//iframe[@id='iframeResult']");
		By genderRadioBtn = By.xpath("//input[@name='gender']");
		By colorRadioBtn = By.xpath("//input[@name='color']");

		driver.findElement(runBtn).click();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeR));

		List<WebElement> genderRadioBtnList = driver.findElements(genderRadioBtn);
		List<WebElement> colorRadioBtnList = driver.findElements(colorRadioBtn);

		selectRadioByIndex(genderRadioBtnList, 2);
		selectRadioByIndex(colorRadioBtnList, 1);
		selectRadioByValue(colorRadioBtnList, "Green");
		//selectRadioByVisibleText(genderRadioBtnList,"Green");

		//driver.quit();
	}

	public static void selectRadioByIndex(List<WebElement> element, int Index) {
		if (!element.get(Index).isSelected()) {
			element.get(Index).click();
		}
	}

	public static void selectRadioByValue(List<WebElement> element, String value) {
		for (WebElement e : element) {
			if (e.getAttribute("value").equalsIgnoreCase(value)) {
				if (!e.isSelected()) {
					e.click();
				}
				break;
			}
		}

	}
	
	public static void selectRadioByVisibleText(List<WebElement> element ,String text) {
		for(WebElement e : element){
			log.debug("Geting Text : "+e.findElement(By.xpath("")).getText());
			
		}
		
	}

}
