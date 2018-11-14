package common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

	private WebDriver driver;

	@BeforeClass
	public void beforeSuite() {
	/*	System.setProperty("headless", "true"); // You can set this property
												// elsewhere
		String headless = System.getProperty("headless");

		if ("true".equals(headless)) {
			System.setProperty("webdriver.chrome.driver", "./src/test/resources/driver/chromedriver.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");
			driver = new ChromeDriver(chromeOptions);
		} else {
			driver = new ChromeDriver();
		}*/
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/driver/chromedriver.exe");
		driver = new ChromeDriver();
		//driver.get("http://newtours.demoaut.com/mercurywelcome.php");
		//driver.get("http://demo.guru99.com/V1/html/Managerhomepage.php");
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
