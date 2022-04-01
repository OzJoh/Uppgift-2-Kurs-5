Feature: Registration

  Background:
    Given Open the webpage

    Scenario: Success
     Given I have entered a valid email
      And I have entered a valid username
      And I have entered a valid password
      When I click Sign up
      Then I will succeed and be moved to the confirmation page

    Scenario: 100 character username
      Given I have entered a valid email
      And I have entered a username longer then 100 characters
      And I have entered a valid password
      When I click Sign up
      Then I will get too long name error

    Scenario: Used username
      Given I have entered a valid email
      And I have entered a username already in use
      And I have entered a valid password
      When I click Sign up
      Then I will fail and get user name already in use

    Scenario: No email
      Given I have entered a valid username
      And I have entered a valid password
      When I click Sign up
      Then I will fail and get please enter value under email