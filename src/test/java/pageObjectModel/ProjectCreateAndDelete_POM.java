package pageObjectModel;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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

public class ProjectCreateAndDelete_POM extends Config {


	public static final String ACCOUNT_SID = "ACe752095555591b20bdc6a4106cf9a760";
	public static final String AUTH_TOKEN = "14937b3a47deb603b2ffc7dc727eb258";

	public ProjectCreateAndDelete_POM(WebDriver driver) {
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



	@FindBy(xpath = "//*[@id=\"root\"]/main/div[1]/div[2]/div/div/div[1]/button")
	public WebElement createProject;

	@FindBy(xpath = "//input[@name='projectName']")
	public WebElement enterProjectName;

	@FindBy(xpath = "/html/body/div[3]/div[2]/div[3]/div/div/form/div[2]/button[2]")
	public WebElement clickCreate;

	@FindBy(xpath = "//*[@id=\"root\"]/main/div[2]/section/div/div/div/section/article/div/div/div/div/div/div[2]/div")
	public WebElement clickOnEllipses;
	

	@FindBy(xpath = "//div[@class='Dropdown_menu__3AI7l Dropdown_theme-quickview__1OAdn']/div/ul/li[3]")
	public WebElement delete;

	@FindBy(xpath = "/html/body/div[3]/div/div[3]/div/div/button[2]")
	public WebElement confirmDelete;


	public void Login() {
		logInLink.click();

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

	public void createProjectFunction() {
		createProject.click();
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(enterProjectName)).sendKeys("Project");
		clickCreate.click();

	}
	public void deleteProjectFunction () {
		clickOnEllipses.click();
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(delete)).click();
		confirmDelete.click();
		
	}


}
