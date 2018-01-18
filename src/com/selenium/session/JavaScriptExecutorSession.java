package com.selenium.session;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JavaScriptExecutorSession {

	public static JavascriptExecutor js;
	public static WebDriverWait wait;
	static Logger log = Logger.getLogger("devpinoyLogger");

	public static void main(String[] args) {

		By email = By.xpath(".//*[@id='email']");
		By pass = By.xpath("//*[@id='pass']");
		By loginBtn = By.xpath("//*[@id='loginbutton']");

		WebDriver driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 60);
		js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

		jsGetUrl("http://facebook.com");

		WebElement emailTxtBox = driver.findElement(email);
		WebElement password = driver.findElement(pass);
		WebElement loginBt = driver.findElement(loginBtn);

		jsSendKeys(emailTxtBox, "pawanawasthi1983@gmail.com");
		jsSendKeys(password, "Vivalv@1983");
		jsElementFlash(loginBt);
		jsClick(loginBt);

		driver.quit();

	}

	public static void jsGetUrl(String Url) {
		log.debug("In Side Get Url...");
		String script = "window.location=\'" + Url + "\'";
		js.executeScript(script);
	}

	public static void jsSendKeys(WebElement element, String key) {
		log.debug("In side Send Key...");
		String script = "arguments[0].value=\'" + key + "\'";
		wait.until(ExpectedConditions.visibilityOf(element));
		js.executeScript(script, element);

	}

	public static void jsClick(WebElement element) {
		log.debug("In Side Click Method");
		String script = "arguments[0].click();";
		wait.until(ExpectedConditions.elementToBeClickable(element));
		js.executeScript(script, element);
	}

	public static void jsElementFlash(WebElement element) {

		log.debug("Flashing the element...");
		String bgColor = element.getCssValue("backgroundColor");

		for (int i = 0; i < 100; i++) {
			changeColor(element, "#0000ff");
			changeColor(element, bgColor);
		}
	}

	public static void changeColor(WebElement element, String color) {
		String script = "arguments[0].style.backgroundColor=\'" + color + "\'";
		js.executeScript(script, element);
	}

}
