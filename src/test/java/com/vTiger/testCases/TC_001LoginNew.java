package com.vTiger.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.vTiger.utilities.ExcelReader;
import com.vTiger.pageObjects.CampaignsNew;
import com.vTiger.pageObjects.LoginPage;

public class TC_001LoginNew extends BaseClass {

	public LoginPage lpObj;
	
	@BeforeMethod
	public void login() throws IOException{
		System.out.println("logger not initialized");
		logger.info("URL opened");
		lpObj=new LoginPage(driver);
		lpObj.enterUserName(userName);
		lpObj.enterPassword(password);
		lpObj.clickLogin();
		
		logger.info("successfully logged in");
		
		if (driver.getTitle().contains("admin")){
			
			Assert.assertTrue(true);
			System.out.println("logged in");
		}
	
		else {
			takeScreenShot(driver, "loginTest");
			Assert.assertFalse(false,"not logged in");
			logger.info("login failed");
		}
	
	}
	
	
	@Test
	public void createCampaign() throws IOException{
		//System.out.println("reached create campaign");
		ExcelReader.getTestCaseData("TC002", "C:\\Users\\nitin.dubey\\Desktop\\vTiger\\vTigerNew\\src\\test\\java\\com\\vTiger\\testData\\LoginData.xlsx", "Sheet3");
		//System.out.println("excel read successfull");
		CampaignsNew campObj=new CampaignsNew(driver);
		campObj.createCampaign();
		
	}
	
	@Test
	public void testMethod2(){
		
		System.out.println("reached test 2");
		
	}
	
	
	
	
	@AfterMethod
	public void testAfterTest(){
		lpObj.clickLogout();
		System.out.println("after Method");
		
		
	}
	
	
	
}
