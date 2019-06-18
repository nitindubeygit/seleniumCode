package com.vTiger.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Reporting extends TestListenerAdapter {

	//public WebDriver driver;

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	
	public void onStart(ITestContext testContext){
		
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //time stamp
		String repName="Test-Report-"+timestamp+".html"; 
		System.out.println(repName);
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/"+ repName);
		htmlReporter.loadConfig(System.getProperty("user.dir")+"/extent-config.xml");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Hostnam", "localhost");
		extent.setSystemInfo("OS", "Win7");
		extent.setSystemInfo("tester Name", "Nitin");
		
	}
	
	
	public void onTestSuccess(ITestResult tr){
		test=extent.createTest(tr.getName());
		test.log(Status.PASS, MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN));
		//System.out.println("PASSED");
			
	}
	
	public void onTestFailure(ITestResult tr){
		test=extent.createTest(tr.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));
		String screenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		File finaldestination = new File(screenshotPath);
		if(finaldestination.exists()){
			try{
				
				test.fail("Screenshot is below:" + test.addScreenCaptureFromPath(screenshotPath));
							}
			catch (IOException e){
				e.printStackTrace();
			}
		}
		
	}
	
	
	public void onTestSkipped(ITestResult tr){
		test=extent.createTest(tr.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(),ExtentColor.YELLOW));
			
	}
	
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
		//System.out.println("report written");

	}

	
/*	public void tearDown(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "TEST CASE FAILED IS" + result.getName()); // to add name in extent report
			test.log(Status.FAIL, "TEST CASE FAILED IS" + result.getThrowable()); // to add error/exception
			String screenshotPath = Reporting.getScreenshot(driver,	result.getName());
			test.addScreenCaptureFromPath(screenshotPath);

		} else if (result.getStatus() == ITestResult.SKIP) {
			
			test.log(Status.SKIP, "Test Case Skipped is " + result.getName());

		} else if (result.getStatus() == ITestResult.SUCCESS) {

			test.log(Status.PASS, "Test Case Passed is " + result.getName());
		}

	}*/

/*	public static String getScreenshot(WebDriver driver, String screenshotname)	throws IOException {

		String datename = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot tss = (TakesScreenshot) driver;
		File source = tss.getScreenshotAs(OutputType.FILE);

		// after execution you could see a folder failed tests screenshots under
		// src folder
		String destination = System.getProperty("user.dir") + "/Screenshots/"+ screenshotname + datename + ".png";
		File finaldestination = new File(destination);
		// System.out.println(dest);
		FileUtils.copyFile(source, finaldestination);
		return destination;

	}*/

	//old code without framework
		/*public void setExtent() {
			htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/myReport.html");
			htmlReporter.config().setDocumentTitle("Automation Report"); // Title of
																			// the
																			// report
			htmlReporter.config().setReportName("Functional Report"); // Name of
																		// report
			htmlReporter.config().setTheme(Theme.DARK); // color theme of report

			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
			extent.setSystemInfo("Hostnam", "localhost");
			extent.setSystemInfo("OS", "Win7");
			extent.setSystemInfo("tester Name", "Nitin");

		}*/
	
}
