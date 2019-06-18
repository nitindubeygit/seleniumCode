package com.vTiger.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vTiger.pageObjects.Campaigns;
import com.vTiger.pageObjects.LoginPage;
import com.vTiger.utilities.XLUtils;

public class TestCase_003_CreateCampaign extends BaseClass{

	
	@Test(dataProvider="campaignData")
	public void createCampaignDDT(String campName, String expectedClosureDt) throws InterruptedException{
		
		LoginPage lpObj= new LoginPage(driver);
		lpObj.enterUserName(userName);
		lpObj.enterPassword(password);
		lpObj.clickLogin();
		
		Campaigns campObj=new Campaigns(driver);
		campObj.createCampaign(campName, expectedClosureDt);
		
		
		if (campObj.iscampaignCreated()==true){
			lpObj.clickLogout();			
			Assert.assertTrue(true);
			logger.info("campaign created & test case passed");
			}
		else{
			Assert.assertTrue(false);
			logger.info("campaign creation error");
			
		}
		
			
	}
	
	@DataProvider(name = "campaignData")
	String[][] getData() throws IOException {
		String path = System.getProperty("user.dir")+ "/src/test/java/com/vTiger/testData/LoginData.xlsx";
		int rownum = XLUtils.getRowCount(path, "Sheet2");
		int colcount = XLUtils.getCellCount(path, "sheet2", 1);
		String campData[][] = new String[rownum][colcount];
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				campData[i - 1][j] = XLUtils.getCellData(path, "Sheet2", i, j);
			}

		}
		return campData;
	}
	
	
}
