Feature: Search product without parameter

  Scenario: Search product without product name or product parameter
    Given user clicks the products link
    When user passes the products url
    Then user receives a post response without product name or product parameter