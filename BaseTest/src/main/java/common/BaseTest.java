package common;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {

	private WebDriver driver;
@Parameters({"browser","ipAddress"})
	@BeforeClass
	public void beforeSuite(String browser,String ipAddress) throws MalformedURLException {

	//	System.setProperty("webdriver.chrome.driver", "./src/test/resources/driver/chromedriver.exe");
		/*DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setBrowserName("chrome");*/
	DesiredCapabilities cap = null;
	   switch(browser){
	   case "edge":
		   cap = DesiredCapabilities.edge();
			cap.setBrowserName("MicrosoftEdge");
		   break;
	   case "chrome":
		   cap = DesiredCapabilities.chrome();
			cap.setBrowserName("chrome");
		   break;	   
	   }
	   	
		/*DesiredCapabilities cap = DesiredCapabilities.edge();
		cap.setBrowserName("MicrosoftEdge");*/
		//String Node = "http://192.168.1.3:5567/wd/hub";
		driver = new RemoteWebDriver(new URL(ipAddress+"/wd/hub"), cap);
		
		
		//System.setProperty("webdriver.edge.driver", "./src/test/resources/driver/MicrosoftWebDriver.exe");
		
		//driver = new EdgeDriver();
		driver.get("http://newtours.demoaut.com/mercurywelcome.php");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@AfterClass
	public void afterSuite() {
		if (null != driver) {
			driver.close();
			driver.quit();
		}
	}

	public WebDriver getDriver() {
		return driver;
	}
}
