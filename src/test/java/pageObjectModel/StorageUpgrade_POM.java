package pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Config;

public class StorageUpgrade_POM extends Config {



	public StorageUpgrade_POM(WebDriver driver) {
		PageFactory.initElements(driver, this);
		Config.driver = driver;
	}


	@FindBy(xpath= "//a[@href='/profile']")
	WebElement accountPage ;

	@FindBy(xpath= "//a[@href='/plans-billing']")
	WebElement plansAndBilling ;

	@FindBy(xpath= "//*[local-name()='svg' and @name='increase']")
	WebElement increaseButton ;

	@FindBy(xpath= "//button[text()='Apply']")
	WebElement apply ;

	@FindBy(xpath= "//p[text()='Purchase Successful']")
	WebElement toastNotification ;

	public void billingPage() {
		accountPage.click();
		plansAndBilling.click();
	}
	public void increaseTB() throws InterruptedException {
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[local-name()='svg' and @name='increase']"))));
		WebElement plusIcon = driver.findElement(By.xpath("//*[local-name()='svg' and @name='increase']"));
		System.out.println("Clicking plus icon");
		plusIcon.click();
	}
	public void clickApply() throws InterruptedException {

		Thread.sleep(2000);
		apply.click();
	}
	public void confirmSuccess () {
        if((toastNotification).isDisplayed());
        System.out.println("Toast Notification message => "+toastNotification.getText());

	}




}
