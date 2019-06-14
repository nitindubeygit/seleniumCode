package com.vTiger.testCases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vTiger.pageObjects.LoginPage;
import com.vTiger.utilities.XLUtils;

public class TestCase_002_DataDriven extends BaseClass {

	@Test(dataProvider="LoginData")
	public void loginDDT(String user, String pwd) throws IOException {

		LoginPage lpObj= new LoginPage(driver);
		lpObj.enterUserName(user);
		lpObj.enterPassword(pwd);
		lpObj.clickLogin();
		
		if (lpObj.isLoggedin()==true){
		Assert.assertTrue(true);
		logger.info("login passed");
		}
		else{
			takeScreenShot(driver, "loginDDT");
			Assert.assertTrue(false);
			logger.warn("login failed");
			
		}
				
	}

	
	
	@DataProvider(name = "LoginData")
	String[][] getData() throws IOException {
		String path = System.getProperty("user.dir")+ "/src/test/java/com/vTiger/testData/LoginData.xlsx";
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "sheet1", 1);
		String loginData[][] = new String[rownum][colcount];
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				loginData[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}

		}
		return loginData;
	}
	
	
	
	

}
