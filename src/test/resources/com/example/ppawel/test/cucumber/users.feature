Feature: Users

  Scenario: Registration - successful
    Given e-mail test@test.com
    And password 123456
    And date of birth 1984-01-22
    And street Test Street
    And city Kraków
    And country PL
    When I try to register
    Then I should be registered

  Scenario: Registration - bad e-mail
    Given e-mail test
    When I try to register
    Then I should get error for field email

  Scenario: Registration - bad e-mail2
    Given e-mail test@.test
    When I try to register
    Then I should get error for field email

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

  Scenario: Registration - same e-mail twice
    Given e-mail test2@test.com
    And password 123456
    And date of birth 1984-01-22
    And street Test Street
    And city Kraków
    And country PL
    When I try to register twice
    Then I should get error for field email

  Scenario: Registration - no data
    When I try to register
    Then I should get error for field email
    And I should get error for field birthDate
    And I should get error for field city
    And I should get error for field street
    And I should get error for field password

  Scenario: Registration - bad date
    Given date of birth 1503-12-01
    When I try to register
    Then I should get error for field birthDate
