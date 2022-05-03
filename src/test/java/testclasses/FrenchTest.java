
package testclasses;

import java.io.FileReader;
import java.io.IOException;

import com.aventstack.extentreports.Status;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import basetest.BaseTest;
import pages.EnglishBasePage;


@Listeners(TestFailureListener.class)

public class FrenchTest extends BaseTest{


    @Parameters({"frenchUrl"})
    @BeforeMethod(dependsOnMethods={"baseTestInit"}) 
    public void frenchClassInit(ITestResult testInfo, String frenchhUrl) throws IOException
  	{	
      driver.navigate().to(frenchhUrl);

    }
    
    @Test(priority = 0, alwaysRun = true)
     public void Test_French_Page() throws IOException, Throwable
    {
    	
    	//parsing file "JSONExample.json" 
    	  Object obj = new JSONParser().parse(new FileReader("C:\\ESASNew\\cppe_3\\src\\test\\java\\resources\\Json.json")); 
    			    
    	  // typecasting obj to JSONObject 
    	  JSONObject jo = (JSONObject) obj; 
    	
        test.log(Status.INFO, "======= Start French Login Testing =======" + callerMethod);
        
        EnglishBasePage homePage = new EnglishBasePage(driver);
        
        String Word = (String) jo.get("Word");
        
        homePage.SearchBox.sendKeys(Word);
        test.log(Status.INFO, "Verify the GCKey button is clickable \r\n" + "Successfully " + callerMethod);
        
        homePage.SearchBox.sendKeys(Keys.ENTER);     
        
        Assert.assertEquals(driver.getTitle(), "CléGC - Ouvrir une session / Enregistrez-vous");
        System.out.println("I'm in line #" + new Exception().getStackTrace()[0].getLineNumber());
             
        String UserName = (String) jo.get("UserName"); 
        String Password = (String) jo.get("Password"); 
        
        homePage.username.sendKeys(UserName);
        test.log(Status.INFO, "Verify that the User Name had been enteredin test box \r\n" + "Successfully " + callerMethod);
        
        homePage.password.sendKeys(Password);
        test.log(Status.INFO, "Verify that the Password had been enteredin test box \r\n" + "Successfully " + callerMethod);
        
        homePage.SignIn();
        test.log(Status.INFO, "Verify the Sign In button is clickable \r\n" + "Successfully " + callerMethod);
        
        Assert.assertEquals(driver.getTitle(), "CléGC - Ouvrir une session / Enregistrez-vous");
        System.out.println("I'm in line #" + new Exception().getStackTrace()[0].getLineNumber());
        
        homePage.Continue();
        test.log(Status.INFO, "Verify the Continue button is clickable \r\n" + "Successfully " + callerMethod);
        
        Assert.assertEquals(driver.getTitle(), "Accueil");
        System.out.println("I'm in line #" + new Exception().getStackTrace()[0].getLineNumber());
        
        homePage.Sign_out();
        test.log(Status.INFO, "Verify the Sign_out_outbutton button is clickable\r\n" + "Successfully " + callerMethod);
        
        Assert.assertEquals(driver.getTitle(), "Services en ligne sécurisé AAC - Déconnexion demandée");
        System.out.println("I'm in line #" + new Exception().getStackTrace()[0].getLineNumber());
       
        test.log(Status.INFO, "========= End English Login Testing ==========" + callerMethod);
            
       }

	 
       
 }
