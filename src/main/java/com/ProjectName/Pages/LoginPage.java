package com.ProjectName.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	
	
	
	@FindBy(xpath = "//*[@name='q']")
	WebElement email;
	
	@FindBy(xpath="//a[text()='Sign in']")
	WebElement pwd;
	
	
	
	public void sign() {
		email.sendKeys("test@gmail.com");
		pwd.sendKeys("password");
		
		
	}

	
	
	
	
	
	
	
}
