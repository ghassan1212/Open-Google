
package testclasses;

import java.io.FileReader;
import java.io.IOException;

import com.aventstack.extentreports.Status;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
//import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import basetest.BaseTest;
import pages.EnglishBasePage;


@Listeners(TestFailureListener.class)

public class EnglishTest extends BaseTest{

    @Parameters({"englishUrl"})
    @BeforeMethod(dependsOnMethods={"baseTestInit"}) 
    public void englishClassInit(ITestResult testInfo, String englishUrl) throws IOException
  	{	
      driver.navigate().to(englishUrl);

    }
    
    @Test(priority = 0, alwaysRun = true)
     public void Test_English_Page() throws IOException, Throwable
    {
    	
    	//parsing file "JSONExample.json" 
    	  Object obj = new JSONParser().parse(new FileReader("C:\\Temp\\cppe_3\\src\\test\\java\\resources\\Json.json")); 
    			    
    	  // typecasting obj to JSONObject 
    	  JSONObject jo = (JSONObject) obj; 
    	
        test.log(Status.INFO, "======= Start English Login Testing =======" + callerMethod);
        
        EnglishBasePage homePage = new EnglishBasePage(driver);
       
        
        homePage.SearchBox.sendKeys((String) jo.get("Word"));
        
        test.log(Status.INFO, "Verify the GCKey button is clickable \r\n" + "Successfully " + callerMethod);
        
            
        Thread.sleep(9000000);
         
       
   
       
        
        /*
        Assert.assertEquals(driver.getTitle(), "GCKey - Sign In / Sign Up");
        System.out.println("I'm in line #" + new Exception().getStackTrace()[0].getLineNumber());
             
        //String UserName = (String) jo.get("UserName"); 
        homePage.username.sendKeys((String) jo.get("UserName"));
        test.log(Status.INFO, "Verify that the User Name had been enteredin test box \r\n" + "Successfully " + callerMethod);
        
        homePage.password.sendKeys((String) jo.get("Password"));
        test.log(Status.INFO, "Verify that the Password had been enteredin test box \r\n" + "Successfully " + callerMethod);
        
        homePage.SignIn();
               
        Assert.assertEquals(driver.getTitle(), "GCKey - Sign In / Sign Up");
        System.out.println("I'm in line #" + new Exception().getStackTrace()[0].getLineNumber());
        test.log(Status.INFO, "Verify the Sign In button is clickable \r\n" + "Successfully " + callerMethod);
        
        homePage.Continue();
        test.log(Status.INFO, "Verify the Continue button is clickable \r\n" + "Successfully " + callerMethod);
        
        Assert.assertEquals(driver.getTitle(), "Home");
        System.out.println("I'm in line #" + new Exception().getStackTrace()[0].getLineNumber());
        
        homePage.Sign_out();
        test.log(Status.INFO, "Verify the Sign_out_outbutton button is clickable\r\n" + "Successfully " + callerMethod);
        
        Assert.assertEquals(driver.getTitle(), "AAFC Secure Online Services - Logout requested");
        System.out.println("I'm in line #" + new Exception().getStackTrace()[0].getLineNumber());
       
        test.log(Status.INFO, "========= End English Login Testing ==========" + callerMethod);
          */  
       }     
       
 }
