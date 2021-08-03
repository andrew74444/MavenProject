package Parameterization;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cpcommunity.utilities.ExcelReader;

public class TestParameterizationFromExcelWithHashTable {

	
	@Test(dataProvider="getData")
	public void doLogin(Hashtable<String, String > data) {
		
		System.out.println(data.get("userName")+" "+ data.get("pwd")+" "+data.get("RunMode"));
		
	}
		
	public static ExcelReader excel = null;
	
	
	@DataProvider
	public Object[][] getData(){
		
		if(excel == null) {
			excel = new ExcelReader("D:\\workspace\\MavenProject\\src\\test\\resources\\TestData\\TestData.xlsx");
		}
		
		String sheetName = "Sheet1";
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);		
		
		Object[][] data = new Object[rows-1][1];
		
		Hashtable<String, String> table = null;
		
		for(int rowNum=2;rowNum<=rows;rowNum++) {
			
			table = new Hashtable<String, String>();
			
			for(int colNum=0;colNum<cols;colNum++) {
				
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum-2][0]=table;
				
				
//				data[rowNum-2][colNum]=excel.getCellData(sheetName, colNum, rowNum);
				
			}
		}
			
		return data;
	}
	
	
	
}
