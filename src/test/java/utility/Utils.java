package utility;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

import base.Config;

public class Utils extends Config{



	public static final String ACCOUNT_SID = "ACe752095555591b20bdc6a4106cf9a760";
	public static final String AUTH_TOKEN = "14937b3a47deb603b2ffc7dc727eb258";

	//------->Twilio is not being used for now<----------//


	public void logIn() throws InterruptedException {
		//Login
		driver.findElement(By.xpath("//*[@id=\"requestDemo\"]/div[2]/header/button")).click();
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("devalteon@iapermisul.ro");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Test1234$");
		driver.findElement(By.xpath("//button[text()='Log In']")).click();
		Thread.sleep(5000);
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		//Fill OTP
		String smsBody = getMessage();
		System.out.println(smsBody);
		String OTPNumber = smsBody.replaceAll("[^-?0-9]+", "");
		System.out.println(OTPNumber);
		driver.findElement(By.id("one-time-code")).sendKeys(Keys.TAB);
		driver.findElement(By.id("one-time-code")).sendKeys(OTPNumber);
		driver.findElement(By.xpath("//*[@id=\"requestDemo\"]/div[2]/div/form/div[3]/button")).click();

	}
	public void signOut() throws InterruptedException {
		//Click on User name on top right
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Test Profile']/parent::div")).click();

		//Click on sign out button
		Thread.sleep(500);
		driver.findElement(By.xpath("//span[text()='Sign Out']/parent::div/parent::button")).click();
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


	//------->Twilio is not being used for now<----------//

	public void log_in() throws InterruptedException {

		driver.findElement(By.xpath("//*[@id=\"requestDemo\"]/div[2]/header/button")).click();
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("adobe.thirdsummit@mail.com");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("AdobeTesting01!@");
		driver.findElement(By.xpath("//button[text()='Log In']")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("one-time-code")).sendKeys(Keys.TAB);
		driver.findElement(By.id("one-time-code")).sendKeys("111111");

	}

	public void log_out() throws InterruptedException {
		WebDriverWait wait= new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[text()='Adobe Thirdsummit']/parent::div"))));

		WebElement el = driver.findElement(By.xpath("//span[text()='Adobe Thirdsummit']/parent::div"));
		el.click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//span[text()='Sign Out']/parent::div/parent::button")).click();

	}
}
