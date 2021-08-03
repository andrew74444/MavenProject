package Parameterization;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterizationWithTestNgXML {
	
	
	@Parameters({"email","pwd"})
	@Test(invocationCount=5,threadPoolSize=5,timeOut=2000)
	public void test(String email,String pwd) throws InterruptedException {
		Thread.sleep(3000);
		System.out.println(email+" "+pwd);
//		System.setProperty("webdriver.chrome.driver", "D:\\workspace\\MavenProject\\src\\test\\resources\\chromedriver.exe");
//		
//		ChromeDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("https://www.google.com/");
//		driver.close();
		
	}
	
	
	
	
	
	
	

}
