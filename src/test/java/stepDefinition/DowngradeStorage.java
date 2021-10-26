package stepDefinition;

import base.Config;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjectModel.DowngradeStorage_POM;
import utility.Utils;

public class DowngradeStorage extends Config {


	Utils util = new Utils();
	DowngradeStorage_POM storageDowngrade = new DowngradeStorage_POM(driver);

	@Given ("sign in to Alteon cloud")
	public void sign_in_to_Alteon_cloud() throws InterruptedException {
		util.log_in();
	}

	@And ("go to the billing page")
	public void go_to_the_billing_page () {
		storageDowngrade.billing();
	}

	@When ("decrease intended TB")
	public void decrease_intended_TB() throws InterruptedException {
		storageDowngrade.decrease();
	}

	@And ("Click apply button")
	public void Click_apply_button() throws InterruptedException {
		storageDowngrade.applyButton();
	}
	@Then ("confirm the successful downgrade message")
	public void confirm_the_successful_downgrade_message() {
		storageDowngrade.confirmSuccess();
	}


















}

