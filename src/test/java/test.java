import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.SkipException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class test {

	@Parameters({"email","pwd"})
	@Test
	public void test1(String email,String pwd) { 
		
		System.out.println(email+" "+pwd);
		
//		System.setProperty("webdriver.chrome.driver", "D:\\workspace\\MavenProject\\src\\test\\resources\\chromedriver.exe");
//	
////		WebDriverManager.chromedriver().setup();
//		
//		
//		ChromeDriver driver = new ChromeDriver();
//		
////		WebDriver driver = new HtmlUnitDriver();
//		
//		driver.manage().window().maximize();
//		driver.get("https://www.google.com/");
//		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Selenium Training");
//		driver.findElement(By.xpath("//input[@name='q']")).clear();
////		driver.findElement(By.xpath("//a[text()='Sign in']")).click();
//		System.out.println(driver.getTitle());
//		driver.navigate().refresh();
//		driver.close();
////		driver.quit();
//		
//		
//		assertEquals("1","2");
////		throw new SkipException("Skipping the test");
		
		
		
		
		
		

	}

}
