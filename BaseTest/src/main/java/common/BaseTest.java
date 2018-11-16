package common;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
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
	public static ExtentHtmlReporter htmlreport;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public final String TESTDATAPATH = ".\\src\\test\\java\\testData\\";
	public final String REPORT_NAME = "Apsrtc_Results";
	public final String REPORT_TITLT = "Apsrtc Regression Testing";
	public final String REPORT_HEADING = "[APSRTC]";

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
		driver.get("https://apsrtconline.in/oprs-web/");
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

	public ExtentTest getLogger() {
		return logger;
	}

	public ExtentReports getExtent() {
		return extent;
	}

	@BeforeSuite
	public void reportInitiliaze() throws IOException {
		cleanUpDirectory("./reports");
		htmlreport = new ExtentHtmlReporter("./reports/" + REPORT_NAME + ".html");
		htmlreport.config().setDocumentTitle(REPORT_TITLT);
		htmlreport.config().setReportName(REPORT_HEADING);
		extent = new ExtentReports();
		extent.attachReporter(htmlreport);

	}

	@AfterSuite
	public void afterSuite1() throws IOException {
		File htmlFile = new File(System.getProperty("user.dir")+"/reports/" + REPORT_NAME + ".html");
		Desktop.getDesktop().browse(htmlFile.toURI());
	}

	public void cleanUpDirectory(String directory) throws IOException {
		Files.walk(Paths.get(directory)).filter(Files::isRegularFile).map(Path::toFile).forEach(File::delete);
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
