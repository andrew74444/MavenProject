package com.ProjectName.ModuleName.TestScripts;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;

import com.ProjectName.ExtentListeners.ExtentListeners;
import com.ProjectName.utilities.DriverFactory;
import com.ProjectName.utilities.DriverManager;
import com.ProjectName.utilities.JavaScript;
import com.ProjectName.utilities.RobotClass;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseTest {

	private FileInputStream fis;
	private Properties Config = new Properties();
	public Logger log = Logger.getLogger(BaseTest.class);
	private WebDriver driver;
	private String defaultUserName;
	private String defaultPassword;
	private String testsiteUrl;
	public boolean grid = false;
	
	
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
		
		
		DriverFactory.setGridPath("http://localhost:4444/wd/hub");
		DriverFactory.setConfigPropertyFilePath(
				System.getProperty("user.dir") + "//src//test//resources//properties//Config.properties");

		if (System.getProperty("os.name").equalsIgnoreCase("mac")) {

			DriverFactory.setChromeDriverExePath(
					System.getProperty("user.dir") + "//src//test//resources//executables//chromedriver");
			DriverFactory.setGeckoDriverExePath(
					System.getProperty("user.dir") + "//src//test//resources//executables//geckodriver");

		} else {

			DriverFactory.setChromeDriverExePath(
					System.getProperty("user.dir") + "//src//test//resources//executables//chromedriver.exe");
			DriverFactory.setGeckoDriverExePath(
					System.getProperty("user.dir") + "//src//test//resources//executables//geckodriver.exe");
			DriverFactory.setIeDriverExePath(
					System.getProperty("user.dir") + "//src//test//resources//executables//IEDriverServer.exe");

		}
		
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
	
	
	public void logInfo(String message) {

		ExtentListeners.testReport.get().info(message);
	}
	
	
	public void openBrowser(String browser) throws InterruptedException {
		
		if (System.getenv("ExecutionType") != null && System.getenv("ExecutionType").equals("Grid")) {

			grid = true;
		}

		DriverFactory.setRemote(grid);
		Capabilities caps;
		ChromeOptions options = new ChromeOptions();

		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });

		// Create object of HashMap Class
		Map<String, Object> prefs = new HashMap<String, Object>();

		// Set the notification setting it will override the default setting
		prefs.put("profile.default_content_setting_values.notifications", 2);

		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);

		// Set the experimental option
		options.setExperimentalOption("prefs", prefs);
		// options.addArguments("headless");

		if (DriverFactory.isRemote()) {
			DesiredCapabilities cap = null;

			if (browser.equals("firefox")) {

				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				cap.setPlatform(Platform.ANY);

			} else if (browser.equals("chrome")) {

				cap = DesiredCapabilities.chrome();
				cap.setBrowserName("chrome");
				cap.setPlatform(Platform.ANY);
			} else if (browser.equals("ie")) {

				cap = DesiredCapabilities.internetExplorer();
				cap.setBrowserName("iexplore");
				cap.setPlatform(Platform.WIN10);
			}

			try {
//				caps = ((RemoteWebDriver) driver).getCapabilities();
				driver = new RemoteWebDriver(new URL(DriverFactory.getGridPath()), cap);
			} catch (MalformedURLException e) {

				e.printStackTrace();
			}

		} else
		
		
		
		
		

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
		DriverManager.getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			
		setTestsiteUrl(Config.getProperty("testsiteurl"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		JavaScript.setJavaScriptObject(js);

		try {
			Robot robot = new Robot();
			RobotClass.setRobotClassObject(robot);
		} catch (AWTException e) {

			e.printStackTrace();
		}
		
	}
	
	
	
	public void quit() {

		DriverManager.getDriver().quit();
		log.info("Test Execution Completed !!!");
	}
	
	
	
	
	
	

}
