package tests;

import org.testng.annotations.Test;

import common.BaseTest;
import pages.WebTourHomepage;


public class Sample2 extends BaseTest {
  
  @Test
  public void homepageTests() {
	  WebTourHomepage googleHomepage = new WebTourHomepage(getDriver());
	  googleHomepage.login();
  }
}
