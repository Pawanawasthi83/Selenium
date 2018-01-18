package com.selenium.session.javascriptexecuter;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JavaScriptExecutorHelper {

	public JavascriptExecutor js;
	public WebDriverWait wait;
	
	Logger log = Logger.getLogger("devpinoyLogger");

	public JavaScriptExecutorHelper(WebDriver driver) {
		log.debug("In Side Helper Cunstroctor..");
		this.js = (JavascriptExecutor) driver;
		this.wait = new WebDriverWait(driver, 60);
	}

	public void jsGetUrl(String Url) {
		log.debug("In Side Get Url...");
		String script = "window.location=\'" + Url + "\'";
		js.executeScript(script);
	}

	public void jsSendKeys(WebElement element, String key) {
		log.debug("In side Send Key...");
		String script = "arguments[0].value=\'" + key + "\'";
		wait.until(ExpectedConditions.visibilityOf(element));
		js.executeScript(script, element);
		
	}

	public void jsClick(WebElement element) {
		log.debug("In Side Click Method");
		String script = "arguments[0].click();";
		wait.until(ExpectedConditions.elementToBeClickable(element));
		js.executeScript(script, element);
	}

}
