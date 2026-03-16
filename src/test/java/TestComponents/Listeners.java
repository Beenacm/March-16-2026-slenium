package TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {

	ExtentReporterNG extentReporter = new ExtentReporterNG();
	ExtentReports reports = extentReporter.ExtentReporing();
	ExtentTest test;
	WebDriver driver;
	ThreadLocal<ExtentTest> threadId=new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
	
		test = reports.createTest(result.getMethod().getMethodName());
		threadId.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		threadId.get().log(Status.PASS, "Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		threadId.get().fail(result.getThrowable());
		String filePath = null;

		try {
			 driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			filePath = getScreenShot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		threadId.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		reports.flush();
	}

}
