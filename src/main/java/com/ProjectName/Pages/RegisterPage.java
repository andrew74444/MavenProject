package com.ProjectName.Pages;

import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ProjectName.ExtentListeners.ExtentManager;
import com.ProjectName.utilities.DriverManager;

public class RegisterPage extends BasePage {
	
	
	
	@FindBy(xpath = "//select[@id='Skills']")
	WebElement skills;

	@FindBy(xpath = "//div[@id='msdd']")
	WebElement Languages;
	
	@FindBy(xpath = "//span[@id='select2-country-container']")
	WebElement country;
	
	
	@FindBy(xpath = "//input[@class='select2-search__field']")
	WebElement countrySearch;
			
			
	@FindBy(xpath = "//span[text()='Insights']")
	WebElement insights;
	
	@FindBy(xpath = "//span[text()='Surveys']")
	WebElement surveys;
	
	
	@FindBy(xpath = "//button[text()='Click me']")
	WebElement clickMe;
	
	
	@FindBy(xpath = "//button[text()='Try it']")
	WebElement tryIt;
	
	@FindBy(xpath = "//iframe[@id='iframeResult']")
	WebElement iframeResult;
	
	
	public RegisterPage open(String url) {
		DriverManager.getDriver().navigate().to(url);
		System.out.println("Page Opened");
		return (RegisterPage) openPage(RegisterPage.class);	
	}
	@Override
	protected void getPageScreenSot() {
		aShot();
	}
	
	
	
	
	public void m1() throws InterruptedException {
		selectByVisibleText(skills, "Adobe Photoshop", "skills");		
		Thread.sleep(1000);		
		clickElementByJavaScript(country);
		Thread.sleep(1000);
		type(countrySearch, "Ind", "India");
		countrySearch.sendKeys(Keys.ENTER);
		Thread.sleep(1000);	
		clickElementByJavaScript(Languages);		
		String value = "Dutch";		
		WebElement element = driver.findElement(By.xpath("//a[text()='"+value+"']"));		
		click(element, value);		
		Thread.sleep(3000);		
		selectUsingIndex(skills, 10, "skills");		
		Thread.sleep(3000);		
		selectUsingByValue(skills, "Certifications", "skills");
	}	
	public void m2() throws InterruptedException {
		Thread.sleep(5000);
		moveToElement(insights);
		String x = getAttributeValue(surveys, "itemprop");
		System.out.println(x);
		setAttribute(surveys, "Green", "itemprop");
		x = getAttributeValue(surveys, "itemprop");
		System.out.println(x);
		Thread.sleep(5000);
		clickElementByJavaScript(surveys);
	}	
	public void m3() {		
		System.out.println(driver.findElements(By.xpath("//iframe")).size());
		switchTOIframeByElement(iframeResult);
		click(clickMe, "click Me");		
		switchTodefaultContent();		
	}
	
	
	public void m4() throws InterruptedException {		
		System.out.println(driver.findElements(By.xpath("//iframe")).size());
		switchTOIframeByElement(iframeResult);
		click(tryIt, "try It");
		ExtentManager.captureScreenshot();
		Thread.sleep(3000);
		getAlertText();
		getAlert();
//		switchTodefaultContent();		
	}
	
	public void m5() throws InterruptedException {		
		
		
		
		 robot.keyPress(KeyEvent.VK_END);
		ExtentManager.captureScreenshot();
		Thread.sleep(3000);
		
	}
	
	
}
