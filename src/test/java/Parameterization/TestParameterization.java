package Parameterization;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestParameterization {

	
	@Test(dataProvider="getData")
	public void doLogin(String userName, String pwd) {
		
		System.out.println(userName+" "+ pwd);
		
	}
	
	
	@DataProvider
	public Object[][] getData(){
		
		Object[][] data = new Object[3][2];
		data[0][0] = "test1@gmail.com";
		data[0][1] = "pwd1";
		
		data[1][0] = "test2@gmail.com";
		data[1][1] = "pwd2";
		
		data[2][0] = "test3@gmail.com";
		data[2][1] = "pwd3";		
		return data;
	}
	
	
	
}
