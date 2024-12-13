Feature: Delete Account

  Scenario: Delete user account in the system
    Given the user is logged in with a valid account
    When the user deletes their account
    Then the user receives a response indicating that the account has been deleted