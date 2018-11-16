package common;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseTest {

	private WebDriver driver;
	public ExtentHtmlReporter htmlreport;
	public ExtentReports extent;
	public ExtentTest logger;

	@Parameters({ "browser", "ipAddress" })
	@BeforeClass
	public void beforeSuite(@Optional("local") String browser, @Optional("") String ipAddress)
			throws MalformedURLException {
		DesiredCapabilities cap = null;
		switch (browser) {
		case "edge":
			cap = DesiredCapabilities.edge();
			cap.setBrowserName("MicrosoftEdge");
			break;
		case "chrome":
			cap = DesiredCapabilities.chrome();
			cap.setBrowserName("chrome");
			break;
		case "local":
			System.setProperty("webdriver.chrome.driver", "./src/test/resources/driver/chromedriver.exe");
			driver = new ChromeDriver();
		}

		if (!(browser.equalsIgnoreCase("Local"))) {
			driver = new RemoteWebDriver(new URL(ipAddress + "/wd/hub"), cap);
		}

		// System.setProperty("webdriver.edge.driver",
		// "./src/test/resources/driver/MicrosoftWebDriver.exe");

		// driver = new EdgeDriver();
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

	@BeforeSuite
	public void reportInitiliaze() {
		htmlreport = new ExtentHtmlReporter("./reports/GROUPON_Results.html");
		htmlreport.config().setDocumentTitle("Groupon Regression Testing");
		htmlreport.config().setReportName("[GROUPON]");
		extent = new ExtentReports();
		extent.attachReporter(htmlreport);

	}

	/*
	 * @BeforeMethod public void beforeMethod(Method method){
	 * 
	 * }
	 */
	@AfterMethod
	public void afterMethod() {
		extent.flush();
	}

	public void captureScreenshot(ExtentTest logger, WebDriver driver, String name) throws IOException {
		Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS, 100, true).withName(name).save("./reports");
		logger.addScreenCaptureFromPath(System.getProperty("user.dir") + "/reports/" + name + ".png");

	}
}
