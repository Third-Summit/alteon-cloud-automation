package base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;

public class Config {

	//initializing driver
	public static WebDriver driver;

	public static WebDriver initDriver (String driverType){
		
		// setup browser
		
		if (driverType.equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup();			
		//	ChromeOptions ChromeOptions = new ChromeOptions();
		//	ChromeOptions.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
		//	driver = new ChromeDriver(ChromeOptions);
			driver = new ChromeDriver();
		} 
		else if (driverType.equalsIgnoreCase("safari")){
			DriverManagerType safari = DriverManagerType.SAFARI;
			WebDriverManager.getInstance(safari).setup();
			driver  = new SafariDriver();
		}
		else if (driverType.equalsIgnoreCase("fireFox")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
		} else if (driverType.equalsIgnoreCase("internetExplorer")){
			WebDriverManager.iedriver().setup();
			driver  = new InternetExplorerDriver();
		}
		
		else if (driverType.equalsIgnoreCase("edgeDriver")){
			WebDriverManager.edgedriver().setup();
			driver  = new EdgeDriver();
		}
		
		else {
			System.out.println("driver isn't initiated properly");
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		return driver;
	}

}
