Feature: Validate field of study on education form

  Scenario: Check validation error when field of study is missing
    Given user is logged in to wuzzuf
    When user clicks on edit profile
    And user clicks on Education tab
    And user clicks Add Education button
    And user selects degree level
    And user fills degree name with "MS"
    And user selects country
    And user fills school name with "BUE"
    And user leaves field of study empty
    And user selects start year
    And user selects end year
    And user selects grade
    And clicks save education button
    Then validation error for field of study is displayed
