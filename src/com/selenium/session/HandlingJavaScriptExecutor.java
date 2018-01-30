package com.selenium.session;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandlingJavaScriptExecutor {

	public static JavascriptExecutor js;
	public static WebDriverWait wait;
	static Logger log = Logger.getLogger("devpinoyLogger");

	public static void main(String[] args) {

		By email = By.xpath(".//*[@id='email']");
		By pass = By.xpath("//*[@id='pass']");
		By loginBtn = By.xpath("//*[@id='loginbutton']");
		By posttext = By.xpath("//*[@id='js_a']");

		WebDriver driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 60);
		js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

		jsGetUrl("http://facebook.com");

		WebElement emailTxtBox = driver.findElement(email);
		WebElement password = driver.findElement(pass);
		WebElement loginBt = driver.findElement(loginBtn);
		
		
		jsGetTitle();
		jsSendKeys(emailTxtBox, "pawanawasthi1983@gmail.com");
		jsSendKeys(password, "Vivalv@1983");
		jsDrawBorder(loginBt);
		jsElementFlash(loginBt);
		jsClick(loginBt);
		jsRefreshBrowser();
		jsScrollToBottom();
		WebElement text = driver.findElement(posttext);
		jsGetElementText(text);
		
		jsGenerateAlert("Java Script Alert: There is some error in the application");
		Alert alert = driver.switchTo().alert();
		alert.accept();
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

	public static void jsMouseOver(WebElement element) {
		String strJavaScript = "var element = arguments[0];"
				+ "var mouseEventObj = document.createEvent('MouseEvents');"
				+ "mouseEventObj.initEvent( 'mouseover', true, true );" + "element.dispatchEvent(mouseEventObj);";
		js.executeScript(strJavaScript, element);
	}

	public static void jsDrawBorder(WebElement element) {
		String script = "arguments[0].style.border='4px solid Red'";
		js.executeScript(script, element);
	}

	public static void jsGenerateAlert(String Msg) {
		String script = "alert(\'"+Msg+"\')";
		js.executeScript(script);
	}

	public static void jsRefreshBrowser() {
		js.executeScript("history.go(0)");
	}
	
	public static void jsGetTitle() {
		String WindowTitle = js.executeScript("return document.title").toString();
		log.debug("Window Title : "+WindowTitle);
	}

	public static void jsScrollToBottom() {
		String script = "window.scrollTo(0, document.body.scrollHeight)";
		js.executeScript(script);
	}

	public static void jsScrollToElement(WebElement element) {
		String script = "arguments[0].scrollIntoView()";
		js.executeScript(script, element);
	}
	
	public static void jsScrollToPixelLocation(int pixel) {
		String script = "window.scrollBy(0,"+pixel+")";
		js.executeScript(script);
	}
	public static void jsGetElementText(WebElement element) {
		String script = "return arguments[0].innerHTML";
		String innerText = js.executeScript(script,element).toString();
		log.debug("Inner Text Of Element "+element+"is"+innerText);
	}
	

}
