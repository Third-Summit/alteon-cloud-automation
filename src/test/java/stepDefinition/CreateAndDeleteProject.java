package stepDefinition;

import base.Config;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import pageObjectModel.ProjectCreateAndDelete_POM;

public class CreateAndDeleteProject extends Config {

	ProjectCreateAndDelete_POM project = new ProjectCreateAndDelete_POM(driver);
	


	@Given("user logs in to alteon cloud")
	public void user_logs_in_to_alteon_cloud() throws InterruptedException {
	
		project.Login();
		project.fetchingOTPfromTwilio();
		project.hitSubmitButton();
		
		
	}
 
	@And("user creates an account")
	public void user_creates_an_account() {
		project.createProjectFunction();
	}

	@And("user deletes the account")
	public void user_deletes_the_account() {
		project.deleteProjectFunction();
	}
	
	
}
