Feature: Delete education

  Scenario: Check education is deleted successfully
    Given user is logged in to Wuzzuf
    When user navigates to profile page
    And user clicks on Education tab
    And user clicks delete button for second education item
    And user confirms deletion
    Then education is deleted successfully
