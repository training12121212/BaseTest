package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import common.BasePage;
import common.ReusableMethods;

public class WebTourHomepage extends BasePage {
	private WebDriver driver;
	public ExtentTest logger;

	public WebTourHomepage(WebDriver driver,ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "source")
	WebElement userName;

	@FindBy(name = "destination")
	WebElement password;

	@FindBy(name = "searchBtn")
	WebElement login;
	
	@FindBy(name = "txtJourneyDate")
	WebElement txtJourneyDate;
	

	public void login(String val1,String val2) throws InterruptedException {
		enterTextValues(userName, val1);
		enterTextValues(password, val2);
		txtJourneyDate.click();
		driver.findElement(By.linkText("18")).click();
		login.click();

	}

}