Feature: Invalid Login

  Scenario: Post an invalid login
    Given the user is on the login page
    When the user enters invalid credentials with email "invalidUser" and password "invalidPassword"
    Then the user receives a response indicating that the login has failed