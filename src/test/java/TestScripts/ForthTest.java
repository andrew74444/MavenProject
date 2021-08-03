package TestScripts;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ForthTest extends BaseTest {
	
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
	@Test(enabled=false)
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
//		driver.close();
		System.out.println("Browser Closed");
	}
	
	
	@Test(dependsOnMethods="UserRegistration")
	public void userLogged() {
		
		System.out.println("user Logged in");
		
	}
	
	@Test()
	public void UserRegistration() {
		
		
		if(true) {
			throw new SkipException("Skipping the test case");
		}
		
		
		
		System.out.println("User Registration");
		
		SoftAssert st = new SoftAssert();
		
		st.assertEquals("User enterd user name", "User enterd user name");
		st.assertEquals("User enterd password", "User enterd password");
		st.assertEquals("User clicked login button", "User clicked login button");
		st.assertEquals("User Registration Successsful", "User Registration Successsful");
		
		st.assertAll();
		
		
		
	}
	
	@Test(dependsOnMethods="userLogged")
	public void UserBookedTicket() {
		
		System.out.println("User Booked Ticket");
		
		SoftAssert st = new SoftAssert();
		st.assertTrue(false);
		st.assertAll();
	}
	
	@Test(dependsOnMethods= {"UserBookedTicket","userLogged"})
	public void UserLoggedOut() {
		
		System.out.println("User Logged Out");
		
	}
	
	

}
