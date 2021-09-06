package Parameterization;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.sikuli.script.FindFailed;

import org.sikuli.script.Pattern;
 
import org.sikuli.script.Screen;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


public class Sikuli {
	
	long startTime;
	ChromeDriver driver;
	@Test()
	public void test() throws FindFailed, InterruptedException {

		Screen screen = new Screen();
		 
		// Create object of Pattern class and specify the images path
		 
		
		 
		Pattern image1 = new Pattern("C:\\Users\\Sasi\\Desktop\\Choose_File.png");
		 
		Pattern image2 = new Pattern("C:\\Users\\Sasi\\Desktop\\Address.PNG");
		 
		
		Thread.sleep(10000);
		
		System.setProperty("webdriver.chrome.driver", "D:\\workspace\\MavenProject\\src\\test\\resources\\chromedriver.exe");
//		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://demo.automationtesting.in/Register.html");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		
		screen.type(image2, "Vinod");
		
		screen.click(image1);
		
		Thread.sleep(5000);
		
	}
	
	@AfterMethod
	public void tearDown() {
		long endTime = System.currentTimeMillis();
		
		long timeTaken = endTime-startTime;
		System.out.println("Total Time Taken is : " +timeTaken);
		
//		driver.close();
		
	}
	
	
	
	
	

}
