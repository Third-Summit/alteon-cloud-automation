package stepDefinition;

import base.Config;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjectModel.ShareProject_POM;
import utility.Utils;

public class ShareaProject extends Config{



	ShareProject_POM sharePro = new ShareProject_POM(driver);
	Utils util = new Utils();

	@Given("user sign in to Alteon")
	public void user_sign_in_to_alteon() throws InterruptedException {

		util.logIn();

	}

	@And ("user creates a project")
	public void user_creates_a_project() throws InterruptedException {
		sharePro.projectCreation();

	}

	@When ("user clicks on share project button")
	public void user_clicks_on_share_project_button() throws InterruptedException {

		sharePro.shareProjectScreen();
	}

    @And ("user enters email and selects permission")
	public void user_enters_email_and_selects_permission() throws InterruptedException {

    	sharePro.enterEmailAndSelectPermission();
	}
    
    @And ("user shares the project")
	public void user_shares_the_project() throws InterruptedException {

    	sharePro.shareButton();
	}
    
    @Then ("user sign out")
    public void user_sign_out() throws InterruptedException {
           util.signOut();
	}






}
