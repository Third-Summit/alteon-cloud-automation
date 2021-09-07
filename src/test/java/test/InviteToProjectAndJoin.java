package test;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
public class InviteToProjectAndJoin {
    public static final String ACCOUNT_SID = "ACe752095555591b20bdc6a4106cf9a760";
    public static final String AUTH_TOKEN = "14937b3a47deb603b2ffc7dc727eb258";
    public static final String ACCOUNT_SID_2 = "ACd7bca93ee0e7b0666467e38c5549bb59";
    public static final String AUTH_TOKEN_2 = "fc9c6075650213d5b7e1adb5749158e3";
    public static void main(String[] args) throws InterruptedException  {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
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
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[text()='+ Add / Join Project']")).click();
        driver.findElement(By.xpath("//input[@name='projectName']")).sendKeys("Test-Project-1");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@type='submit' and text()='Create']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//h2[text()='Test-Project-1']/parent::div//div[@class='Dropdown_trigger__mz0Fr Dropdown_trigger-theme-quickview__2HfZ0'])[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[text()='Share Project']/parent::button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='membersAdded[0].Email']")).sendKeys("silberner@exy.email");
        Select readOnlyContributorDropDown = new Select(driver.findElement(By.cssSelector("#permission")));
        readOnlyContributorDropDown.selectByValue("WRITE");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@class=\"Button_root__1LFu4 ButtonLinkStyles_root__2drze ButtonLinkStyles_default__2D1LR MemberInput_button__kN0AS\"]")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//button[text()='share']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//span[text()='Test Profile']/parent::div")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//span[text()='Sign Out']/parent::div/parent::button")).click();
        driver.get("https://google.com");
        driver.get("https://emailfake.com/channel2/exy.email/silberner");
        Thread.sleep(5000);
        driver.navigate().refresh();
        driver.findElement(By.xpath("(//*[text()=\"You've been invited to join Test-Project-1 on Alteon Cloud\"]/parent::a)[1]")).click();
        String alteonJoinCode;
        try {
            alteonJoinCode = driver.findElement(By.xpath("//span[text()='Once logged into Alteon.io, copy the code below and paste it into the Join Project field.']/parent::p/following-sibling::p[1]")).getText();
        } catch (NoSuchElementException e) {
            System.out.println("Couldn't find code, refreshing page");
            driver.navigate().refresh();
            Thread.sleep(1000);
            try {
                driver.findElement(By.xpath("(//*[text()=\"You've been invited to join Test-Project-1 on Alteon Cloud\"]/parent::a)[1]")).click();
            } catch (NoSuchElementException f) {
                System.out.println("After refresh, we are already on email body page and couldn't click on email link again");
            }
            alteonJoinCode = driver.findElement(By.xpath("//span[contains(text(),\"is inviting you to collaborate on a project in Alteon Cloud.\")]/parent::p/following-sibling::p[1]")).getText();
        }
        
        
        System.out.println(alteonJoinCode);
        driver.get("https://dev.alteon.io/");
        driver.findElement(By.xpath("//*[@id=\"requestDemo\"]/div[2]/header/button")).click();
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("silberner@exy.email");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Test1234$");
        driver.findElement(By.xpath("//button[text()='Log In']")).click();
        Thread.sleep(5000);
        Twilio.init(ACCOUNT_SID_2,AUTH_TOKEN_2);
        String smsBody2 = getMessage2();
        System.out.println(smsBody2);
        String OTPNumber2 = smsBody2.replaceAll("[^-?0-9]+", "");
        System.out.println(OTPNumber2);
        driver.findElement(By.id("one-time-code")).sendKeys(Keys.TAB);
        driver.findElement(By.id("one-time-code")).sendKeys(OTPNumber2);
        driver.findElement(By.xpath("//*[@id=\"requestDemo\"]/div[2]/div/form/div[3]/button")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[text()='+ Add / Join Project']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[text()='join project']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='accessCode']")).sendKeys(alteonJoinCode);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[text()='Join']")).click();
        Thread.sleep(2000);
        driver.findElement(By.linkText("go to Project »")).click();
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