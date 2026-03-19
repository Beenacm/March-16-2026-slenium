package TestComponents;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
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
    private static final Logger log = LogManager.getLogger(Listeners.class);

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
		
		try {
			 driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.error(result.getName());;
		LogEntries log1=driver.manage().logs().get(LogType.BROWSER);
		List<LogEntry>logs=log1.getAll();
		for(LogEntry e:logs) {
		log.error(e.getMessage());
		}
		
        log.error("Test Failed"+":"+result.getName());

		
        threadId.get().fail(result.getThrowable());
		String filePath = null;

		
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
