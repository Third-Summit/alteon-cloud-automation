package pageObjectModel;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import base.Config;

public class ProjectCreateAndDelete_POM extends Config {



	public ProjectCreateAndDelete_POM(WebDriver driver) {
		PageFactory.initElements(driver, this);
		Config.driver = driver;
	}





	@FindBy(xpath = "//input[@name='projectName']")
	public WebElement enterProjectName;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement clickCreate;

//	@FindBy(xpath = "//div[@class='Card_wrapper__4JRpX']/div/div/div/div[2]/div")
//	public WebElement clickOnEllipses;


	@FindBy(xpath = "//div[@class='Dropdown_menu__3AI7l undefined']/div/ul/li[3]/button[1]")
	public WebElement delete;

	@FindBy(xpath = "//button[text()='Yes, Permanently Delete']")
	public WebElement confirmDelete;




	public void createProjectFunction() throws InterruptedException {


		WebDriverWait wait = new WebDriverWait(driver, 500);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='DesktopOptions_root__3mbgI']/div/div[1]/button[1]")));

		WebElement element = driver.findElement(By.xpath("//div[@class='DesktopOptions_root__3mbgI']/div/div[1]/button[1]"));
		element.click();

		enterProjectName.sendKeys("The_Test_Project");
		clickCreate.click();



	}

	public void deleteProjectFunction () throws InterruptedException {
				
		Thread.sleep(3000);
		WebDriverWait wait2 = new WebDriverWait(driver, 500);
//click on ellipses
		wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='Card_wrapper__4JRpX']/div/div/div/div[2]/div"))).click();
		WebElement element2 = driver.findElement(By.xpath("//div[@class='Card_wrapper__4JRpX']/div/div/div/div[2]/div"));
		element2.click();
		Thread.sleep(2000);
		element2.click();
		delete.click();
		confirmDelete.click();





	}


}
