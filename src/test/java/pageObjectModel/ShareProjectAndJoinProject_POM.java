package pageObjectModel;


import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

import base.Config;

public class ShareProjectAndJoinProject_POM extends Config{

	public static final String ACCOUNT_SID_2 = "ACd7bca93ee0e7b0666467e38c5549bb59";
	public static final String AUTH_TOKEN_2 = "fc9c6075650213d5b7e1adb5749158e3";
     String alteonJoinCode;

	public ShareProjectAndJoinProject_POM (WebDriver driver) {
		PageFactory.initElements(driver, this);  
		Config.driver = driver;
	}

	@FindBy(xpath = "//button[text()='+ Add / Join Project']")
	WebElement createProject;


	@FindBy(xpath = "//input[@name='projectName']")
	WebElement inputProjectName;


	@FindBy(xpath = "//button[@type='submit']")
	WebElement createButton;

	@FindBy(xpath = "(//h2[text()='Test-Project-1']/parent::div//div[@class='Dropdown_trigger__mz0Fr Dropdown_trigger-theme-quickview__2HfZ0'])[1]")
	WebElement clickOnPrejectEllipses;

	@FindBy(xpath = "//span[text()='Share Project']/parent::button")
	WebElement clickOnShareProject;

	@FindBy(xpath = "//input[@name='membersAdded[0].Email']")
	WebElement enterEmail;

	@FindBy(css = "#permission")
	WebElement selectPermission;

	@FindBy(xpath = "//*[local-name()='svg'][@height='40']")
	WebElement clickPlusIcon;

	@FindBy(xpath = "//div[@class='SharePanel_submit-div__3ljZH']/button[1]")
	WebElement clickShareButton;

	
	public void projectCreation() throws InterruptedException{

		
		
		
		
		WebDriverWait wait = new WebDriverWait(driver, 500);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='DesktopOptions_root__3mbgI']/div/div[1]/button[1]")));

		WebElement element = driver.findElement(By.xpath("//div[@class='DesktopOptions_root__3mbgI']/div/div[1]/button[1]"));
		element.click();

		inputProjectName.sendKeys("Test_Project");
		createButton.click();


	}

	public void shareProjectScreen() throws InterruptedException {
		
		//Instantiate Action Class        
        Actions actions = new Actions(driver);
        //Retrieve WebElement 'Music' to perform mouse hover 
    	WebElement mainProject = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div[2]/section/div/div/div/section/div[1]/article/div/div/div"));
    	//Mouse hover 
    	actions.moveToElement(mainProject).perform();
		
		WebDriverWait wait2 = new WebDriverWait(driver, 500);
        //click on ellipses
		wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='Card_wrapper__4JRpX']/div/div/div/div[2]/div"))).click();
		WebElement element2 = driver.findElement(By.xpath("//div[@class='Card_wrapper__4JRpX']/div/div/div/div[2]/div"));
		element2.click();
		Thread.sleep(2000);
		element2.click();
		clickOnShareProject.click();

	}

	public void enterEmailAndSelectPermission() throws InterruptedException {

		Thread.sleep(1000);
		enterEmail.sendKeys("silberner@exy.email");
		Select readOnlyContributorDropDown = new Select(driver.findElement(By.cssSelector("#permission")));
		readOnlyContributorDropDown.selectByValue("WRITE");

	}


	public void shareButton() throws InterruptedException {

		Thread.sleep(2000);
		clickPlusIcon.click();
		Thread.sleep(500);
		clickShareButton.click();

	}

	public void OpenEmailFake () throws InterruptedException {
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
	}

     public void userCopiesCodeFromEmailfake () throws InterruptedException {
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
				WebDriverWait wait = new WebDriverWait(driver,10);

				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()=\"You've been invited to join Test-Project-1 on Alteon Cloud\"]/parent::a)[1]")));
				driver.findElement(By.xpath("(//*[text()=\"You've been invited to join Test-Project-1 on Alteon Cloud\"]/parent::a)[1]")).click();
			} catch (Exception f) {
				System.out.println("After refresh, we are already on email body page and couldn't click on email link again");
			}
			alteonJoinCode = driver.findElement(By.xpath("//span[contains(text(),\"is inviting you to collaborate on a project in Alteon Cloud.\")]/parent::p/following-sibling::p[1]")).getText();
		}
		System.out.println(alteonJoinCode);

     }
        
       public void revisitAlteonIo () {
   		driver.get("https://dev.alteon.io/");
       }
       
       public void nonOwnerSignIn () throws InterruptedException {
    	   driver.findElement(By.xpath("//*[@id=\"requestDemo\"]/div[2]/header/button")).click();
   		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("silberner@exy.email");
   		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Test1234$");
   		driver.findElement(By.xpath("//button[text()='Log In']")).click();
   		Thread.sleep(5000);
   		Twilio.init(ACCOUNT_SID_2,AUTH_TOKEN_2);

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
   	
   	public void fetchOtp () {
   	//Fill OTP
   			String smsBody2 = getMessage2();
   			System.out.println(smsBody2);
   			String OTPNumber2 = smsBody2.replaceAll("[^-?0-9]+", "");
   			System.out.println(OTPNumber2);
   			driver.findElement(By.id("one-time-code")).sendKeys(Keys.TAB);
   			driver.findElement(By.id("one-time-code")).sendKeys(OTPNumber2);
   			driver.findElement(By.xpath("//*[@id=\"requestDemo\"]/div[2]/div/form/div[3]/button")).click();

   	}
   	
   	public void joinProject() throws InterruptedException {
   	//Click on Add/Join button
   			Thread.sleep(2000);
   			driver.findElement(By.xpath("//button[text()='+ Add / Join Project']")).click();
   			//Click on Join button
			WebDriverWait wait = new WebDriverWait(driver,10);

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
       
}
