Feature: User Portal Sign Up Page Functionalities Verify that User is able to sign up and logged in.


Scenario: Verify User is able to login
	
	Given User navigates to Alteon.io home page and clicks on the login link
	And user enters email and password in appropriate fields
	Then user fatches the otp from twilio and enters into alteon
	When user logs in by clicking on submit button
	And user signs out