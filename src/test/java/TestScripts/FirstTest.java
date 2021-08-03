package TestScripts;

import org.testng.annotations.Test;

public class FirstTest {
	
	
	
	
	@Test(groups ="Functional")
	public void test1() {
		
		System.out.println("test1");
		
	}
	
	@Test(groups = {"Regression","Functional","End2End"})
	public void test2() {
		
		System.out.println("test2");
		
		
	}
	
	@Test(groups ={"Regression","Functional","End2End"})
	public void test3() {
		
		System.out.println("test3");
		
		
	}
	
	@Test(groups = {"Functional","End2End"})
	public void test4() {
		
		System.out.println("test4");
		
	}
	
	@Test(groups ="Functional")
	public void test5() {
		
		System.out.println("test5");
		
	}
	
	@Test(groups ="Smoke")
	public void test6() {
		
		System.out.println("test6");
		
	}
	
	
	
	

}
