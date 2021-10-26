
Feature: User is downgrading storage


  Scenario: downgrade storage Storage
    Given sign in to Alteon cloud
    And go to the billing page
    When decrease intended TB
    And Click apply button
    Then confirm the successful downgrade message
