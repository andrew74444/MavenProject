package TestScripts2;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import TestScripts.BaseTest;

public class FirstTest extends BaseTest {
	
	@Test(enabled=false)
	public void test() {
				
		System.setProperty("webdriver.chrome.driver", "D:\\workspace\\MavenProject\\src\\test\\resources\\chromedriver.exe");
//		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
//		WebDriver driver = new HtmlUnitDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("A");
		driver.findElement(By.xpath("//input[@name='q']")).clear();
//		driver.findElement(By.xpath("//a[text()='Sign in']")).click();
		System.out.println(driver.getTitle());
		driver.navigate().refresh();
		driver.close();
		
		
	}
	
	ChromeDriver driver;
	@Test()
	public void test1() {
		
		System.setProperty("webdriver.chrome.driver", "D:\\workspace\\MavenProject\\src\\test\\resources\\chromedriver.exe");
//		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
//		WebDriver driver = new HtmlUnitDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("B");
		driver.findElement(By.xpath("//input[@name='q']")).clear();
//		driver.findElement(By.xpath("//a[text()='Sign in']")).click();
		System.out.println(driver.getTitle());
		driver.navigate().refresh();
		
	}
	
	@AfterMethod
	public void CloseBrowser() {
		driver.close();
	}
	
	
//	@Test(priority=2)
//	public void userLogged() {
//		
//		System.out.println("user Logged in");
//		
//	}
//	
//	@Test(priority=1)
//	public void UserRegistration() {
//		
//		System.out.println("User Registration");
//		
//	}
//	
//	@Test(priority=3)
//	public void UserBookedTicket() {
//		
//		System.out.println("User Booked Ticket");
//		
//	}
//	
//	@Test(priority=4)
//	public void UserLoggedOut() {
//		
//		System.out.println("User Logged Out");
//		
//	}
	
	

}
