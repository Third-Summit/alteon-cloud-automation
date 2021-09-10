package pageObjectModel;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Config;

public class ShareProject_POM extends Config{



	public ShareProject_POM (WebDriver driver) {
		PageFactory.initElements(driver, this);  
		Config.driver = driver;
	}

	@FindBy(xpath = "//button[text()='+ Add / Join Project']")
	WebElement createProject;


	@FindBy(xpath = "//input[@name='projectName']")
	WebElement inputProjectName;


	@FindBy(xpath = "//button[@type='submit' and text()='Create']")
	WebElement createButton;

	@FindBy(xpath = "(//h2[text()='Test-Project-1']/parent::div//div[@class='Dropdown_trigger__mz0Fr Dropdown_trigger-theme-quickview__2HfZ0'])[1]")
	WebElement clickOnPrejectEllipses;

	@FindBy(xpath = "//span[text()='Share Project']/parent::button")
	WebElement clickOnShareProject;

	@FindBy(xpath = "//input[@name='membersAdded[0].Email']")
	WebElement enterEmail;

	@FindBy(css = "#permission")
	WebElement selectPermission;

	@FindBy(xpath = "//button[@class=\"Button_root__1LFu4 ButtonLinkStyles_root__2drze ButtonLinkStyles_default__2D1LR MemberInput_button__kN0AS\"]")
	WebElement clickPlusIcon;

	@FindBy(xpath = "//button[text()='share']")
	WebElement clickShareButton;

	
	public void projectCreation() throws InterruptedException{

		Thread.sleep(2000);
		createProject.click();
		inputProjectName.sendKeys("Test_Project");
		Thread.sleep(1000);
		createButton.click();

	}

	public void shareProjectScreen() throws InterruptedException {
        Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(clickOnPrejectEllipses));
		clickOnPrejectEllipses.click();
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

	



}
