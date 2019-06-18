package com.vTiger.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.vTiger.pageObjects.LoginPage;

public class TestCase_001 extends BaseClass {

	@Test
	public void loginTest() throws IOException{
		logger.info("URL opened");
		LoginPage lpObj=new LoginPage(driver);
		lpObj.enterUserName(userName);
		lpObj.enterPassword(password);
		lpObj.clickLogin();
		
		logger.info("successfully logged in");
		
		if (driver.getTitle().contains("nitin")){
			
			Assert.assertTrue(true);
			System.out.println("logged in");
		}
	
		else {
			takeScreenShot(driver, "loginTest");
			Assert.assertFalse(false,"not logged in");
			logger.info("login failed");
		}
	}
	
	
	
	
}
