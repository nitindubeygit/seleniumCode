package com.vTiger.testCases;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.vTiger.utilities.ReadConfig;

import freemarker.log.Logger;

public class BaseClass {

	ReadConfig readConfig = new ReadConfig();
	public String baseURL = readConfig.getBaseURL();
	public String userName = readConfig.getuserName();
	public String password = readConfig.getPasswor();
	public static WebDriver driver;
	public static Logger logger;
	
	
	
	public static List<String> TCData;   //This variable stores the list of all test data, value for this comes from excel reader class


	public static String getfieldValue(String fieldName, List<String> testcasedata){
		int fieldNumber=0;
		for (int i=0; i<=testcasedata.size()-1;i++){
			String fname=testcasedata.get(i);
			if (fname != null){
				if(fname.equalsIgnoreCase(fieldName)){
					fieldNumber=i;
					break;
				}
			}
			
		}
		String fieldValue=testcasedata.get(fieldNumber+1);
		return fieldValue;
	}


	@Parameters("brName")
	@BeforeClass
	public void setup(String brName) {

		logger = Logger.getLogger("vTiger");
		PropertyConfigurator.configure("log4j.properties");

		if (brName.equalsIgnoreCase("chrome")) {

			// this picks file location from the root as complete path is
			// defined
			// System.setProperty("webdriver.chrome.driver",
			// "C:\\Users\\nitin.dubey\\Desktop\\vTiger\\myPractice\\Drivers\\chromedriver.exe");

			// this picks the file location from config.properties file using
			// method from readConfig class
			System.setProperty("webdriver.chrome.driver",readConfig.getChromePath());

			driver = new ChromeDriver();

		} else if (brName.equalsIgnoreCase("firefox")) {

			// System.setProperty("webdriver.gecko.driver",
			// "C:\\Users\\nitin.dubey\\Desktop\\vTiger\\myPractice\\Drivers\\geckodriver.exe");
			System.setProperty("webdriver.gecko.driver",readConfig.getFirefoxPath());
			driver = new FirefoxDriver();
		}

		else if (brName.equalsIgnoreCase("IE")) {

			System.setProperty("webdriver.ie.driver", readConfig.getIEPath());
			driver = new InternetExplorerDriver();
		}
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(baseURL);

	}


	
	
	@AfterClass
	public void tearDown() {

		driver.quit();

	}

	
	public void takeScreenShot(WebDriver driver, String tName) throws IOException {
		TakesScreenshot tss=(TakesScreenshot) driver;
		File srcObj=tss.getScreenshotAs(OutputType.FILE);
		File destFile=new File("screenshots/"+tName+".png");
		//System.out.println(dest);
		FileUtils.copyFile(srcObj,destFile );
		System.out.println("screenshot taken");
		logger.info("Screenshot taken");
		}

	
		
	public static void enterText(WebElement we,String fieldName){
		String fieldValue=getfieldValue(fieldName, TCData);
		we.clear();
		we.sendKeys(fieldValue);
	}
	
	public static void mouseHover(WebElement we){
		Actions act=	new Actions(driver);
		act.moveToElement(we).build().perform();
	}


	public static void switchToWindow(String fieldName){
		String fieldValue=getfieldValue(fieldName, TCData);
		Set<String> winHndlColl=driver.getWindowHandles();
		Iterator<String> hndlColl=winHndlColl.iterator();	
		while (hndlColl.hasNext()==true){

			String hndlVal=hndlColl.next();
			driver.switchTo().window(hndlVal);
			String winTitle=driver.getTitle();
			if (winTitle.equalsIgnoreCase(fieldValue)){
				break;
			}
		}
	}

	public static void scrollDownthroughJS(){
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("scroll(0, 250);");
	}

	public static void scrollUpthroughJS(){
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("scroll(0, -250);");
	}

	public static void pageUp(){
		Actions act=new Actions(driver);
		act.sendKeys(Keys.PAGE_UP);
	}

	public static void pageDown(){
		Actions act=new Actions(driver);
		act.sendKeys(Keys.PAGE_DOWN);
	}

	public static void switchToFrame(WebElement we){
		driver.switchTo().frame(we);
	}

	public static void switchBacktoMainPagefromFrame(){
		driver.switchTo().defaultContent();
	}

	public static void selectFromList(WebElement we, String fieldName){
		String fieldValue=getfieldValue(fieldName, TCData);
		Select selObj=new Select(we);
		List <WebElement> weColl=selObj.getOptions();
		for (int i=0; i<=weColl.size()-1;i++){
			WebElement wel=weColl.get(i);
			if (wel.getText().trim().equalsIgnoreCase(fieldValue)){
				wel.click();
			}
		}
	}

	

}
