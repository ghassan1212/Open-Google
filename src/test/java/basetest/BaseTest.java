package basetest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import selenium.SeleniumDriverFactory;

// The base test encapsulates the setup/teardown, and data fetching
// that we expect every single test to have access to.
// super test class to be used/extended by child test classes 

public class BaseTest {
	
	protected static int failCount = 0;
	protected static String passDir;
	protected static String failDir;
	protected static String mainDir;
	protected static String testName = "";
	protected static ExtentReports extent = null;
	public static ExtentTest test = null;
	protected static ExtentTest parent = null;
	
	protected static String TestResultDirectory;
	public static String callerClass = "";
	public static String callerMethod = "";
	protected static ITestResult testInfo;
	
	protected static WebDriver driver;
	protected static String mainDirectory;
	protected static String userName;
	protected static String machineName;
	protected static String operatingSystem;
	protected static String browserName;
	
	static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
	static Date date = new Date(); 
	static String dateTime = formatter.format(date);
	
	@BeforeSuite
	public void baseSuiteInit() throws IOException {
		oneTimeSetup();
	}
	
	@BeforeClass
  	public void baseClassInit(ITestContext context) throws IOException, JsonIOException, JsonSyntaxException, Exception
  	{	
		// As a hack, if we're running an individual class in test NG,
		// we make sure the one time setup happens by checking first that
		// our extent report is null.
		if(extent == null) {
			oneTimeSetup();
		  }
		
		// Configure the test name in the report.
		parent = extent.createTest(this.getClass().getSimpleName());
		
		// Initialize the driver.
		driver = SeleniumDriverFactory.chromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.switchTo().activeElement().sendKeys(Keys.CONTROL + "0");

  	}
  			 
	@BeforeMethod
  	public void baseTestInit(ITestResult testInfo) throws IOException
  	{
  		setup(testInfo);
	}
	  
  	@AfterClass
  	public void afterClass() throws IOException {
  		// Close and quit the browser when we're all tests.
		driver.close();
	  }

	  @AfterMethod
	  public void AfterMethod(ITestResult result) {

	  
		  if (result.getStatus() == ITestResult.FAILURE) {
			  test.log(Status.FAIL, 
					  MarkupHelper.createLabel(result.getName() +" " + result.getClass() + " " + result.getTestName()
							  + " Test case FAILED due to below issues:", ExtentColor.RED));
			 test.fail(result.getThrowable());
			  

		  } else if (result.getStatus() == ITestResult.SUCCESS) {
			  test.log(
					  Status.PASS,
					  MarkupHelper.createLabel(result.getName()
							  + " Test Case PASSED", ExtentColor.GREEN));
		  } else {
			  test.log(
					  Status.SKIP,
					  MarkupHelper.createLabel(result.getName()
							  + " Test Case SKIPPED", ExtentColor.ORANGE));
			  test.skip(result.getThrowable());
		  }
	  	}
			
	


  	@AfterSuite
  	public void afterSuite() throws IOException
  	{
		// Flush the report when we're totally finished.
		extent.flush();
		driver.quit();

	}
  	
  	public void oneTimeSetup() throws IOException {
		// Set the system data.
		mainDirectory = System.getProperty("user.dir");
        userName = System.getProperty("user.name");
        machineName = System.getenv("COMPUTERNAME");
        operatingSystem = System.getProperty("os.name");
		
		// Load in the test data.
		dataTest();


		// Setup the report.
		mainDir = "target\\extent-reports";
        extent = new ExtentReports();
		setReport(extent, mainDir);
  	}
	
     /**
      *Set up the driver  
     * @throws IOException 
      */
     public void setup(ITestResult testInfo) throws IOException
     {   	 
    	 BaseTest.testInfo = testInfo;
         callerMethod = testInfo.getMethod().getMethodName();
         test = parent.createNode(callerMethod).info("..... SetUp .....");
         setLog();        
     }

     public void setLog()
     {
         testName = (" Feature: " + this.getClass().getSimpleName() + " - Scenario: " + callerMethod);
         test.log(Status.INFO, "Test Executed by: " + userName);
         test.log(Status.INFO, "Machine Name: " + machineName);
         test.log(Status.INFO, "Operating System: " + operatingSystem);
         test.log(Status.INFO, "Browser Name: " + browserName);
         test.log(Status.INFO, " ... Test Execution ... ");
         test.log(Status.INFO, "Start Testing: " + testName);
         System.out.println("Start Testing: " + testName);
     }
     
  	public void dataTest() throws IOException {
  		
  				
		//This code is to set the location of the HTML output
		System.out.println("HTML Summary Test Results Report: " + mainDirectory + "\\test-output\\emailable-report.html");
		System.out.println("HTML Detailed Test Results Report: " + "target/extent-reports");

		System.out.println("Java Script Location: " + mainDirectory);
		System.out.println("User Name: " + userName);
		System.out.println("Machine Name: " + machineName);
		System.out.println("Operating System Name: " + operatingSystem);		
  	}
  	
	 private void setReport(ExtentReports extent, String dir) throws IOException
     {
    	new File(dir).mkdirs();
    	ExtentSparkReporter htmlReport = new ExtentSparkReporter(dir +  "\\" + "ResultsReport.html");
        extent.attachReporter(htmlReport);
	}
	 


}
