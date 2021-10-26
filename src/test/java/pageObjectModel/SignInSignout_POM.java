package pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Config;

public class SignInSignout_POM extends Config{
	
		
		public SignInSignout_POM(WebDriver driver) {
			PageFactory.initElements(driver, this);
			Config.driver = driver;
		}


	    @FindBy(xpath = "//*[@id=\"requestDemo\"]/div[2]/header/button")
	    public WebElement logInLink;
	    
	    @FindBy(xpath = "//input[@name='email']")
	    public WebElement email;
	    
	    @FindBy(xpath = "//input[@name='password']")
	    public WebElement password;
	
	    @FindBy(xpath = "//button[text()='Log In']")
	    public WebElement logInButton;
	    
	    @FindBy(id = "one-time-code")
	    public WebElement OTPfield;
	    
	    
//	    @FindBy(xpath = "//*[@id=\"root\"]/main/div[1]/div[1]/div/div/div/div/div")
//	    public WebElement signOutDropDown;
	    
	    @FindBy(xpath = "//span[text()='Sign Out']/parent::div/parent::button")
	    public WebElement signOut;
	    
	    
	  
	    
	    public void clickOnLogin() {
	    	logInLink.click();
	    }
	    public void enterEmailPassword() {
	    	email.sendKeys("adobe.thirdsummit@mail.com");
	    	password.sendKeys("Test1234$");
	    	logInButton.click();
	    }
	    public void enteringOTP () {
	    	
	    	OTPfield.sendKeys(Keys.TAB);
			OTPfield.sendKeys("111111");
	    	
	    }
	    
		
		

		public void signOut() throws InterruptedException {
			WebDriverWait wait = new WebDriverWait(driver, 500);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/main/div[1]/div[1]/div/div/div/div/div")));
			WebElement signOutDropDown = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div[1]/div[1]/div/div/div/div/div"));

			signOutDropDown.click();
		    Thread.sleep(500);
		    signOut.click();

		}
}
