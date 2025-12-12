Feature: Delete training certification

  Scenario: Check training certification is deleted successfully
    Given user is logged in to Wuzzuf
    When user navigates to profile page
    And user clicks on Education tab
    And user clicks delete button for training item
    And user confirms deletion
    Then training is deleted successfully
