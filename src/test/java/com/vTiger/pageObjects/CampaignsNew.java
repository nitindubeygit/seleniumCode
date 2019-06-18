package com.vTiger.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.vTiger.testCases.BaseClass;

public class CampaignsNew extends BaseClass {

	WebDriver ldriver;
	
	public CampaignsNew(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);

	}
	
	
	@FindBy(xpath = "//a[contains(text(),'Marketing')]")
	WebElement marketingLnk;
	
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
	
	
	public void moveTomarketingLnk(){
		mouseHover(marketingLnk);
			
	}
	
	public void clickcampaignsLnk(){
			
		campaignsLnk.click();
		//Thread.sleep(3000);
		
		
	}
	
	
	public void createCampaign(){
		moveTomarketingLnk();
		clickcampaignsLnk();
		createCampaignsLnk.click();
		enterText(campaignNameTxt, "campaignNameTxt");
		assignedToGrpRadio.click();
		enterText(expectedClosureCalendar, "expectedClosureCalendar");
		//click(assignto_dropDown);
		//selectFromList(assignto_dropDown, "assignto_dropDown");		
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
