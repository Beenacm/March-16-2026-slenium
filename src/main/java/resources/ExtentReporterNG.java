package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public  ExtentReports ExtentReporing() {
		ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//src//reporter");
		reporter.config().setDocumentTitle("Automation testing");
		reporter.config().setReportName("UI Testing");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Beena");
		return extent;
	}

}
