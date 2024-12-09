Feature: Get account

  Scenario: Get user account by email

    Given user clicks the get user url
    When user passes the url of get user
    Then user receives a response of user account
