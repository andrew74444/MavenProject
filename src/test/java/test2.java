import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.SkipException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class test2 {

	@Parameters({"email","pwd"})
	@Test(invocationCount=5,threadPoolSize=5)
	public void test1(String email,String pwd) { 
		
		System.out.println(email+" "+pwd);
		
		System.setProperty("webdriver.chrome.driver", "D:\\workspace\\MavenProject\\src\\test\\resources\\chromedriver.exe");
		
//			WebDriverManager.chromedriver().setup();
			
			
			ChromeDriver driver = new ChromeDriver();
			
//			WebDriver driver = new HtmlUnitDriver();
			
			driver.manage().window().maximize();
			driver.get("https://www.google.com/");
			driver.quit();
		
		
		
		
		

	}

}
