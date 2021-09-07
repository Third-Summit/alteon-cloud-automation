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
	
	@Then ("user fatches the otp from twilio and enters into alteon")
	public void Then_user_fatches_the_otp_from_twilio_and_enters_into_alteon() throws InterruptedException {
		logIn.fetchingOTPfromTwilio();
	}
	
	@When ("user logs in by clicking on submit button")
	public void When_user_logs_in_by_clicking_on_submit_button() {
		logIn.hitSubmitButton();
	}

   @And ("user signs out")
   public void user_signs_out() {
	 //  logIn.signOut();
   }














}
