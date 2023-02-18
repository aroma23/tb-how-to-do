@testbots
Feature: ResReq Positive & Negative Testing
  Description: The purpose of this feature is test ResReq webservice

  Scenario: Get User
    Given User exist in system
    When Get User api is called with userId: '2'
    Then Get User api should respond with response code: 200
    Then Get User api response should have right schema

