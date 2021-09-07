package stepDefinition;

import base.Config;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import com.github.javafaker.Faker;

import pageObjectModel.SignUp_POM;


public class UserSignUp extends Config{

	Faker faker = new Faker();

	SignUp_POM loginSignUp = new SignUp_POM(driver);


	@Given("user navigates to emailfake website")
	public void user_navigates_to_emailfake_website() {
		loginSignUp.navigateToEmailFake();
	}

	@And("copy the generated email")
	public void copy_the_generated_email() {

		loginSignUp.copyEmail();
	}

	@Then("user navigates to alteon website in a new tab")
	public void user_navigates_to_alteon_website_in_a_new_tab() {

		loginSignUp.userNavigatesBackToAlteon();
	}

	@When("user fills in {string}, {string}, email, phonenumber {string} and {string}")
	public void user_fills_in_email_phonenumber_and(String string, String string2, String string3, String string4) {

		loginSignUp.userFillsOutCreateAccountForm();

	}

	@And("clicks on {string} button")
	public void clicks_on_button(String string) {

		loginSignUp.ClickCreateAnAccount();
	}

	@Then("user navigates back to EmailFake website to activate account")
	public void user_navigates_back_to_EmailFake_website_to_activate_account() {

		loginSignUp.navigateBackToEmailFake();
	}

	@And("user clicks on {string} button")
	public void user_clicks_on_button(String string) {

		loginSignUp.clickOnVerifyLink();
	}

	@Then("user is taken to alteon website in new tab")
	public void user_is_taken_to_alteon_website_in_new_tab() {

		loginSignUp.userRedirectedToAlteon();
	}

	@And("Account Verified message is displayed")
	public void account_verified_message_is_displayed() {

		loginSignUp.getAccountVerifiedText();
	}

}
