package tests;

import org.testng.annotations.Test;

import common.BaseTest;
import pages.WebTourHomepage;


public class Sample extends BaseTest {
  
  @Test
  public void homepageTests() {
	  WebTourHomepage loginPage = new WebTourHomepage(getDriver());
	  loginPage.login();
  }
}
