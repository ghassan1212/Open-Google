package testclasses;


import com.aventstack.extentreports.Status;
import basetest.BaseTest;
import org.testng.ITestContext;
import org.testng.ITestListener;		
import org.testng.ITestResult;		

// This listener class is how we make sure we control
// what happens when a test fails for any reason.
// So all failures get processed/logged here.
public class TestFailureListener implements ITestListener						
{		
    public void onFinish(ITestContext Result) 					
    {		
                		
    }		

    public void onStart(ITestContext Result)					
    {		
            		
    }		

    public void onTestFailedButWithinSuccessPercentage(ITestResult Result)					
    {		
    		
    }		

    // When Test case get failed, this method is called.		
    public void onTestFailure(ITestResult Result) 					
    {		
        Throwable e = Result.getThrowable();
        StackTraceElement[] st = e.getStackTrace();
        StackTraceElement ste = GetItrdsTraceElement(st);

        BaseTest.test.log(Status.FAIL, e.getMessage() + " " + ste.getClassName() + " " + ste.getMethodName() + " at line: " + ste.getLineNumber());					
    }
    
    // This function parses the stack trace elements from an exception,
    // finds the top level one that belongs to one of our test classes,
    // and returns that so we can use it in our reporting for which
    // line something failed on.
    private StackTraceElement GetItrdsTraceElement(StackTraceElement[] st) 
    {
        for(StackTraceElement ste : st) {
            if(ste.getClassName().toLowerCase().contains("testclasses")) {
                return ste;
            }
        }
        
        return null;
    }

    // When Test case get Skipped, this method is called.		
    public void onTestSkipped(ITestResult Result)					
    {		
        //System.out.println("The name of the testcase Skipped is :"+Result.getName());					
    }		

    // When Test case get Started, this method is called.		
    public void onTestStart(ITestResult Result)					
    {		
        //System.out.println(Result.getName()+" test case started");					
    }		

    // When Test case get passed, this method is called.		
    public void onTestSuccess(ITestResult Result)					
    {		
        //System.out.println("The name of the testcase passed is :"+Result.getName());					
    }
}