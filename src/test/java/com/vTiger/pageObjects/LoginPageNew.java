package com.vTiger.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vTiger.testCases.BaseClass;

public class LoginPageNew extends BaseClass{

	WebDriver ldriver;

	public LoginPageNew(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);

	}

	@FindBy(name = "user_name")
	@CacheLookup
	WebElement userNametxtBox;

	@FindBy(xpath = "//input[@name='user_password']")
	WebElement passwordtxtBox;

	@FindBy(xpath = "//input[@name='Login']")
	WebElement btnLogin;
	
	@FindBy(xpath = "//a[contains(text(),'Sign Out')]")
	WebElement logOut;


	public void enterUserName(String uname) {
		userNametxtBox.clear();
		userNametxtBox.sendKeys(uname);

	}
	
	
	public void enterPass(String pass) {
		passwordtxtBox.clear();
		passwordtxtBox.sendKeys(pass);

	}

	
	public void clickLogin() {

		btnLogin.click();

	}
	
	public void clickLogout(){
		
		logOut.click();
	}
	
	
public boolean isLoggedin(){
		
		try{
		clickLogout();
		return true;
		}
		catch (Exception e){
			return false;
		}
	
}
}