package pageObjectModel;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

import base.Config;

public class SignInSignout_POM extends Config{
	
	public static final String ACCOUNT_SID = "ACe752095555591b20bdc6a4106cf9a760";
	public static final String AUTH_TOKEN = "14937b3a47deb603b2ffc7dc727eb258";
		
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
	    
	    @FindBy(xpath = "//*[@id=\"requestDemo\"]/div[2]/div/form/div[3]/button")
	    public WebElement submitButton;
	    
	    @FindBy(xpath = "//*[@id=\"root\"]/main/div[1]/div[1]/div/div[2]/div/div/div")
	    public WebElement signOutDropDown;
	    
	    @FindBy(xpath = "//span[text()='Sign Out']/parent::div/parent::button")
	    public WebElement signOut;
	    
	    
	  
	    
	    public void clickOnLogin() {
	    	logInLink.click();
	    }
	    public void enterEmailPassword() {
	    	email.sendKeys("devalteon@iapermisul.ro");
	    	password.sendKeys("Test1234$");
	    	logInButton.click();
	    }
	    public void fetchingOTPfromTwilio () throws InterruptedException {
	    	Thread.sleep(5000);
			Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
			String smsBody = getMessage();
			System.out.println(smsBody);
			String OTPNumber = smsBody.replaceAll("[^-?0-9]+", "");
			System.out.println(OTPNumber);
			
			OTPfield.sendKeys(Keys.TAB);
			OTPfield.sendKeys(OTPNumber);
	    }
	    
		public static String getMessage() {
			return getMessages().filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
					.filter(m -> m.getTo().equals("+14804422940")).map(Message::getBody).findFirst()
					.orElseThrow(IllegalStateException::new);
		}
		private static Stream<Message> getMessages() {
			ResourceSet<Message> messages = Message.reader(ACCOUNT_SID).read();
			return StreamSupport.stream(messages.spliterator(), false);
		}
		
		public void hitSubmitButton() {
			submitButton.click();
		}

		public void signOut() throws InterruptedException {
			signOutDropDown.click();
		    Thread.sleep(500);
		    signOut.click();

		}
}
