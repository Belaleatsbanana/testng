Feature: feature to test profile update functionality

  Scenario: check profile update is successful with valid information
    Given user is logged in to wuzzuf
    When user clicks on edit profile
    And user fills personal information
    And user selects birth date
    And user selects gender and nationality
    And user selects marital status
    And user selects additional profile fields
    And user selects driving licenses
    And user selects location
    And user fills contact information
    And clicks save profile button
    Then success message is displayed
