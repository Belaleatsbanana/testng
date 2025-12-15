Feature: Add education successfully

  Scenario: Check education is added successfully with valid information
    Given user is logged in to wuzzuf
    When user clicks on edit profile
    And user clicks on Education tab
    And user clicks Add Education button
    And user selects degree level
    And user fills degree name with "MS"
    And user selects country
    And user fills school name with "BUE"
    And user fills field of study with "BUE"
    And user selects start year
    And user selects end year
    And user selects grade
    And user fills studied subjects with "SQA"
    And user fills notes with "SQA"
    And clicks save education button
    Then education is added successfully
