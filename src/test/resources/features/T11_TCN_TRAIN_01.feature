Feature: Validate organization field on training form

  Scenario: Check validation error when organization field is missing
    Given user is logged in to wuzzuf
    When user clicks on edit profile
    And user clicks on Education tab
    And user clicks Add Training button
    And user fills training title with "SQA"
    And user leaves organization empty
    And user selects training start month
    And user selects training start year
    And user fills training description with "SQA"
    And clicks save training button
    Then validation error for organization is displayed
