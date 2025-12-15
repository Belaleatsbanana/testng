Feature: feature to validate alternate phone field is required

  Scenario: check validation error when alternate phone field is left blank
    Given user is logged in to wuzzuf
    When user clicks on edit profile
    And user fills personal information
    And user selects birth date
    And user selects gender and nationality
    And user selects marital status
    And user selects additional profile fields
    And user selects driving licenses
    And user selects location
    And user fills primary phone but leaves alternate phone blank
    And clicks save profile button
    Then validation error for alternate phone is displayed
