package com.ProjectName.ModuleName.TestScripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;

import com.ProjectName.utilities.DriverFactory;
import com.ProjectName.utilities.DriverManager;


import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseTest {

	private FileInputStream fis;
	private Properties Config = new Properties();
	public Logger log = Logger.getLogger(BaseTest.class);
	private WebDriver driver;
	private String defaultUserName;
	private String defaultPassword;
	private String testsiteUrl;
	
	
	public String getTestsiteUrl() {
		return testsiteUrl;
	}

	public void setTestsiteUrl(String testsiteUrl) {
		this.testsiteUrl = testsiteUrl;
		System.out.println(testsiteUrl);
		System.out.println(this.testsiteUrl);
	}

	public String getDefaultUserName() {
		return defaultUserName;
	}
	
	public String getDefaultPassword() {
		return defaultPassword;
	}
	
	
	public void setDefaultUserName(String defaultUserName) {
		this.defaultUserName = defaultUserName;
	}
	
	public void setDefaultPassword(String defaultPassword) {
		this.defaultPassword = defaultPassword;
	}
	
	@BeforeSuite
	public void setUpFramework() {
		configureLogging();
		DriverFactory.setConfigPropertyFilePath(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");

		/*
		 * Initialize properties Initialize logs load executables
		 * 
		 */
		try

		{
			System.out.println(DriverFactory.getConfigPropertyFilePath());
			fis = new FileInputStream(DriverFactory.getConfigPropertyFilePath());
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		try {
			Config.load(fis);			
			 log.info("Config properties file loaded");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void configureLogging() {
		String log4jConfigFile = System.getProperty("user.dir") + File.separator + "src/test/resources/properties"
				+ File.separator + "log4j.properties";
		PropertyConfigurator.configure(log4jConfigFile);
	}
	
	
	public void openBrowser(String browser) throws InterruptedException {	

		if (browser.equals("chrome")) {
			System.out.println("Launching : " + browser);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			System.out.println("Launching : " + browser);
			System.setProperty("webdriver.gecko.driver", DriverFactory.getGeckoDriverExePath());
			driver = new FirefoxDriver();

		}		
		DriverManager.setWebDriver(driver);
		log.info("Driver Initialized !!!");
		DriverManager.getDriver().manage().window().maximize();
		DriverManager.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
		setTestsiteUrl(Config.getProperty("testsiteurl"));
		

		
		
	}
	
	
	
	
	
	
	
	
	
	

}
