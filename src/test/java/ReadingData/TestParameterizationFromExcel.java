package ReadingData;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cpcommunity.utilities.ExcelReader;

public class TestParameterizationFromExcel {
	
	
	@Test(dataProvider="getData1")
	public void doLogin(String userName, String pwd, String RunMode) {
		System.out.println(userName+" "+pwd);
		
	}
	
	
	
	public static ExcelReader excel = null;
	
	@DataProvider
	public Object[][] getData(){
		
		if(excel ==null) {
			
			excel = new ExcelReader("D:\\workspace\\MavenProject\\src\\test\\resources\\TestData\\TestData.xlsx");
		}
		
		String sheetName="Sheet1";
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		
		Object[][] data = new Object[rows-1][cols];
		
		for(int rowNum=2;rowNum<=rows;rowNum++) {
			
			for(int colNum=0;colNum<cols;colNum++) {
				
			data[rowNum-2][colNum]= excel.getCellData(sheetName, colNum, rowNum);
			
			}
		}
		
		return data;
	}
	
	@DataProvider
	public static Object[][] getData1() {

		if (excel == null) {

			excel = new ExcelReader("D:\\workspace\\MavenProject\\src\\test\\resources\\TestData\\TestData.xlsx");

		}

		String sheetName = "Sheet1";
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][cols];

		for (int rowNum = 2; rowNum <= rows; rowNum++) {

			for (int colNum = 0; colNum < cols; colNum++) {

				data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
			}
		}
		return data;
	}

}
