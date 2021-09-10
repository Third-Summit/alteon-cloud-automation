package utility;
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class TestOTP {


	public static final String ACCOUNT_SID = "ACe752095555591b20bdc6a4106cf9a760";
	public static final String AUTH_TOKEN = "14937b3a47deb603b2ffc7dc727eb258";
	public static void main(String[] args) throws InterruptedException  {


		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://dev.alteon.io/");



		driver.findElement(By.xpath("//*[@id=\"requestDemo\"]/div[2]/header/button")).click();
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("devalteon@iapermisul.ro");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Test1234$");
		driver.findElement(By.xpath("//button[text()='Log In']")).click();
		Thread.sleep(5000);
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		String smsBody = getMessage();
		System.out.println(smsBody);
		String OTPNumber = smsBody.replaceAll("[^-?0-9]+", "");
		System.out.println(OTPNumber);

		driver.findElement(By.id("one-time-code")).sendKeys(Keys.TAB);

		driver.findElement(By.id("one-time-code")).sendKeys(OTPNumber); 

		driver.findElement(By.xpath("//*[@id=\"requestDemo\"]/div[2]/div/form/div[3]/button")).click();
	 driver.findElement(By.xpath("/html/body/div[3]/div/div[3]/div/div/button[2]")).click();
	
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

}









