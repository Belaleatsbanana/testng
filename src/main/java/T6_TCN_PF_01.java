import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

void main() throws InterruptedException {
    // Set ChromeDriver path
    System.setProperty("webdriver.chrome.driver", "/home/fax/Downloads/chromedriver-linux64/chromedriver");

    // Chrome options
    ChromeOptions option = new ChromeOptions();
    option.addArguments("--remote-allow-origins=*");
    option.addArguments("--start-maximized");
    ChromeDriver driver = new ChromeDriver(option);

    // Implicit wait
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    IO.println("Starting Profile Information Update Test (Blank Alt Phone Validation)...");

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

        // Step 4: Fill in First Name
        WebElement firstNameField = wait.until(ExpectedConditions.elementToBeClickable(By.name("firstName")));
        firstNameField.click();
        firstNameField.sendKeys(Keys.CONTROL + "a");
        firstNameField.sendKeys(Keys.DELETE);
        firstNameField.sendKeys("dummy");
        IO.println("Entered first name: dummy");
        Thread.sleep(500);

        // Step 5: Fill in Middle Name
        WebElement middleNameField = driver.findElement(By.name("middleName"));
        middleNameField.click();
        middleNameField.sendKeys(Keys.CONTROL + "a");
        middleNameField.sendKeys(Keys.DELETE);
        middleNameField.sendKeys("shams");
        IO.println("Entered middle name: shams");
        Thread.sleep(500);

        // Step 6: Fill in Last Name
        WebElement lastNameField = driver.findElement(By.name("lastName"));
        lastNameField.click();
        lastNameField.sendKeys(Keys.CONTROL + "a");
        lastNameField.sendKeys(Keys.DELETE);
        lastNameField.sendKeys("bell");
        IO.println("Entered last name: bell");
        Thread.sleep(500);

        // Step 7: Select Birth Day
        WebElement birthDayInput = driver.findElement(By.id("react-select-2-input"));
        birthDayInput.click();
        IO.println("Clicked birth day dropdown");
        Thread.sleep(1500);

        WebElement birthDayOption = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'react-select-2-option')]"))
        );
        birthDayOption.click();
        IO.println("Selected birth day");
        Thread.sleep(1000);

        // Step 8: Select Birth Month
        WebElement birthMonthInput = driver.findElement(By.id("react-select-3-input"));
        birthMonthInput.click();
        IO.println("Clicked birth month dropdown");
        Thread.sleep(1500);

        WebElement birthMonthOption = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'react-select-3-option')]"))
        );
        birthMonthOption.click();
        IO.println("Selected birth month");
        Thread.sleep(1000);

        // Step 9: Select Birth Year
        WebElement birthYearInput = driver.findElement(By.id("react-select-4-input"));
        birthYearInput.click();
        IO.println("Clicked birth year dropdown");
        Thread.sleep(1500);

        WebElement birthYearOption = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'react-select-4-option')]"))
        );
        birthYearOption.click();
        IO.println("Selected birth year");
        Thread.sleep(1000);

        // Step 10: Select Gender (Female)
        WebElement genderOption = driver.findElement(By.xpath("//div[@id='gender']/div/label[2]/div"));
        genderOption.click();
        IO.println("Selected gender: Female");
        Thread.sleep(800);

        // Step 11: Select Nationality
        WebElement nationalityInput = driver.findElement(By.id("react-select-5-input"));
        nationalityInput.click();
        IO.println("Clicked nationality dropdown");
        Thread.sleep(1500);

        WebElement nationalityOption = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'react-select-5-option')]"))
        );
        nationalityOption.click();
        IO.println("Selected nationality");
        Thread.sleep(1000);

        // Step 12: Select Marital Status
        WebElement maritalStatusOption = driver.findElement(By.xpath("//div[@id='marital-status']/div/label/div"));
        maritalStatusOption.click();
        IO.println("Selected marital status");
        Thread.sleep(800);

        // Step 13: Select something from name dropdown (possibly title or prefix)
        WebElement nameDropdownContainer = driver.findElement(By.xpath("//div[@id='name']/div/div[7]/div"));
        WebElement nameInput = nameDropdownContainer.findElement(By.xpath(".//input[contains(@id,'react-select-')]"));
        nameInput.click();
        IO.println("Clicked name dropdown");
        Thread.sleep(1500);

        String nameInputId = nameInput.getAttribute("id").replace("-input", "");
        WebElement nameOption = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'" + nameInputId + "-option')]"))
        );
        nameOption.click();
        IO.println("Selected name option");
        Thread.sleep(1000);

        // Step 14: Select Military Status
        WebElement militaryStatusContainer = driver.findElement(By.xpath("//div[@id='military-status']/div"));
        WebElement militaryInput = militaryStatusContainer.findElement(By.xpath(".//input[contains(@id,'react-select-')]"));
        militaryInput.click();
        IO.println("Clicked military status dropdown");
        Thread.sleep(1500);

        String militaryInputId = militaryInput.getAttribute("id").replace("-input", "");
        WebElement militaryStatusOption = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'" + militaryInputId + "-option')]"))
        );
        militaryStatusOption.click();
        IO.println("Selected military status");
        Thread.sleep(1000);

        // Step 15: Select Driving License options
        WebElement drivingLicenseOption1 = driver.findElement(By.xpath("//div[@id='driving-license']/div/div/label/div"));
        drivingLicenseOption1.click();
        IO.println("Selected first driving license option");
        Thread.sleep(800);

        WebElement drivingLicenseOption2 = driver.findElement(By.xpath("//div[@id='driving-license']/div[2]/div/label/div"));
        drivingLicenseOption2.click();
        IO.println("Selected second driving license option");
        Thread.sleep(800);

        // Step 16: Select City
        WebElement cityContainer = driver.findElement(By.xpath("//div[@id='location']/div/div"));
        WebElement cityInput = cityContainer.findElement(By.xpath(".//input[contains(@id,'react-select-')]"));
        cityInput.click();
        IO.println("Clicked city dropdown");
        Thread.sleep(1500);

        String cityInputId = cityInput.getAttribute("id").replace("-input", "");
        WebElement cityOption = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'" + cityInputId + "-option')]"))
        );
        cityOption.click();
        IO.println("Selected city");
        Thread.sleep(1000);

        // Step 17: Select Location
        WebElement areaContainer = driver.findElement(By.xpath("//div[@id='location']/div/div[2]"));
        WebElement areaInput = areaContainer.findElement(By.xpath(".//input[contains(@id,'react-select-')]"));
        areaInput.click();
        IO.println("Clicked area dropdown");
        Thread.sleep(1500);

        String areaInputId = areaInput.getAttribute("id").replace("-input", "");
        WebElement areaOption = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'" + areaInputId + "-option')]"))
        );
        areaOption.click();
        IO.println("Selected area");
        Thread.sleep(1000);

        // Step 18: Fill in Primary Phone
        WebElement primaryPhoneField = driver.findElement(By.name("primaryPhone"));
        primaryPhoneField.click();
        primaryPhoneField.sendKeys(Keys.CONTROL + "a");
        primaryPhoneField.sendKeys(Keys.DELETE);
        primaryPhoneField.sendKeys("01116615352");
        IO.println("Entered primary phone: 01116615352");
        Thread.sleep(500);

        // Step 19: Click on Other Phone field and leave it BLANK
        WebElement otherPhoneField = driver.findElement(By.name("otherPhone"));
        otherPhoneField.click();
        otherPhoneField.sendKeys(Keys.CONTROL + "a");
        otherPhoneField.sendKeys(Keys.DELETE);
        IO.println("Cleared other phone field (left blank)");
        Thread.sleep(500);

        // Step 20: Click Save/Submit button
        WebElement saveButton = driver.findElement(By.xpath("//div[@id='app']/div/div[2]/div[2]/form/button"));
        saveButton.click();
        IO.println("Clicked save button");
        Thread.sleep(2000);

        // Step 21: Verify validation error message appears
        WebElement validationError = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//div[@id='contact-info']/div[2]/div/span")
                )
        );

        // Step 22: Validate the error message text
        String expectedErrorMessage = "This is a required field";
        String actualErrorMessage = validationError.getText();

        if (validationError.isDisplayed() && actualErrorMessage.equals(expectedErrorMessage)) {
            IO.println("✓ Test PASSED: Validation error is displayed correctly");
            IO.println("✓ Error message: '" + actualErrorMessage + "'");
            IO.println("✓ Form correctly validates that alternate phone is required");
        } else {
            IO.println("✗ Test FAILED: Validation error not displayed correctly");
            IO.println("✗ Expected: '" + expectedErrorMessage + "'");
            IO.println("✗ Actual: '" + actualErrorMessage + "'");
        }

        boolean isErrorPresent = driver.findElements(
                By.xpath("//div[@id='contact-info']/div[2]/div/span")
        ).size() > 0;

        if (isErrorPresent) {
            IO.println("✓ Validation error element is present in the DOM");
        } else {
            IO.println("✗ Validation error element not found");
        }

    } catch (Exception e) {
        IO.println("✗ Test FAILED with exception: " + e.getMessage());
        e.printStackTrace();
    } finally {
        // Step 23: Close the browser
        Thread.sleep(2000);
        driver.close();
        driver.quit();
        IO.println("Browser closed");
    }
}