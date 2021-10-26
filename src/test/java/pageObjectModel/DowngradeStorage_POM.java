package pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Config;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DowngradeStorage_POM extends Config {



	public DowngradeStorage_POM(WebDriver driver) {
		PageFactory.initElements(driver, this);
		Config.driver = driver;
	}

	@FindBy(xpath= "//a[@href='/profile']")
	WebElement accountScreen ;

	@FindBy(xpath= "//a[@href='/plans-billing']")
	WebElement plansAndBillingScreen;

	@FindBy(xpath= "//*[local-name()='svg' and @name='decrease']")
	WebElement decreaseButton ;

	@FindBy(xpath= "//*[local-name()='svg' and @name='increase']")
	WebElement increaseButton ;

	@FindBy(xpath= "//button[text()='Apply']")
	WebElement applyButton ;

	@FindBy(xpath= "//p[contains(text(),'Adjustment successful')]")
	WebElement notificationButton ;


	public void billing() {
		accountScreen.click();
		plansAndBillingScreen.click();
	}
	public void decrease() throws InterruptedException {
		Thread.sleep(3000);
		decreaseButton.click();
		Thread.sleep(1000);
		increaseButton.click();
	}

	public void applyButton() throws InterruptedException {
		Thread.sleep(2000);
		applyButton.click();
	}

	public void confirmSuccess () {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Adjustment successful')]")));;
        if((notificationButton).isDisplayed())
        System.out.println("Toast Notification message => "+notificationButton.getText());

	}

}
