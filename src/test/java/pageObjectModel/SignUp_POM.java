package pageObjectModel;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.github.javafaker.Faker;

import base.Config;

public class SignUp_POM extends Config{

	Faker faker = new Faker();

	String emailFake;
	ArrayList<String> windowHandles;
	ArrayList<String> windowHandles2;

	public SignUp_POM(WebDriver driver) {
		PageFactory.initElements(driver, this);
		Config.driver = driver;
	}


	@FindBy(xpath = "//input[@name='firstName']")
	public WebElement firstName;

	@FindBy(xpath = "//input[@name='lastName']")
	public WebElement lastName;

	@FindBy(xpath = "//input[@name='email']")
	public WebElement email;

	@FindBy(xpath = "//input[@name='phoneNumber-visual']")
	public WebElement phoneNumber;

	@FindBy(xpath = "//input[@name='password']")
	public WebElement password;
	//*[@id="requestDemo"]/div[2]/div/form/div[4]/div[2]/div/input
	@FindBy(xpath = "//*[@id=\"requestDemo\"]/div[2]/div/form/div[4]/div[2]/div/input")
	public WebElement passwordConfirm;

	@FindBy(xpath = "//button[contains(text(),'Create an Account')]")
	public WebElement createAnAccount;

	@FindBy(xpath = "//*[contains(text(),'Verify Email')]")
	public WebElement verifyEmailHeader;

	@FindBy(xpath = "//a[@title = 'VERIFY']")
	public WebElement verifyEmailLinkInEmail;
	//*[@id="requestDemo"]/div[2]/header/h1
	@FindBy(xpath = "//*[@id=\"requestDemo\"]/div[2]/header/h1")
	public WebElement accountVerifiedHeader;

	@FindBy(xpath = "//span[@id='email_ch_text']")
	public WebElement copiedEmailfromEmailFake;

	public void navigateToEmailFake () {
		driver.get("https://emailfake.com/");
	}

	public void copyEmail () {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		emailFake = driver.findElement(By.xpath("//span[@id='email_ch_text']")).getText();
		System.out.println("Email = > "+emailFake);
	}

	public void userNavigatesBackToAlteon () {

		driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/a[1]")).sendKeys(Keys.chord(Keys.COMMAND, Keys.ENTER));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		windowHandles = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(windowHandles.get(1));
		driver.get("https://qa.alteon.io");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void userFillsOutCreateAccountForm () {
		firstName.sendKeys(faker.name().firstName());
		lastName.sendKeys(faker.name().lastName());
		email.sendKeys(emailFake);
	//	phoneNumber.sendKeys(faker.phoneNumber().cellPhone());
		phoneNumber.sendKeys(faker.phoneNumber().phoneNumber());
		
		password.sendKeys("Test1234$");
		passwordConfirm.sendKeys("Test1234$");
	}

	public void ClickCreateAnAccount() {
		createAnAccount.click();
		Assert.assertEquals("Verify Email",verifyEmailHeader.getText());

	}
	public void navigateBackToEmailFake () {
		driver.switchTo().window(windowHandles.get(0));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clickOnVerifyLink () {
		driver.navigate().refresh();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		verifyEmailLinkInEmail.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		windowHandles2 = new ArrayList<>(driver.getWindowHandles());

	}
	public void userRedirectedToAlteon () {
		driver.switchTo().window(windowHandles2.get(2));

	}
	
	public void getAccountVerifiedText () {
		Assert.assertEquals("Account Verified",accountVerifiedHeader.getText());

	}

}

















