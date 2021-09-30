package stepDefinition;


import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Config;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import pageObjectModel.ProjectCreateAndDelete_POM;
import utility.Utils;

public class CreateAndDeleteProject extends Config {

	ProjectCreateAndDelete_POM project = new ProjectCreateAndDelete_POM(driver);

	Utils util = new Utils();


	@Given("user logs in to alteon cloud")
	public void user_logs_in_to_alteon_cloud() throws InterruptedException {
		util.log_in();
	}

	@And("user creates a project")
	public void user_creates_a_project() throws InterruptedException {
			project.createProjectFunction();



	}

	@And("user deletes the account")
	public void user_deletes_the_account() throws InterruptedException {
			project.deleteProjectFunction();
	}


}
