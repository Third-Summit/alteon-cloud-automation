package utility;
import org.openqa.selenium.NoSuchElementException;

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


	public void log_in() throws InterruptedException {

		driver.findElement(By.xpath("//*[@id=\"requestDemo\"]/div[2]/header/button")).click();
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("adobe.thirdsummit@mail.com");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Test1234$");
		driver.findElement(By.xpath("//button[text()='Log In']")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("one-time-code")).sendKeys(Keys.TAB);
		driver.findElement(By.id("one-time-code")).sendKeys("111111");

	}

	public void log_out() throws InterruptedException {
		WebDriverWait wait= new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[text()='Test Account']/parent::div"))));



		try {
			WebElement el1 = driver.findElement(By.xpath("//span[text()='Test Account']/parent::div"));
			el1.click();

		} catch (NoSuchElementException e) {
			WebElement el = driver.findElement(By.xpath("//span[text()='Adobe Thirdsummit']/parent::div"));
			el.click();
		} catch (Exception e) {
			System.out.println("The username is neither Test Account nor Adobe Thirdsummit and hence couldn't log out");
		}

		Thread.sleep(500);
		driver.findElement(By.xpath("//span[text()='Sign Out']/parent::div/parent::button")).click();

	}
}
