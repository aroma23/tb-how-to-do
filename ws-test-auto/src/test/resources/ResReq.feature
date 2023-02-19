@testbots
Feature: ResReq Positive & Negative Testing
  Description: The purpose of this feature is test ResReq webservice

  @Positive @GetUsers
  Scenario: Get Users
  Description: The purpose of this test is check whether get users api works as expected
    Given User exist in system
    When Get Users api is called
    Then Get Users api should respond with response code: 200
    Then Get Users api response should have right schema

  @Positive @CreateUser
  Scenario Outline: Create User
  Description: The purpose of this test is check whether create users api works as expected
    When Create User api is called with first name: '<firstName>', last name: '<lastName>', email: '<email>', and job: '<job>'
    Then Create User api should respond with response code: 201
    Then Create User api response should have right schema

    Examples:
      | firstName | lastName   | email                        | job   |
      | test      | bots-tamil | test.bosts-tamil@hotmail.com | actor |

  @Positive @GetUser
  Scenario Outline: Get User
  Description: The purpose of this test is check whether get user api works as expected
    Given User exist in system
    When Get User api is called with userId: '<id>'
    Then Get User api should respond with response code: <expectedCode>
    Then Get User api response should have right schema
    Then Get User api response should have correct firstName: '<firstName>', and lastName: '<lastName>'

    Examples:
      | id | expectedCode | firstName | lastName |
      | 1  | 200          | George    | Bluth    |
      | 2  | 200          | Janet     | Weaver   |

  @Positive @DeleteUser
  Scenario Outline: Delete User
  Description: The purpose of this test is check whether delete user api works as expected
    Given User exist in system
    When Delete User api is called with userId: '<id>'
    Then Delete User api should respond with response code: <expectedCode>

    Examples:
      | id  | expectedCode |
      | 2   | 204          |
      | 1   | 204          |

  @Negative @GetUserNegative
  Scenario Outline: Get User
  Description: The purpose of this test is check whether get user api returns proper error code for non exist user
    Given User exist in system
    When Get User api is called with userId: '<id>'
    Then Get User api should respond with response code: <expectedCode>

    Examples:
      | id  | expectedCode |
      | 250 | 404          |

  @Negative @CreateUserNegative
  Scenario Outline: Create User
  Description: The purpose of this test is check whether create users api works as expected
    When Create User api is called with first name: '<firstName>', last name: '<lastName>', and job: '<job>'
    Then Create User api should respond with response code: 400

    Examples:
      | firstName | lastName   | job   |
      | test      | bots-tamil | actor |
