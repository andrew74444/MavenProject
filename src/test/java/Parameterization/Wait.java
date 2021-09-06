package Parameterization;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.ProjectName.utilities.DriverManager;

public class Wait {
	
	long startTime;
	ChromeDriver driver;
	@Test()
	public void test() throws InterruptedException {


		System.setProperty("webdriver.chrome.driver", "D:\\workspace\\MavenProject\\src\\test\\resources\\chromedriver.exe");
//		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		startTime = System.currentTimeMillis();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ttt"))).click();
		
	
		
	}
	
	@AfterMethod
	public void tearDown() {
		long endTime = System.currentTimeMillis();
		
		long timeTaken = endTime-startTime;
		System.out.println("Total Time Taken is : " +timeTaken);
		
		driver.close();
		
	}
	
	
	
	
	

}
