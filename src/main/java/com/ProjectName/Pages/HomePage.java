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
		
		type(searchField, "search Field", "Selenium Training");
		click(signInButton,"sign In Button");
		
		return (LoginPage) openPage(LoginPage.class);
	}
	
	public HomePage open(String url) {

		DriverManager.getDriver().navigate().to(url);
		System.out.println("Page Opened");
		return (HomePage) openPage(HomePage.class);
	}

	
	
}
