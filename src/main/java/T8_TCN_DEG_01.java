import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

    IO.println("Starting Add Education Negative Test (Missing Field of Study)...");

    try {
        WuzzufLogin loginHelper = new WuzzufLogin(driver);
        loginHelper.login();
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div/div/div/div"))).click();
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div[2]/div/div[4]/a/span"))).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Education"))).click();
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button']"))).click();
        Thread.sleep(2000);

        // Degree Level
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Degree Level'])[1]/following::div[3]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-1')]"))).click();

        // Degree Name
        WebElement degreeNameField = wait.until(ExpectedConditions.elementToBeClickable(By.name("degreeDisplayName")));
        degreeNameField.click();
        degreeNameField.sendKeys(Keys.CONTROL + "a", Keys.DELETE, "MS");

        // Country
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Country'])[1]/following::div[3]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-0')]"))).click();

        // School
        WebElement schoolField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("school")));
        driver.executeScript("arguments[0].scrollIntoView(true);", schoolField);
        try { schoolField.click(); } catch (Exception e) { driver.executeScript("arguments[0].click();", schoolField); }
        schoolField.sendKeys(Keys.CONTROL + "a", Keys.DELETE, "BUE");
        IO.println("Entered school: BUE");
        Thread.sleep(500);

        // We scroll to it to ensure visibility, but we DO NOT type in it to trigger the error.
        WebElement fieldOfStudyInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//label[contains(text(), 'Field')]/parent::div/following-sibling::div//input[@type='text']")
                )
        );
        driver.executeScript("arguments[0].scrollIntoView({block: 'center'});", fieldOfStudyInput);
        Thread.sleep(300);
        IO.println("Scrolled to Field of Study (Leaving empty for negative test)");

        // fieldOfStudyInput.click();
        // fieldOfStudyInput.sendKeys("Computer Science"); // Using a valid subject name
        // fieldOfStudyInput.sendKeys(Keys.TAB);
        // IO.println("Typed in field of study");

        // Step 10: Select Start Year
        WebElement startYearDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Start Year'])[1]/following::div[3]")));
        driver.executeScript("arguments[0].scrollIntoView({block: 'center'});", startYearDropdown);
        startYearDropdown.click();
        WebElement startYearOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-3')]")));
        driver.executeScript("arguments[0].click();", startYearOption);
        IO.println("Selected Start Year");

        // Step 11: Select End Year
        WebElement endYearDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='End Year'])[1]/following::div[4]")));
        driver.executeScript("arguments[0].scrollIntoView({block: 'center'});", endYearDropdown);
        endYearDropdown.click();
        WebElement endYearOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-0')]")));
        driver.executeScript("arguments[0].click();", endYearOption);
        IO.println("Selected End Year");

        // Step 12: Select Grade
        WebElement gradeDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Grade'])[1]/following::div[3]")));
        driver.executeScript("arguments[0].scrollIntoView({block: 'center'});", gradeDropdown);
        gradeDropdown.click();
        WebElement gradeOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-0')]")));
        driver.executeScript("arguments[0].click();", gradeOption);
        IO.println("Selected Grade");
        Thread.sleep(500);

        // Step 15: Click Save button
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancel'])[1]/following::button[1]")));
        driver.executeScript("arguments[0].scrollIntoView({block: 'center'});", saveButton);
        Thread.sleep(500);
        saveButton.click();
        IO.println("Clicked Save button without filling Field of Study");
        Thread.sleep(2000);

        // --- UPDATED STEP 16:
        // We use text-based matching instead of absolute XPath to avoid "Expected condition failed"
        WebElement errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(), 'Please add at least 1 studies')]")
                )
        );

        String expectedErrorMessage = "Please add at least 1 studies";
        String actualErrorMessage = errorMessage.getText();

        if (actualErrorMessage.contains("Please add at least")) {
            IO.println("Test PASSED: Validation error displayed correctly");
            IO.println("Actual: '" + actualErrorMessage + "'");
        } else {
            IO.println("Test FAILED: Error message mismatch");
            IO.println("Expected part of: '" + expectedErrorMessage + "'");
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