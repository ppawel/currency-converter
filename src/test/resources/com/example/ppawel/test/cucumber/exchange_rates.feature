Feature: Exchange Rates

  Scenario: Get current USD/EUR rate
    Given base currency USD
    And target currency EUR
    When I ask for current exchange rate
    Then I should get 1.25
