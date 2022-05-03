package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class SeleniumDriverFactory {


	// invoke chrome web browser via the driver in path outlined below
	public static WebDriver chromeDriver() {
		String driverDirectory = System.getProperty("user.dir")+"\\src\\main\\drivers\\";


				//Driver executable from here: http://chromedriver.storage.googleapis.com/index.html
				System.setProperty("webdriver.chrome.driver", driverDirectory + "chromedriver.exe");
				ChromeOptions chromeOptions = new ChromeOptions();
				//chromeOptions.setHeadless(true);
				chromeOptions.setAcceptInsecureCerts(true);
				return new ChromeDriver(chromeOptions);
		
	}
}