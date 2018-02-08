package com.selenium.session;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandlingSynchronizationCustomExpectedConditions {

	public static void main(String[] args) {
			
		WebDriver driver = new FirefoxDriver();
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		By selectedTextArea=By.xpath("//div[@id='ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel']/span");
		By dateToBeSelected=By.xpath("//*[contains(@class,'rcWeekend')]/a");
		By dateGrid = By.xpath("//table[@id='ctl00_ContentPlaceholder1_RadCalendar1_Top']");
		By loader = By.xpath("//div[@class='raDiv']");

		
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(dateGrid));
		
		String textbeforeAjaxCall = driver.findElement(selectedTextArea).getText();
		System.out.println("Text Before AJAX Call : "+textbeforeAjaxCall);
		
		WebElement element = driver.findElement(dateToBeSelected);
		element.click();
			
		//new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(loader));
		
		//By implementing ExpectedCondition<Boolean> Interfce and override Apply method
		new WebDriverWait(driver, 20).until(new CustomException(selectedTextArea, "2018"));
		
		//Custom Exception with Anonymous class
		/*new WebDriverWait(driver, 30).ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {return driver.findElement(selectedTextArea).getText().contains("2018");}});*/
		
		String textAeforeAjaxCall = driver.findElement(selectedTextArea).getText();
		System.out.println("Text Aefore AJAX Call : "+textAeforeAjaxCall);
		
		if(textAeforeAjaxCall.equalsIgnoreCase("Saturday, February 03, 2018")){
			System.out.println("Pass");
		}else{
			System.out.println("Fail");
		}
	}

}

class CustomException implements ExpectedCondition<Boolean>{
	
	private By findBy;
	private String textToFind;
	
	public CustomException(By by,String text) {
		this.findBy=by;
		this.textToFind=text;
	}
		
	@Override
	public Boolean apply(WebDriver driver) {
		System.out.println("In side Apply Method");
		return driver.findElement(findBy).getText().contains(textToFind);
	}
	
	
}
