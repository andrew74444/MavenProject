package Parameterization;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class AutoIt {
	
	
	ChromeDriver driver;
	@Test()
	public void test() throws InterruptedException, IOException {


		System.setProperty("webdriver.chrome.driver", "D:\\workspace\\MavenProject\\src\\test\\resources\\chromedriver.exe");
//		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://demo.automationtesting.in/Register.html");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//input[@type='file']")).click();
		Thread.sleep(15000);
		Runtime.getRuntime().exec("D:\\workspace\\MavenProject\\src\\test\\resources\\TestData\\New folder\\filrupload.exe");
	
		Thread.sleep(5000);
	}
	
	@AfterMethod
	public void tearDown() {
		
		
		driver.close();
		
	}
	
	
	
	
	

}
