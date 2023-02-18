@testbots
Feature: ResReq Positive & Negative Testing
  Description: The purpose of this feature is test ResReq webservice

  @Positive @GetUsers
  Scenario: Get Users
    Given User exist in system
    When Get Users api is called
    Then Get Users api should respond with response code: 200
    Then Get Users api response should have right schema

  @Positive @CreateUser
  Scenario Outline: Get User
    When Create User api is called with first name: '<firstName>', last name: '<lastName>', email: '<email>', and job: '<job>'
    Then Create User api should respond with response code: 201
    Then Create User api response should have right schema

    Examples:
      | firstName | lastName   | email                        | job |
      | test      | bots-tamil | test.bosts-tamil@hotmail.com | actor    |

  @Positive @GetUser
  Scenario Outline: Get User
    Given User exist in system
    When Get User api is called with userId: '<id>'
    Then Get User api should respond with response code: <expectedCode>
    Then Get User api response should have right schema

    Examples:
      | id  | expectedCode |
      | 2   | 200          |
      | 1   | 200          |

  @Negative @GetUser
  Scenario Outline: Get User
    Given User exist in system
    When Get User api is called with userId: '<id>'
    Then Get User api should respond with response code: <expectedCode>

    Examples:
      | id  | expectedCode |
      | 250 | 404          |
