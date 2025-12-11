import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

void main() throws InterruptedException {
    System.setProperty("webdriver.chrome.driver", "/home/fax/Downloads/chromedriver-linux64/chromedriver");

    ChromeOptions option = new ChromeOptions();
    option.addArguments("--remote-allow-origins=*");
    option.addArguments("--start-maximized");
    ChromeDriver driver = new ChromeDriver(option);


    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    IO.println("Starting Recaptcha Validation Test...");

    try {
        // Step 1: Navigate to Wuzzuf homepage
        driver.get("https://wuzzuf.net");
        IO.println("Navigated to https://wuzzuf.net");
        Thread.sleep(1000);

        // Step 1: Login using WuzzufLogin utility
        WuzzufLogin loginHelper = new WuzzufLogin(driver);
        loginHelper.login();
        Thread.sleep(2000);

        // Create WebDriverWait for explicit waits
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 2: Wait for and click on user profile dropdown/menu
        WebElement profileDropdown = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div/div/div/div")
                )
        );
        profileDropdown.click();
        IO.println("Clicked on profile dropdown");
        Thread.sleep(1000);

        // Step 3: Click on "Contact Us" option in the dropdown menu
        WebElement contactUsOption = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div[2]/div/div[9]/a/div/span")
                )
        );
        contactUsOption.click();
        IO.println("Clicked on 'Contact Us' option");
        Thread.sleep(2000);

        // Step 4: Wait for and click on the subject field
        WebElement subjectField = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("subject"))
        );
        subjectField.click();
        IO.println("Clicked on subject field");
        Thread.sleep(500);

        // Step 5: Enter subject text
        subjectField.clear();
        subjectField.sendKeys("Partnership Request");
        IO.println("Entered subject: Partnership Request");
        Thread.sleep(1000);

        // Step 6: Click on the message field
        WebElement messageField = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("message"))
        );
        messageField.click();
        IO.println("Clicked on message field");
        Thread.sleep(500);

        // Step 7: Enter message text
        messageField.clear();
        messageField.sendKeys("Partnership message test");
        IO.println("Entered message: Partnership message test");
        Thread.sleep(1000);

        // Step 8: Click the send button (without completing reCAPTCHA)
        WebElement sendButton = driver.findElement(By.id("popup_send"));
        sendButton.click();
        IO.println("Clicked send button without completing reCAPTCHA");
        Thread.sleep(2000);

        // Step 9: Wait for and locate the error message
        WebElement errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Sending...'])[1]/following::p[1]")
                )
        );

        // Step 10: Verify the error message text
        String expectedErrorMessage = "The Recaptcha field is required.";
        String actualErrorMessage = errorMessage.getText();

        if (actualErrorMessage.equals(expectedErrorMessage)) {
            IO.println("✓ Test PASSED: Recaptcha validation error displayed correctly");
            IO.println("Expected: '" + expectedErrorMessage + "'");
            IO.println("Actual: '" + actualErrorMessage + "'");
        } else {
            IO.println("✗ Test FAILED: Error message mismatch");
            IO.println("Expected: '" + expectedErrorMessage + "'");
            IO.println("Actual: '" + actualErrorMessage + "'");
        }

        if (errorMessage.isDisplayed()) {
            IO.println("Error message is visible to user");
        } else {
            IO.println("Error message is not visible");
        }

    } catch (Exception e) {
        IO.println("Test FAILED with exception: " + e.getMessage());
        e.printStackTrace();
    } finally {
        Thread.sleep(2000);
        driver.close();
        driver.quit();
        IO.println("Browser closed");
    }
}