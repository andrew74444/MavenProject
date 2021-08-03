package com.ProjectName.Pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.ProjectName.utilities.DriverManager;




public class BasePage<T> {
	
	protected WebDriver driver;
	private int AJAX_ELEMENT_TIMEOUT = 150;
	
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
	
	
	
	
	

}
