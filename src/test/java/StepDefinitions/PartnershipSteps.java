package StepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PartnershipSteps {
    
    // Locators
    private final By profileDropdown = By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div/div/div/div");
    private final By contactUsOption = By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div[2]/div/div[9]/a/div/span");
    private final By subjectField = By.id("subject");
    private final By messageField = By.id("message");
    private final By sendButton = By.id("popup_send");
    private final By captchaError = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Sending...'])[1]/following::p[1]");

    @When("user clicks on become a partner")
    public void user_clicks_on_become_a_partner() throws InterruptedException {
        TestContext.wait.until(ExpectedConditions.elementToBeClickable(profileDropdown)).click();
        System.out.println("Clicked profile dropdown");
        Thread.sleep(1000);

        TestContext.wait.until(ExpectedConditions.elementToBeClickable(contactUsOption)).click();
        System.out.println("Clicked contact us");
        Thread.sleep(2000);
    }

    @And("user fills subject")
    public void user_fills_subject() throws InterruptedException {
        WebElement subject = TestContext.wait.until(ExpectedConditions.elementToBeClickable(subjectField));
        subject.click();
        Thread.sleep(500);
        subject.clear();
        subject.sendKeys("Partnership Request");
        System.out.println("Filled subject field");
        Thread.sleep(1000);
    }

    @And("user fills message")
    public void user_fills_message() throws InterruptedException {
        WebElement message = TestContext.wait.until(ExpectedConditions.elementToBeClickable(messageField));
        message.click();
        Thread.sleep(500);
        message.clear();
        message.sendKeys("Partnership message test");
        System.out.println("Filled message field");
        Thread.sleep(1000);
    }

    @And("clicks send button")
    public void clicks_send_button() throws InterruptedException {
        TestContext.driver.findElement(sendButton).click();
        System.out.println("Clicked send button");
        Thread.sleep(2000);
    }

    @Then("CAPTCHA validation error is displayed")
    public void captcha_validation_error_is_displayed() {
        WebElement errorMessage = TestContext.wait.until(ExpectedConditions.visibilityOfElementLocated(captchaError));

        String expectedErrorMessage = "The Recaptcha field is required.";
        String actualErrorMessage = errorMessage.getText();

        if (actualErrorMessage.equals(expectedErrorMessage)) {
            System.out.println("captcha error displayed");
        } else {
            System.out.println("test failed");
        }
    }
}
