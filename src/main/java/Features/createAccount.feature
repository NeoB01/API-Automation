Feature: Create Account

  Scenario: Post create user account
    Given user clicks the create account link
    When user passes the create account url with name "Neo" and form data
    Then user receives a response showing that the account has been created