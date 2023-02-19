@Negative @All
Feature: ResReq Negative Testing
  Description: The purpose of this feature is test ResReq webservice

  @GetUser
  Scenario Outline: Get User
  Description: The purpose of this test is check whether get user api returns proper error code for non exist user
    Given User exist in system
    When Get User api is called with userId: '<id>'
    Then Get User api should respond with response code: <expectedCode>

    Examples:
      | id  | expectedCode |
      | 250 | 404          |

  @CreateUser
  Scenario Outline: Create User
  Description: The purpose of this test is check whether create user api works as expected
    When Create User api is called with first name: '<firstName>', last name: '<lastName>', and job: '<job>'
    Then Create User api should respond with response code: 400

    Examples:
      | firstName | lastName   | job   |
      | test      | bots-tamil | actor |
