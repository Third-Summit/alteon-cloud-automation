package stepDefinition;


import base.Config;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjectModel.SignInSignout_POM;

public class UserSignInSignout extends Config{

	
	SignInSignout_POM logIn = new SignInSignout_POM(driver);


	@Given("User navigates to Alteon.io home page and clicks on the login link")
	public void user_navigates_to_alteon_io_home_page_and_clicks_on_the_login_link() {
		logIn.clickOnLogin();

	}

	@And ("user enters email and password in appropriate fields")
	public void user_enters_email_and_password_in_appropriate_fields() {
		logIn.enterEmailPassword();
	}
	
	@Then ("user signs in after entering correct OTP")
	public void user_signs_in_after_entering_correct_OTP() throws InterruptedException {
		//logIn.fetchingOTPfromTwilio();
		logIn.enteringOTP();
	}
	

   @And ("user signs out")
   public void user_signs_out() throws InterruptedException  {
	  logIn.signOut();
   }














}
