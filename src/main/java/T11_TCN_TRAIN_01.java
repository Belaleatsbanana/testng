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

    IO.println("Starting Add Training Negative Test (Missing Organization)...");

    try {
        // Step 1: Login
        WuzzufLogin loginHelper = new WuzzufLogin(driver);
        loginHelper.login();
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 2 & 3: Navigate to Profile
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div/div/div/div"))).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div[2]/div/div[4]/a/span"))).click();
        IO.println("Navigated to profile page");
        Thread.sleep(2000);

        // Step 4: Click Education Tab
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Education"))).click();
        IO.println("Clicked on Education tab");
        Thread.sleep(1500);

        // Step 5: Click on Add Training/Certification Button
        WebElement addTrainingButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='app']/div/div[2]/div[2]/div[5]/button")));
        driver.executeScript("arguments[0].scrollIntoView({block: 'center'});", addTrainingButton);
        addTrainingButton.click();
        IO.println("Clicked on Add Training/Certification button");
        Thread.sleep(2000);

        // Step 6: Fill in Title
        WebElement titleField = wait.until(ExpectedConditions.elementToBeClickable(By.name("title")));
        titleField.sendKeys("SQA");
        IO.println("Entered title: SQA");
        Thread.sleep(500);


        // Step 7: Select Start Month
        WebElement monthDropdown = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Month'])[1]/following::div[3]"))
        );
        monthDropdown.click();
        Thread.sleep(500);

        WebElement monthOption = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-0')]"))
        );
        driver.executeScript("arguments[0].click();", monthOption);
        IO.println("Selected Start Month");
        Thread.sleep(500);

        // Step 8: Select Start Year
        WebElement yearDropdown = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Year'])[1]/following::div[4]"))
        );
        yearDropdown.click();
        Thread.sleep(500);

        WebElement yearOption = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-0')]"))
        );
        driver.executeScript("arguments[0].click();", yearOption);
        IO.println("Selected Start Year");
        Thread.sleep(500);

        // Step 9: Fill in Description
        WebElement descriptionField = wait.until(ExpectedConditions.elementToBeClickable(By.name("description")));
        descriptionField.sendKeys("SQA");
        IO.println("Entered description: SQA");
        Thread.sleep(500);

        // Step 10: Click Save button
        WebElement saveButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancel'])[1]/following::button[1]")
                )
        );
        saveButton.click();
        IO.println("Clicked Save button without filling Organization");
        Thread.sleep(1500);

        // Step 11: Wait for and assert validation error message (Robust XPath)
        String expectedErrorMessage = "This is a required field";

        WebElement errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(), '" + expectedErrorMessage + "')]")
                )
        );

        // Step 12: Verify the error message text
        String actualErrorMessage = errorMessage.getText().trim();

        if (actualErrorMessage.equals(expectedErrorMessage)) {
            IO.println("Test PASSED: Validation error displayed correctly");
            IO.println("Expected: '" + expectedErrorMessage + "'");
            IO.println("Actual: '" + actualErrorMessage + "'");
        } else {
            IO.println("Test FAILED: Error message mismatch");
            IO.println("Expected: '" + expectedErrorMessage + "'");
            IO.println("Actual: '" + actualErrorMessage + "'");
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