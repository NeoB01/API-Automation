Feature: Update user account

  Scenario: Put to update user account details
    When the user updates their account details with valid information
    Then the user receives a response indicating that the account has been updated successfully