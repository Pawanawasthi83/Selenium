package com.selenium.session;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandlingDynamicWebTablesPagination {

	public static void main(String[] args) {
		
		WebDriver driver = new FirefoxDriver();
		WebDriverWait wait = new WebDriverWait(driver,30);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

		driver.get("http://freecrm.com/");

		By username = By.xpath("//input[@name='username']");
		By password = By.xpath("//input[@name='password']");
		By loginBtn = By.xpath("//input[@value='Login']");
		By contactLink = By.xpath("//a[contains(text(),'Contacts')]");
		By rowLocator = By.xpath("//form[@id='vContactsForm']/table/tbody/tr");
		By colsLocator = By.xpath("//form[@id='vContactsForm']/table/tbody/tr[4]/td");
		By iFrameMainPanel = By.xpath("//frame[@name='mainpanel']");
		
		
		driver.findElement(username).sendKeys("pawanawasthi83");
		driver.findElement(password).sendKeys("Vivalv@1983");

		
		wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
		driver.findElement(loginBtn).click();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iFrameMainPanel));

		driver.findElement(contactLink).click();

		String cruntSelectedPage=driver.findElement(By.xpath("//form[@id='vContactsForm']/table/tbody/tr[25]/td[1]/div/strong")).getText();
		int totalNumberOfPages = driver.findElements(By.xpath("//form[@id='vContactsForm']/table/tbody/tr[25]/td[1]/div/a")).size();
		
		System.out.println(cruntSelectedPage);
		System.out.println(totalNumberOfPages);
		
		int currpg= Integer.parseInt(cruntSelectedPage);
		
		for(int i=0;i<=totalNumberOfPages;i++){
			
			/*By frameLocator = By.className("intercom-borderless-frame");
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
			Actions builder = new Actions(driver);
			WebElement menu = driver.findElement(By.className("intercom-author-summary-name"));
			builder.moveToElement(menu).build().perform();
			driver.findElement(By.className("intercom-borderless-dismiss-button")).click();;
			driver.switchTo().parentFrame();*/
						
			List<WebElement> element= driver.findElements(By.xpath("//form[@id='vContactsForm']/table/tbody/tr[25]/td[1]/div/a"));
			System.out.println("Size : "+element.size());
			//WebElement e = driver.findElement(By.xpath("//a[contains(text(),'Pawan1 Awasthi1')]"));
			By by=By.xpath("//a[contains(text(),'Priyanka Awasthi')]");
					
			if(driver.findElements(by).size()!=0){
				System.out.println("Element Found");
				driver.findElement(By.xpath("//a[contains(text(),'Priyanka Awasthi')]/parent::td/preceding-sibling::td//input[@name='contact_id']")).click();
				break;
			}else{
				System.out.println("Element not found Clicking on next Page");
				element.get(i).click();
			}
			
			
		}
		
	}
		
}	
		
		


	
	
