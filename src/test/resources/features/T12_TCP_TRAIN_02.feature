Feature: Delete training certification

  Scenario: Check training certification is deleted successfully
    Given user is logged in to wuzzuf
    When user clicks on edit profile
    And user clicks on Education tab
    And user clicks delete button for training item
    And user confirms deletion
    Then training is deleted successfully
