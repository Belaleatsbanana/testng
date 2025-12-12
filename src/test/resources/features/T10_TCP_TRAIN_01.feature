Feature: Add training certification successfully

  Scenario: Check training certification is added successfully with valid information
    Given user is logged in to Wuzzuf
    When user navigates to profile page
    And user clicks on Education tab
    And user clicks Add Training button
    And user fills training title with "SQA"
    And user fills training organization with "BUE"
    And user selects training start month
    And user selects training start year
    And user fills training description with "SQA"
    And clicks save training button
    Then training is added successfully
