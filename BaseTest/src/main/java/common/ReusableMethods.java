package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReusableMethods extends BasePage {
	WebDriver driver;

	public ReusableMethods(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void enterText(WebElement ele ,String value){
		ele.sendKeys(value);
	}

}
