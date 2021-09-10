
Feature: user shares a project to another user

  
  Scenario: Share Project
    Given user sign in to Alteon
    And user creates a project
    When user clicks on share project button
    And user enters email and selects permission
    And user shares the project
    Then user sign out
    And navigate to google and open emailfake
    And user copies the project join code
    And reopen Alteon
    And non owner signs into Alteon
    And otp is fetched
    And use joins project