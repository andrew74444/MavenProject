package com.ProjectName.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ProjectName.utilities.DriverManager;


public class HomePage extends BasePage{

	
	@FindBy(xpath = "//*[@name='q']")
	WebElement searchField;
	
	@FindBy(xpath="//a[text()='Sign in']")
	WebElement signInButton;
	
	
	public LoginPage Search() {
		searchField.sendKeys("Selenium Training");
		signInButton.click();
		return (LoginPage) openPage(LoginPage.class);
	}
	
	public HomePage open(String url) {

		DriverManager.getDriver().navigate().to(url);
		System.out.println("Page Opened");
		return (HomePage) openPage(HomePage.class);
	}

	
	
}
