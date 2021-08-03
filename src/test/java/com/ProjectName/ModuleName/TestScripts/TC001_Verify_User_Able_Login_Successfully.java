package com.ProjectName.ModuleName.TestScripts;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.ProjectName.Pages.HomePage;
import com.ProjectName.Pages.LoginPage;
import com.ProjectName.utilities.Constants;
import com.ProjectName.utilities.DataProviders;
import com.ProjectName.utilities.DataUtil;
import com.ProjectName.utilities.ExcelReader;


public class TC001_Verify_User_Able_Login_Successfully extends BaseTest{

	
	@Test(dataProviderClass=DataProviders.class,dataProvider="masterDP")
	public void TC001 (Hashtable<String,String> data) throws Exception {		
	
	ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);
	DataUtil.checkExecution("master", "TC001", data.get("Runmode"), excel);
	openBrowser(data.get("browser"));
	HomePage home = new HomePage().open(getTestsiteUrl());
	LoginPage lp = home.Search();
	lp.sign();
	
	}
	
}
