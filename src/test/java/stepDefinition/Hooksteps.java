package stepDefinition;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.util.Strings;

import base.Config;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooksteps extends Config{

	static String url;
	static String baseURL = System.getProperty("env");
	static String browserType = System.getProperty("browser");
	// default browser and env
	static String defaultBrowser = "ch";
	static String defaultEnv = "dev";

	@Before
	public void openBrowser(){
		if (Strings.isNullOrEmpty(browserType)){
			browserType = defaultBrowser;
		}
		if (Strings.isNullOrEmpty(baseURL)){
			baseURL = defaultEnv;
		}
		driver = initDriver (browserType);
		switch (baseURL){

		case "dev":
			url = "https://v1.dev.alteon.io/";
			break;
		case "pre_prod":
			url = "https://alteon.io/";
			break;

		default:  System.out.println("Env isn't set up yet");
		break;
		}
		driver.get(url);
	}

	@After
	public void tearDown(Scenario scenario){
		try{
			if (scenario.isFailed()){
				final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", scenario.getName());
				System.out.println(scenario.getName());
			}
		} finally {
			driver.quit();
		}
	}

}
