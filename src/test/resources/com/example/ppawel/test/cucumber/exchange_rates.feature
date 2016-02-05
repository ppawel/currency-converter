Feature: Exchange Rates

  Scenario: Get current USD/EUR rate - mock
    Given base currency USD
    And target currency EUR
    And mock currency data provider
    And exchange rate 1.25
    When I ask for current exchange rate
    Then I should get 1.25

  Scenario: Get current USD/EUR rate - OER API
    Given base currency USD
    And target currency EUR
    And OER currency data provider
    When I ask for current exchange rate
    Then I should get a reasonable result

  Scenario: Get current USD/EUR rate - OER API
    Given base currency USD
    And target currency GBP
    And OER currency data provider
    When I ask for exchange rate on 2005-05-23
    Then I should get 0.546831

  Scenario: Get current GBP/EUR rate - OER API
    Given base currency GBP
    And target currency EUR
    And OER currency data provider
    When I ask for current exchange rate
    Then I should get no result

  Scenario: Check saved user queries
    Given base currency USD
    And target currency EUR
    And mock currency data provider
    When I ask 5 times for current exchange rate
    Then I should get at least 5 user queries

  Scenario: Convert using mock
    Given base currency USD
    And target currency EUR
    And mock currency data provider
    And amount 102.50
    And exchange rate 0.93
    When I ask for conversion
    Then I should get 95.325
