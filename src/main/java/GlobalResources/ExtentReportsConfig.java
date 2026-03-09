package GlobalResources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsConfig 
{
	public static ExtentReports getExtentReportsObj()
	{
		 String path = System.getProperty("user.dir")+"//reports//index.html";
		 ExtentSparkReporter reporter= new ExtentSparkReporter(path);
		 reporter.config().setReportName("WebAutomation Result");
		 
		 ExtentReports report=new ExtentReports();
		 report.attachReporter(reporter);
		 report.setSystemInfo("Tester", "Shubham");
		 
		 return report;
	}

}
