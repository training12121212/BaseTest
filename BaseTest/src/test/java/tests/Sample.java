package tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import common.BaseTest;
import common.Excel;
import pages.WebTourHomepage;


public class Sample extends BaseTest {
	ExtentTest logger;
  @Test
  public void homepageTests() throws InterruptedException, IOException {
	   logger=extent.createTest("TC001");
	   Excel excel =new Excel(TESTDATAPATH+"TestScripts.xlsx","TestData","TC001");
	  WebTourHomepage loginPage = new WebTourHomepage(getDriver(),logger);
	  loginPage.login(excel.getData("FROM"),excel.getData("TO"));
	  captureScreenshot(logger, getDriver(), "SCreen1");
	  
	  
  }
}
