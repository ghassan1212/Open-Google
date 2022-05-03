package pages;


import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EnglishBasePage 
{
	
	protected WebDriver driver;
    final static int IMPLICIT_WAIT_TIME = 30;
	//private static final String Enter = null;

    public EnglishBasePage(WebDriver drv) {
        driver = drv;

        // Every time we try to reference an element on the page, the AjaxElementLocatorFactory
        // ensures that we wait up to the configured timeout (5 seconds currently), before
        // throwing element not found. This avoids 99% of explicit wait() functions.
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60), this);
    }
    
	//Wait until the page is loaded
    protected void waitLoadingPage(WebDriver driver) {
    	new WebDriverWait(driver, IMPLICIT_WAIT_TIME).until((ExpectedCondition<Boolean>) wd ->
		((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }
      
    // ESAS click on search box
    @FindBy(xpath = "/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input")
	public WebElement SearchBox;

    /*
    
     
   // ESAS enter the user name in the text box
    @FindBy(xpath = "//*[@id=\"token1\"]")
	public WebElement username;
    
  // ESAS enter the password in the text box
    @FindBy(xpath = "//*[@id=\"token2\"]")
	public WebElement password;
    
  // ESAS click on SignIn Button
    @FindBy(xpath = "//*[@id=\"login\"]/div[3]/div/button")
    private WebElement SignIn;

 // ESAS click on Continue Button
    @FindBy(xpath = " //*[@id=\"continue\"]")
    private WebElement Continue;
    
 // ESAS click on Continue Button
    @FindBy(xpath = "/html/body/header/div[2]/div/div/nav/ul/li/a")
    private WebElement Sign_out;
    
    
 
    // click function on the SearchBox button
    public EnglishBasePage SearchBox(){
    	SearchBox.sendKeys(Keys.ENTER);   
        waitLoadingPage(driver);
        return new EnglishBasePage(driver);
        
    }
    
   // click function on the Continue
   public EnglishBasePage Continue(){
	   Continue.click();
       waitLoadingPage(driver);
       return new EnglishBasePage(driver);
  }
    
 // click function on the Sign_out
 public EnglishBasePage Sign_out(){
    	Sign_out.click();
        waitLoadingPage(driver);
        return new EnglishBasePage(driver);
 }
    
    // click function on the SignIn
    public EnglishBasePage SignIn(){
        SignIn.click();
        waitLoadingPage(driver);
        return new EnglishBasePage(driver);
    }
 
    // Just in case we want to take a screenshot of things in the future, 
    // like failures in headless mode.
    public void takeScreenshot(String filePath) {
        TakesScreenshot scrShot = (TakesScreenshot)driver;
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(filePath);
        try {
            FileUtils.copyFile(srcFile, destFile);
        }
        catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        }
        
        
    }
    
  */ 
}