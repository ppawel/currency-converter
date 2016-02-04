Feature: Users

  Scenario: Registration - successful
    Given e-mail test@test.com
    And password 123456
    And date of birth 1984-01-22
    When I try to register
    Then I should be registered

  Scenario: Login - successful
    Given e-mail test@test.com
    And password 123456
    When I try to login
    Then I should be logged in

  Scenario: Login - bad password
    Given e-mail test@test.com
    And password someotherpassword
    When I try to login
    Then I should fail to log in
