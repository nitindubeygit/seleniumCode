package com.vTiger.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Campaigns {

	WebDriver ldriver;
	
	public Campaigns(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);

	}
	
	
	@FindBy(xpath = "//a[contains(text(),'Marketing')]")
	WebElement marketingLnk;
	
	/*@FindBy(xpath = "//a[contains(text(),'Campaigns')]")
	WebElement campaignsLnk;*/
	
	@FindBy(xpath = "//table[@class='hdrTabBg']//a[contains(text(),'Marketing')]")
	WebElement campaignsLnk;

	@FindBy(xpath = "//img[@title='Create Campaign...']")
	WebElement createCampaignsLnk;
	
	@FindBy(xpath = "//input[@value='  Save  ']")
	WebElement saveBtn;
	
	@FindBy(xpath = "//input[@type='button']")
	WebElement cancelBtn;
	
	@FindBy(xpath = "//input[@name='campaignname']")
	WebElement campaignNameTxt;
	
	@FindBy(xpath = "(//input[@name='assigntype'])[2]")
	WebElement assignedToGrpRadio;
	
	@FindBy(xpath = "//input[@name='closingdate']")
	WebElement expectedClosureCalendar;
	
	@FindBy(xpath = "//span[contains(text(),'Campaign Information')]")
	WebElement createdCampaign;
	
	
	public void moveTomarketingLnk() throws InterruptedException{

			Actions act=new Actions(ldriver);
			act.moveToElement(marketingLnk).build().perform();
			//Thread.sleep(20000);
			
	}
	
	public void clickcampaignsLnk() throws InterruptedException{
			
		campaignsLnk.click();
		//Thread.sleep(3000);
		
		
	}
	
	
	public void createCampaign(String campName, String expectedClosureDt) throws InterruptedException{
		moveTomarketingLnk();
		clickcampaignsLnk();
		createCampaignsLnk.click();
		campaignNameTxt.sendKeys("campName");
		assignedToGrpRadio.click();
		expectedClosureCalendar.sendKeys("expectedClosureDt");
		saveBtn.click();
				
	}
	
	
	
public boolean iscampaignCreated(){
		
		try{
		createdCampaign.getText();
		
		return true;
		}
		catch (Exception e){
			System.out.println("exception in campaign creation");
			return false;
		}
		
	
}
	
}
