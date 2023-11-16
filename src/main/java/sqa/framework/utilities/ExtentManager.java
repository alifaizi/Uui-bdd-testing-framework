package sqa.framework.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.*;

public class ExtentManager {
	
	private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            extent = createInstance();
        }
        return extent;
    }

    private static ExtentReports createInstance() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("target/ExtentReports/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        return extent;
    }
}
