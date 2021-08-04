package com.ProjectName.Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.ProjectName.utilities.DriverManager;




public class BasePage<T> {
	
	protected WebDriver driver;
	private int AJAX_ELEMENT_TIMEOUT = 150;
	public Logger log = Logger.getLogger(BasePage.class);
	
	public BasePage() {
		this.driver = DriverManager.getDriver();

	}
	
	public T openPage(Class<T> clazz) {
		T page = null;
		try {
			driver = DriverManager.getDriver();
			AjaxElementLocatorFactory ajaxElemFactory = new AjaxElementLocatorFactory(driver, AJAX_ELEMENT_TIMEOUT);
			page = PageFactory.initElements(driver, clazz);
			PageFactory.initElements(ajaxElemFactory, page);
//			ExpectedCondition pageLoadCondition = ((BasePage) page).getPageLoadCondition();
//			waitForPageToLoad(pageLoadCondition);
//			((BasePage) page).getPageScreenSot();
		} catch (NoSuchElementException e) {
			/*
			 * String error_screenshot = System.getProperty("user.dir") +
			 * "\\target\\screenshots\\" + clazz.getSimpleName() + "_error.png";
			 * this.takeScreenShot(error_screenshot);
			 */ throw new IllegalStateException(String.format("This is not the %s page", clazz.getSimpleName()));
		}
		return page;
	}
	
	
	
	public void click(WebElement element,String elementName) {
		
		log.info("Clicking on "+elementName);
		element.click();
		log.info("Clicked on "+elementName);
		
	}
	
public void type(WebElement element,String elementName, String value) {
	log.info("Text entering in "+elementName+ " and the value is "+value);
		element.sendKeys(value);;
		log.info("Text entered in "+elementName+ " and the value is "+value);
		
	}
	
	
	
	
	

}
