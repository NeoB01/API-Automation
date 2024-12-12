Feature: Valid Login

  Scenario: Post a login valid details
    When the user enters valid credentials
    Then the user receives a response indicating that the login is successful