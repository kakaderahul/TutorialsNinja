package Utilities;

import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Base.Base;

public class ListenerUtil extends Base implements ITestListener{
	
	ExtentSparkReporter reporter;
	ExtentReports extentReport;
	ExtentTest etest;

	@Override
	public void onStart(ITestContext context) {
		
		reporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/target/Reports/index.html");
		reporter.config().setDocumentTitle("Rahuls Extent Report");
		reporter.config().setReportName("Extent Report");
		
		extentReport=new ExtentReports();
		extentReport.attachReporter(reporter);
		extentReport.setSystemInfo("OS", "Window");
		extentReport.setSystemInfo("Tested By","RK");
	}

	
	//// 
	
	@Override
	public void onFinish(ITestContext context) {
		
		extentReport.flush();
		
	}
	
	/////////////
	
	@Override
	public void onTestStart(ITestResult result) {
		
		etest=extentReport.createTest(result.getName());
		
	}

	///////////
	
	@Override
	public void onTestSuccess(ITestResult result) {
		
	}
	

	@Override
	public void onTestFailure(ITestResult result) {
		
		etest.fail(result.getName()+" test is failed.");
		try {
			String screenShotPath=takeScreenshot(result.getName());
			etest.addScreenCaptureFromPath(screenShotPath);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("exception in ot test filure method....in listeners");
			e.printStackTrace();
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
	
	}

	

	
	
	
	
}
