package sqa.framework.steps;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import sqa.framework.utilities.CommonUtilities;
import sqa.framework.utilities.ExtentManager;


public class HooksSteps extends CommonUtilities{

    private ExtentReports extent = ExtentManager.getInstance();
    private ExtentTest test;
    

    @Before
    public void initiateTest() {
        System.out.println("Starting Test Here. Opening Browser.");
        super.setupBrowser();
        test = extent.createTest(getClass().getSimpleName());
    }

    @After
    public void closeTest(Scenario scenario) {	
    	
        if (scenario.getStatus().equals(Status.FAILED)) {
        	scenario.attach(getScreenShot(), "image/png", scenario.getName());
            
            test.fail("Test Case Failed: " + scenario.getName());

            String exceptionMessage = scenario.getStatus().toString() + ": " + scenario.getName();
            test.fail(exceptionMessage);
            scenario.getStatus();
            
        } else {
            test.pass("Test Case Passed: " + scenario.getName());
        }
        

        // Quit browser after the test is done.
        System.out.println("Closing Test here. Quitting the browser.");
        super.quitBrowser();

        extent.flush();
    }
    
}