package test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Keys;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TempEmailActivation {

	
	public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://emailfake.com/");
        Thread.sleep(2000);
        String email = driver.findElement(By.xpath("//span[@id='email_ch_text']")).getText();
        System.out.println("Email = > "+email);
        String tempEmailWindow = driver.getWindowHandle();
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/a[1]")).sendKeys(Keys.chord(Keys.COMMAND, Keys.ENTER));;
        Thread.sleep(2000);
        ArrayList<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowHandles.get(1));
        driver.get("https://dev.alteon.io/");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys("TestFirstName");
        driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys("TestLastName");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='phoneNumber-visual']")).sendKeys("0010000000");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Alteon@123");
        driver.findElement(By.xpath("//input[@name='passwordConfirm']")).sendKeys("Alteon@123");
        driver.findElement(By.xpath("//button[contains(text(),'Create an Account')]")).click();
        Assert.assertEquals("Verify Email",driver.findElement(By.xpath("//*[contains(text(),'Verify Email')]")).getText());
        driver.switchTo().window(windowHandles.get(0));
        Thread.sleep(2000);
        driver.navigate().refresh();
        Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        //driver.findElement(By.xpath("//a[contains(text(),'Welcome to Alteon Cloud. Please activate your account.')]")).click();
        driver.findElement(By.xpath("//a[@title = 'VERIFY']")).click();
        Thread.sleep(2000);
        ArrayList<String> windowHandles2 = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowHandles2.get(2));
        Assert.assertEquals("Account Verified",driver.findElement(By.xpath("//h2[contains(text(),'Account Verified')]")).getText());

	}
}
