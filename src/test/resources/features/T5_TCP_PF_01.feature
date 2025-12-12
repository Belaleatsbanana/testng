Feature: Update profile successfully

  Scenario: Check profile update is successful with valid information
    Given user is logged in to Wuzzuf
    When user navigates to profile page
    And user fills personal information
    And user selects birth date
    And user selects gender and nationality
    And user selects marital status
    And user selects additional profile fields
    And user selects driving licenses
    And user selects location
    And user fills contact information
    And clicks save profile button
    Then profile is updated successfully
