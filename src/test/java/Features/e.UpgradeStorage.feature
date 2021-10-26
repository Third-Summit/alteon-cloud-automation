
Feature: User is buying more storage


  Scenario: increase Storage
    Given sign in to Alteon
    And go to plans and billing page
    When increase intended TB
    And Click apply
    Then confirm the successful purchase
  

 