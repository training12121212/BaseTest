package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;

public class ReusableMethods extends BasePage {
	WebDriver driver;
	//protected ExtentTest logger;

	public ReusableMethods(WebDriver driver,ExtentTest logger) {
		super(driver, logger);
		// TODO Auto-generated constructor stub
	}
	
	public void enterText(WebElement ele ,String value){
		ele.sendKeys(value);
	}

}
