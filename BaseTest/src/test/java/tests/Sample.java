package tests;

import org.testng.annotations.Test;

import common.BaseTest;
import pages.WebTourHomepage;


public class Sample extends BaseTest {
	
  @Test
  public void homepageTests() {
	  logger = extent.createTest("TC001");
	  WebTourHomepage loginPage = new WebTourHomepage(getDriver());
	  loginPage.login();
	  
  }
}
