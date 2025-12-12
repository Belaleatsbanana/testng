Feature: Validate ReCAPTCHA on contact form

  Scenario: Check reCAPTCHA validation when submitting contact form without completing it
    Given user is logged in to Wuzzuf
    When user navigates to contact us page
    And user fills subject with "Partnership Request"
    And user fills message with "Partnership message test"
    And clicks send button without completing reCAPTCHA
    Then reCAPTCHA validation error is displayed
