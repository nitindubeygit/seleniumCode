package com.vTiger.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver ldriver;

	public LoginPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);

	}

	@FindBy(name = "user_name")
	@CacheLookup
	WebElement userName;

	@FindBy(xpath = "//input[@name='user_password']")
	WebElement password;

	@FindBy(xpath = "//input[@name='Login']")
	WebElement btnLogin;
	
	@FindBy(xpath = "//a[contains(text(),'Sign Out')]")
	WebElement logOut;


	public void enterUserName(String uname) {
		userName.clear();
		userName.sendKeys(uname);

	}

	public void enterPassword(String pass) {
		password.clear();
		password.sendKeys(pass);

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