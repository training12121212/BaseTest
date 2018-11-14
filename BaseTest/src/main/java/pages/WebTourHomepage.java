package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.BasePage;
import common.ReusableMethods;

public class WebTourHomepage extends BasePage {
	private WebDriver driver;

	public WebTourHomepage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "userName")
	WebElement userName;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(name = "login")
	WebElement login;

	public void login() {
		enterText(userName, "Demo");
		enterText(password, "demo");
		login.click();

	}

}