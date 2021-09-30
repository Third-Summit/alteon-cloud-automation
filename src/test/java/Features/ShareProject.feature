
Feature: user shares a project to another user

  
  Scenario: Share Project
    Given user sign in to Alteon
    And user creates a test project
    When user clicks on share project button
    And user enters email and selects permission
    And user shares the project
    Then user sign out
   