package pageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Config;

public class MembershipUpgrade_POM extends Config {



	public MembershipUpgrade_POM(WebDriver driver) {
		PageFactory.initElements(driver, this);
		Config.driver = driver;
	}
	 //Click on Profile

	@FindBy(xpath = "//a[@href='/profile']")
	WebElement accounts;
	 
	//click on plans and billing
	@FindBy(xpath = "//a[@href='/plans-billing']")
	WebElement plansAndbilling;
	
    //Upgrade membership  
	@FindBy(xpath = "//button[text()='Upgrade Membership']")
	WebElement upgradeButton;
	
	@FindBy(xpath = "//button[text()='Next']")
	WebElement next;
	
	@FindBy(xpath = "//*[local-name()='svg' and @name='increase']")
	WebElement plusIconToAddTB;
	
	@FindBy(xpath = "//button[text()='+ Add Billing Information']")
	WebElement addBillingInfo;
	

	@FindBy(xpath = "//button[text()='Apply']")
	WebElement apply;
	
	
	
	public void billingPage() {
	
		accounts.click();
		plansAndbilling.click();
	}
	
	public void upgrade() {
		upgradeButton.click();
		next.click();
	}
	
	public void enterBillingInfoAndClickApply() throws InterruptedException {
		 for(int i=0;i<1;i++) {
			 plusIconToAddTB.click();
	        }
		    addBillingInfo.click();
	        fillBillingInformation(driver);
	        next.click();
	        String estimatedTax="";
	        Thread.sleep(5000);
	        estimatedTax = driver.findElement(By.xpath("//p[text()='Estimated Tax']/following-sibling::p")).getText();
	        System.out.println(estimatedTax);
	        if(estimatedTax.equalsIgnoreCase("Not Calculated")){
	            System.out.println("Estimated Tax is not calculated correctly");
	        }else
	        {
	            System.out.println("Estimated Tax is calculated correctly");
	        }
	        apply.click();
	        Thread.sleep(5000);
	        if( driver.findElement(By.xpath("//p[text()='Membership Status']/following-sibling::p")).isDisplayed()){
	            System.out.println("Transaction Successful message displayed and Membership status is Active");
	        }
	    }
	
	
	  public  void fillBillingInformation(WebDriver driver) throws InterruptedException {
	        driver.findElement(By.xpath("//input[@name='BillingName']")).sendKeys("Test Card");
	        Thread.sleep(1000);
	        List<WebElement> frames = driver.findElements(By.tagName("iframe"));
	        driver.switchTo().frame(0);
	        for(int i=0;i<16;i++){
	            driver.findElement(By.xpath("//input[@name='cardnumber' and @inputmode='numeric']")).sendKeys(Keys.NUMPAD4);
	            driver.findElement(By.xpath("//input[@name='cardnumber' and @inputmode='numeric']")).sendKeys(Keys.NUMPAD2);
	        }
	        driver.switchTo().defaultContent();
	        driver.switchTo().frame(1);
	        driver.findElement(By.xpath("//input[@name='exp-date']")).sendKeys(Keys.NUMPAD0);
	        driver.findElement(By.xpath("//input[@name='exp-date']")).sendKeys(Keys.NUMPAD1);
	        driver.findElement(By.xpath("//input[@name='exp-date']")).sendKeys(Keys.NUMPAD2);
	        driver.findElement(By.xpath("//input[@name='exp-date']")).sendKeys(Keys.NUMPAD3);
	        driver.switchTo().defaultContent();
	        driver.switchTo().frame(2);
	        driver.findElement(By.xpath("//input[@name='cvc']")).sendKeys(Keys.NUMPAD1);
	        driver.findElement(By.xpath("//input[@name='cvc']")).sendKeys(Keys.NUMPAD2);
	        driver.findElement(By.xpath("//input[@name='cvc']")).sendKeys(Keys.NUMPAD3);
	        driver.switchTo().defaultContent();
	        driver.findElement(By.xpath("//input[@name='Street']")).sendKeys("test address");
	        driver.findElement(By.xpath("//input[@name='Floor']")).sendKeys("te");
	        driver.findElement(By.xpath("//input[@name='City']")).sendKeys("test city");
	        WebElement stateDD = driver.findElement(By.xpath("//select[@name='States']"));
	        Select stateDropDown = new Select(stateDD);
	        stateDropDown.selectByValue("AL");
	        driver.findElement(By.xpath("//input[@name='ZipCode']")).sendKeys("07666");
	    }
	
}
