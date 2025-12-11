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

    IO.println("Starting Add Education Test...");

    try {
        // Step 1: Login using WuzzufLogin utility
        WuzzufLogin loginHelper = new WuzzufLogin(driver);
        loginHelper.login();
        Thread.sleep(2000);

        // Create WebDriverWait for explicit waits
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 2: Click on user profile dropdown/menu
        WebElement profileDropdown = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div/div/div/div")
                )
        );
        profileDropdown.click();
        IO.println("Clicked on profile dropdown");
        Thread.sleep(1500);

        // Step 3: Click on profile menu option
        WebElement profileMenuOption = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div[2]/div/div[4]/a/span")
                )
        );
        profileMenuOption.click();
        IO.println("Navigated to profile page");
        Thread.sleep(2000);

        // Step 4: Click on Education link/tab
        WebElement educationLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.linkText("Education"))
        );
        educationLink.click();
        IO.println("Clicked on Education tab");
        Thread.sleep(1500);

        // Step 5: Click on Add Education button (button[@type='button'])
        WebElement addEducationButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button']"))
        );
        addEducationButton.click();
        IO.println("Clicked on Add Education button");
        Thread.sleep(2000);

        // Step 6: Select Degree Level
        WebElement degreeLevelField = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//div[@data-field='educationalDegree']")
                )
        );
        IO.println("Education form loaded");

        // Click on the dropdown control area (not the input)
        WebElement degreeLevelControl = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@data-field='educationalDegree']//div[contains(@class, 'css-5usvjy-control') or contains(@class, 'control')]")
                )
        );
        degreeLevelControl.click();
        IO.println("Clicked on Degree Level dropdown");
        Thread.sleep(1000);

        // Wait for the menu to appear and select the first option
        WebElement degreeLevelOption = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-1')]")
                )
        );
        degreeLevelOption.click();
        IO.println("Selected Degree Level option");
        Thread.sleep(500);

        // Wait for dropdown menu to close
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[contains(@id, 'react-select') and contains(@class, 'menu')]")
        ));
        Thread.sleep(500);

        // Step 7: Fill in Degree Display Name
        WebElement degreeNameField = driver.findElement(By.name("degreeDisplayName"));
        degreeNameField.click();
        degreeNameField.sendKeys(Keys.CONTROL + "a");
        degreeNameField.sendKeys(Keys.DELETE);
        degreeNameField.sendKeys("MS");
        IO.println("Entered degree name: MS");
        Thread.sleep(500);

        // Step 8: Select Country
        WebElement countryControl = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//label[contains(text(), 'Country')]/following-sibling::div//div[contains(@class, 'control')]")
                )
        );
        countryControl.click();
        IO.println("Clicked on Country dropdown");
        Thread.sleep(1000);

        WebElement countryOption = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-')]")
                )
        );
        countryOption.click();
        IO.println("Selected Country");
        Thread.sleep(1000);

        // Wait for dropdown menu to close
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[contains(@id, 'react-select') and contains(@class, 'menu')]")
        ));
        Thread.sleep(500);

        // Step 9: Fill in School/University name
        WebElement schoolField = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.name("school"))
        );
        driver.executeScript("arguments[0].scrollIntoView(true);", schoolField);
        Thread.sleep(300);
        driver.executeScript("arguments[0].focus();", schoolField);
        driver.executeScript("arguments[0].value = '';", schoolField);
        schoolField.sendKeys("BUE");
        IO.println("Entered school: BUE");
        Thread.sleep(1000);

        // Press TAB to dismiss any autocomplete dropdown
        schoolField.sendKeys(Keys.TAB);
        IO.println("Moved to next field");
        Thread.sleep(500);

        // Step 10-12: Fill in Field of Study
        WebElement fieldOfStudyInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//label[contains(text(), 'Field')]/parent::div/following-sibling::div//input[@type='text']")
                )
        );
        driver.executeScript("arguments[0].scrollIntoView(true);", fieldOfStudyInput);
        Thread.sleep(300);
        fieldOfStudyInput.click();
        fieldOfStudyInput.sendKeys("BUE");
        IO.println("Typed in field of study: BUE");
        Thread.sleep(1000);

        // Press TAB to move to next field and close any dropdown
        fieldOfStudyInput.sendKeys(Keys.TAB);
        IO.println("Moved to next field after Field of Study");
        Thread.sleep(2000);

        // Step 13: Select Start Year
        WebElement startYearControl = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//label[contains(text(), 'Start Year')]/following-sibling::div//div[contains(@class, 'control')]")
                )
        );
        startYearControl.click();
        IO.println("Clicked on Start Year dropdown");
        Thread.sleep(1000);

        WebElement startYearOption = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-')]")
                )
        );
        startYearOption.click();
        IO.println("Selected Start Year");

        // Wait for dropdown menu to close
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[contains(@id, 'react-select') and contains(@class, 'menu')]")
        ));
        Thread.sleep(500);

        // Step 14: Select End Year
        WebElement endYearControl = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//label[contains(text(), 'End Year')]/following-sibling::div//div[contains(@class, 'control')]")
                )
        );
        endYearControl.click();
        IO.println("Clicked on End Year dropdown");
        Thread.sleep(1000);

        WebElement endYearOption = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-')]")
                )
        );
        endYearOption.click();
        IO.println("Selected End Year");

        // Wait for dropdown menu to close
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[contains(@id, 'react-select') and contains(@class, 'menu')]")
        ));
        Thread.sleep(500);

        // Step 15: Select Grade
        WebElement gradeControl = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//label[contains(text(), 'Grade')]/following-sibling::div//div[contains(@class, 'control')]")
                )
        );
        gradeControl.click();
        IO.println("Clicked on Grade dropdown");
        Thread.sleep(1000);

        WebElement gradeOption = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-')]")
                )
        );
        gradeOption.click();
        IO.println("Selected Grade");

        // Wait for dropdown menu to close
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[contains(@id, 'react-select') and contains(@class, 'menu')]")
        ));
        Thread.sleep(500);

        // Step 16: Fill in Studied Subjects
        WebElement studiedSubjectsField = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.name("studiedSubjects"))
        );
        driver.executeScript("arguments[0].scrollIntoView({block: 'center'});", studiedSubjectsField);
        Thread.sleep(500);

        // Use JavaScript to click if regular click is intercepted
        try {
            wait.until(ExpectedConditions.elementToBeClickable(studiedSubjectsField));
            studiedSubjectsField.click();
        } catch (Exception e) {
            driver.executeScript("arguments[0].click();", studiedSubjectsField);
        }

        studiedSubjectsField.sendKeys(Keys.CONTROL + "a");
        studiedSubjectsField.sendKeys(Keys.DELETE);
        studiedSubjectsField.sendKeys("SQA");
        IO.println("Entered studied subjects: SQA");
        Thread.sleep(500);

        // Step 17: Fill in Notes
        WebElement notesField = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.name("notes"))
        );
        driver.executeScript("arguments[0].scrollIntoView({block: 'center'});", notesField);
        Thread.sleep(500);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(notesField));
            notesField.click();
        } catch (Exception e) {
            driver.executeScript("arguments[0].click();", notesField);
        }

        notesField.sendKeys(Keys.CONTROL + "a");
        notesField.sendKeys(Keys.DELETE);
        notesField.sendKeys("SQA");
        IO.println("Entered notes: SQA");
        Thread.sleep(500);

        // Step 18: Click Save button
        WebElement saveButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancel'])[1]/following::button[1]")
                )
        );
        saveButton.click();
        IO.println("Clicked Save button");
        Thread.sleep(2000);

        // Step 19: Wait for and verify success modal appears
        WebElement successModal = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("/html/body/div[1]/div/div[5]/div/div")
                )
        );

        // Step 20: Validate the test result
        if (successModal != null && successModal.isDisplayed()) {
            IO.println("Test PASSED: Success modal is displayed");
            IO.println("Education entry added successfully");
        } else {
            IO.println("Test FAILED: Success modal is not visible");
        }

        // Additional verification
        boolean isSuccessModalPresent = driver.findElements(
                By.xpath("/html/body/div[1]/div/div[5]/div/div")
        ).size() > 0;

        if (isSuccessModalPresent) {
            IO.println("Success modal element is present in the DOM");
        } else {
            IO.println("Success modal element not found");
        }

    } catch (Exception e) {
        IO.println("✗ Test FAILED with exception: " + e.getMessage());
        e.printStackTrace();
    } finally {
        // Step 21: Close the browser
        Thread.sleep(2000);
        driver.close();
        driver.quit();
        IO.println("Browser closed");
    }
}