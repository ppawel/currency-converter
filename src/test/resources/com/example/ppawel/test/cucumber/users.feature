Feature: Users

  Scenario: Successful registration
    Given e-mail test@test.com
    And date of birth 1984-01-22
    When I try to register
    Then I should be successful
