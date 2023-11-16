#Author: Jan Faizi

@Smoke @Regression
Feature: Smoke Test Suite

@Test_1
  Scenario: Login page smoke test
    Then Login page title is "TEK Insurance Portal" 
    And wait 2 second
    Then Loaded Section is "Customer Service Portal"
    And wait 2 second