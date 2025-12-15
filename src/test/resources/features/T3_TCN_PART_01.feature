Feature: feature to test partnership submission without doing CAPTCHA

  Scenario: ensure partnership request is unsuccessful with invalid CAPTCHA
    Given user is logged in to wuzzuf
    When user clicks on become a partner
    And user fills subject
    And user fills message
    And clicks send button
    Then CAPTCHA validation error is displayed
