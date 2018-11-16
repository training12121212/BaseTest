package tests;

import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import common.BaseTest;
import common.Excel;
import pages.WebTourHomepage;


public class Sample2 extends BaseTest {
	ExtentTest logger;
  @Test
  public void homepageTests() throws InterruptedException, IOException {
	  logger = extent.createTest("TC002");
	  Excel excel =new Excel(TESTDATAPATH+"TestScripts.xlsx","TestData","TC002");
	  WebTourHomepage loginPage = new WebTourHomepage(getDriver(),logger);
	  loginPage.login(excel.getData("FROM"),excel.getData("TO"));
	  captureScreenshot(logger, getDriver(), "SCreen2");
  }
}
