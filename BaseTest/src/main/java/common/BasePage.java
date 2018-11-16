package common;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

public class BasePage {

    private static final int TIMEOUT = 5;
    private static final int POLLING = 100;

    protected WebDriver driver;
    private WebDriverWait wait;
    protected ExtentTest logger;
    public BasePage(WebDriver driver,ExtentTest logger) {
        this.driver = driver;
        this.logger=logger;
        wait = new WebDriverWait(this.driver, TIMEOUT, POLLING);
        PageFactory.initElements(this.driver, this);
    }

    protected void waitForElementToAppear(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected void waitForTextToDisappear(By locator, String text) {
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(locator, text)));
    }
    
	public void enterText(WebElement ele ,String value){
		ele.sendKeys(value);
	}
	
	public void enterTextValues(WebElement ele ,String value) throws InterruptedException{
		ele.sendKeys(value);
		ele.click();
		ele.sendKeys(Keys.BACK_SPACE);
		Thread.sleep(2000);
		waitForElementToAppear(driver.findElement(By.linkText(value)));
		driver.findElement(By.linkText(value)).click();
		logger.info("Entered Value : "+ value);
	}
	
}