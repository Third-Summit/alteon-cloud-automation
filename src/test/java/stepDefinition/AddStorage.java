package stepDefinition;

import base.Config;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjectModel.StorageUpgrade_POM;
import utility.Utils;

public class AddStorage extends Config {

	StorageUpgrade_POM upgrade = new StorageUpgrade_POM(driver);

	Utils util = new Utils();


	@Given("sign in to Alteon")
	public void sign_in_to_Alteon() throws InterruptedException {
		
		util.log_in();
	}
	
	@And("go to plans and billing page")
	public void go_to_plans_and_billing_page() throws InterruptedException {
		
		upgrade.billingPage();
	}
	
	@When("increase intended TB")
	public void increase_intended_TB() throws InterruptedException {
		
		upgrade.increaseTB();
	}
	//Unable to automate clicking on plus icon to  increase TB 
	
	@And("Click apply")
	public void Click_apply() throws InterruptedException {
		
		upgrade.clickApply();
	}
	
	@Then("confirm the successful purchase")
	public void confirm_the_successful_purchase() throws InterruptedException {
		
		upgrade.confirmSuccess();
	}

}

