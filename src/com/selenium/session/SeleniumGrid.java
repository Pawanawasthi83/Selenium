package com.selenium.session;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SeleniumGrid {
	RemoteWebDriver driver;
	WebDriverWait wait;
	By email = By.xpath("//input[@id='email']");
	By password = By.xpath("//input[@id='pass']");
	By loginBtn = By.xpath("//*[@id='loginbutton']");
	
	@Parameters("browser")
	@BeforeMethod
	public void setUp(String browser) {
		System.out.println(browser);
		DesiredCapabilities cap=null;
		
		if (browser.equalsIgnoreCase("firefox")) {
			cap = DesiredCapabilities.firefox();
			cap.setBrowserName("firefox");
			cap.setPlatform(Platform.ANY);
			System.out.println("Capabilities Set for fire fox...");
		} else if (browser.equalsIgnoreCase("chrome")) {
			cap = DesiredCapabilities.chrome();
			cap.setBrowserName("chrome");
			cap.setPlatform(Platform.ANY);
			System.out.println("Capabilities Set for Chrome...");
		} else if (browser.equalsIgnoreCase("iexplore")) {
			cap = DesiredCapabilities.internetExplorer();
			cap.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");
			cap.setCapability(CapabilityType.PLATFORM, Platform.ANY);
			System.out.println("Capabilities Set for Internet Explorer...");
		}
		
		try {
				driver = new RemoteWebDriver(new URL("http://localhost:4455/wd/hub"), cap);
				
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 50); 
		wait.until(ExpectedConditions.alertIsPresent());
		driver.get("https://www.facebook.com/");
	}
	
	@Test
	public void getTitle(){
		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertEquals(title, "Facebook - Log In or Sign Up");
	}
	
	@Test
	public void facebookLogin() throws InterruptedException{
		System.out.println("Entering User Name");
		driver.findElement(email).sendKeys("pawanawasthi83");
		System.out.println("Entering password");
		driver.findElement(password).sendKeys("Vivalv@1983");
		System.out.println("Clicking on Login button");
		driver.findElement(loginBtn).click();
	}
	
	
	@AfterMethod
	public void tearDown(){
		System.out.println("Closing Driver");
		driver.quit();
	}
}
