package utility;
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
public class InviteAndJoinProject {



	public static final String ACCOUNT_SID_2 = "ACd7bca93ee0e7b0666467e38c5549bb59";
	public static final String AUTH_TOKEN_2 = "fc9c6075650213d5b7e1adb5749158e3";

	public static void main(String[] args) throws InterruptedException  {

		//Open website
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver,10);
		driver.get("https://dev.alteon.io/");



		//Navigate to google
		driver.get("https://google.com");
		//Navigate to email fake and open invitee email
		Thread.sleep(1000);
		driver.get("https://emailfake.com/channel2/exy.email/silberner");
		driver.manage().window().maximize();
		//Refresh after waiting for 5 seconds for the invite email to come
		Thread.sleep(5000);
		driver.navigate().refresh();
		//Find the email and click on it
		Thread.sleep(2000);  
		
        JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		
		driver.findElement(By.xpath("(//*[text()=\"You've been invited to join Test-Project-1 on Alteon Cloud\"]/parent::a)[1]")).click();

		String alteonJoinCode;
		try {
			//Copy join code
			Thread.sleep(1000);
			alteonJoinCode = driver.findElement(By.xpath("//span[text()='Once logged into Alteon.io, copy the code below and paste it into the Join Project field.']/parent::p/following-sibling::p[1]")).getText();
		} catch (NoSuchElementException e) {
			System.out.println("Couldn't find code, refreshing page");
			driver.navigate().refresh();
			Thread.sleep(1000);
			try {
				Thread.sleep(1000);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()=\"You've been invited to join Test-Project-1 on Alteon Cloud\"]/parent::a)[1]")));
				driver.findElement(By.xpath("(//*[text()=\"You've been invited to join Test-Project-1 on Alteon Cloud\"]/parent::a)[1]")).click();
			} catch (Exception f) {
				System.out.println("After refresh, we are already on email body page and couldn't click on email link again");
			}
			alteonJoinCode = driver.findElement(By.xpath("//span[contains(text(),\"is inviting you to collaborate on a project in Alteon Cloud.\")]/parent::p/following-sibling::p[1]")).getText();
		}
		System.out.println(alteonJoinCode);

		//Visit alteon website
		driver.get("https://dev.alteon.io/");
		//login
		driver.findElement(By.xpath("//*[@id=\"requestDemo\"]/div[2]/header/button")).click();
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("silberner@exy.email");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Test1234$");
		driver.findElement(By.xpath("//button[text()='Log In']")).click();
		Thread.sleep(5000);
		Twilio.init(ACCOUNT_SID_2,AUTH_TOKEN_2);

		//Fill OTP
		String smsBody2 = getMessage2();
		System.out.println(smsBody2);
		String OTPNumber2 = smsBody2.replaceAll("[^-?0-9]+", "");
		System.out.println(OTPNumber2);
		driver.findElement(By.id("one-time-code")).sendKeys(Keys.TAB);
		driver.findElement(By.id("one-time-code")).sendKeys(OTPNumber2);
		driver.findElement(By.xpath("//*[@id=\"requestDemo\"]/div[2]/div/form/div[3]/button")).click();

		//Click on Add/Join button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()='+ Add / Join Project']")).click();
		//Click on Join button
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='join project']")));
		driver.findElement(By.xpath("//button[text()='join project']")).click();
		//Type access code
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='accessCode']")).sendKeys(alteonJoinCode);
		//Click on Join button
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Join']")));
		driver.findElement(By.xpath("//button[text()='Join']")).click();
		//Click on Go To project button
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("go to Project »")));
		driver.findElement(By.linkText("go to Project »")).click();
	}
	
	public static String getMessage2() {
		return getMessages2().filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
				.filter(m -> m.getTo().equals("+13235242575")).map(Message::getBody).findFirst()
				.orElseThrow(IllegalStateException::new);
	}
	private static Stream<Message> getMessages2() {
		ResourceSet<Message> messages = Message.reader(ACCOUNT_SID_2).read();
		return StreamSupport.stream(messages.spliterator(), false);
	}







}
