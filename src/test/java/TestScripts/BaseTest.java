package TestScripts;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
	
	
	@BeforeSuite
	public void setUpFrameWok() {
		System.out.println("Reoprt Initiallized");
	}
//	@BeforeClass
	
//	@BeforeTest
	
	@BeforeMethod
	public void openBrowser() {
		System.out.println("open Browser");
	}
	
//	@Test
	
	
	
//	@AfterTest
//	@AfterClass
	@AfterSuite
	public void sendMail() {
		System.out.println("Test Execution Completed");
	}

}
