package stepDefinition;

import base.Config;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjectModel.ShareProjectAndJoinProject_POM;
import utility.Utils;

public class ShareAndJoinaProject extends Config{



	ShareProjectAndJoinProject_POM sharePro = new ShareProjectAndJoinProject_POM(driver);
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

    @And ("navigate to google and open emailfake")
    public void And_navigate_to_google_and_open_emailfake() throws InterruptedException {
    	
    	        sharePro.OpenEmailFake();			
    }
    
    @And ("user copies the project join code")
    public void  And_user_copies_the_project_join_code() throws InterruptedException {
    	sharePro.userCopiesCodeFromEmailfake();
    }
    
    @And ("reopen Alteon")
    public void reopen_Alteon () {
    	sharePro.revisitAlteonIo();
    }
    
    @And ("non owner signs into Alteon")
    public void nonOwnerLogIn () throws InterruptedException {
    	sharePro.nonOwnerSignIn();
    }
    
    @And ("otp is fetched")
    public void  And_otp_is_fetched() {
    	sharePro.fetchOtp();
    }

    @And ("user joins project")
    public void  user_joins_project () throws InterruptedException {
    	sharePro.joinProject();
    }




}
