package pageObjectModel;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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

	@FindBy(xpath = "//p[text()='Estimated Tax']/following-sibling::p")
	WebElement estimatedTaxEle;

	@FindBy(xpath = "//p[contains(text(),'Active member since')]")
	WebElement activeMemberSinceEle;

	@FindBy(xpath = "//input[@name='BillingName']")
	WebElement billingName;

	@FindBy(xpath = "//input[@name='cardnumber' and @inputmode='numeric']")
	WebElement cardNumberEle;

	@FindBy(xpath = "//input[@name='exp-date']")
	WebElement expDateEle;

	@FindBy(xpath = "//input[@name='cvc']")
	WebElement cvcEle;

	@FindBy(xpath = "//input[@name='Street']")
	WebElement streetEle;

	@FindBy(xpath = "//input[@name='Floor']")
	WebElement floorEle;

	@FindBy(xpath = "//input[@name='City']")
	WebElement cityEle;

	@FindBy(xpath = "//select[@name='States']")
	WebElement statesEle;

	@FindBy(xpath = "//input[@name='ZipCode']")
	WebElement zipCodeEle;


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
		 Thread.sleep(500);
		 try {
			 addBillingInfo.click();
			 fillBillingInformation(driver);
			 next.click();
		 }catch (NoSuchElementException e){
			 System.out.println("Billing information already present!");
		 }

	        String estimatedTax="";
	        Thread.sleep(5000);
	        estimatedTax = estimatedTaxEle.getText();
	        System.out.println(estimatedTax);
	        if(estimatedTax.equalsIgnoreCase("Not Calculated")){
	            System.out.println("Estimated Tax is not calculated correctly");
	        }else
	        {
	            System.out.println("Estimated Tax is calculated correctly");
	        }
	        apply.click();
	        Thread.sleep(5000);
	        if(activeMemberSinceEle.isDisplayed()){
	            System.out.println("Transaction Successful message displayed and Membership status is Active");
	        }
	    }


	  public  void fillBillingInformation(WebDriver driver) throws InterruptedException {
	        billingName.sendKeys("Test Card");
	        Thread.sleep(1000);
	        List<WebElement> frames = driver.findElements(By.tagName("iframe"));
	        driver.switchTo().frame(0);
	        for(int i=0;i<16;i++){
	            cardNumberEle.sendKeys(Keys.NUMPAD4);
	            cardNumberEle.sendKeys(Keys.NUMPAD2);
	        }
	        driver.switchTo().defaultContent();
	        driver.switchTo().frame(1);
	        expDateEle.sendKeys(Keys.NUMPAD0);
	        expDateEle.sendKeys(Keys.NUMPAD1);
	        expDateEle.sendKeys(Keys.NUMPAD2);
	        expDateEle.sendKeys(Keys.NUMPAD3);
	        driver.switchTo().defaultContent();
	        driver.switchTo().frame(2);
	        cvcEle.sendKeys(Keys.NUMPAD1);
	        cvcEle.sendKeys(Keys.NUMPAD2);
	        cvcEle.sendKeys(Keys.NUMPAD3);
	        driver.switchTo().defaultContent();
	        streetEle.sendKeys("test address");
	        floorEle.sendKeys("te");
	        cityEle.sendKeys("test city");
	        WebElement stateDD = statesEle;
	        Select stateDropDown = new Select(stateDD);
	        stateDropDown.selectByValue("AL");
	        zipCodeEle.sendKeys("07666");
	    }

}


