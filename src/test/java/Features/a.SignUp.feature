Feature: Sign Up

  Scenario: Validate user is able to sign up
    Given user navigates to emailfake website
    And copy the generated email
    Then user navigates to alteon website in a new tab
    When user fills in 'first_name', 'last_name', email, phonenumber '' and 'password'
    And clicks on 'Create an Account' button
    Then user navigates back to EmailFake website to activate account
    And user clicks on "verify email" button
    Then user is taken to alteon website in new tab
    And Account Verified message is displayed
