package stepDefinition;

import base.Config;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageObjectModel.MembershipUpgrade_POM;
import utility.Utils;

public class Upgrademembership extends Config{


	MembershipUpgrade_POM upgradeMembership = new MembershipUpgrade_POM(driver);
	Utils util = new Utils();



	@Given("log in to alteon cloud")
	public void log_in_to_alteon_cloud() throws InterruptedException {
		util.log_in();

	}

	@And("go to billing page")
	public void go_to_billing_page() throws InterruptedException {
		upgradeMembership.billingPage();
	}
	
	@When("user clicks on Upgrade Membership button and adds storage")
	public void user_clicks_on_upgrade_membership_button_and_adds_storage() {
		upgradeMembership.upgrade();
	}

   @And ("user enters billing info and click apply")
   public void user_enters_billing_info_and_click_apply() throws InterruptedException {
	   upgradeMembership.enterBillingInfoAndClickApply();
   }

 //unable to catch purchase successful message 
   //make sure to add a function to click apply while upgrading membership if the payment method is already there


}
