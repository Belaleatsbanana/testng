import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

void main() throws InterruptedException {
    // Locators
    By profileDropdown = By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div/div/div/div");
    By editProfileOption = By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div[2]/div/div[4]/a/span");
    By firstNameField = By.name("firstName");
    By middleNameField = By.name("middleName");
    By lastNameField = By.name("lastName");
    By birthDayInput = By.id("react-select-2-input");
    By birthDayOption = By.xpath("//div[contains(@id,'react-select-2-option')]");
    By birthMonthInput = By.id("react-select-3-input");
    By birthMonthOption = By.xpath("//div[contains(@id,'react-select-3-option')]");
    By birthYearInput = By.id("react-select-4-input");
    By birthYearOption = By.xpath("//div[contains(@id,'react-select-4-option')]");
    By genderOption = By.xpath("//div[@id='gender']/div/label[2]/div");
    By nationalityInput = By.id("react-select-5-input");
    By nationalityOption = By.xpath("//div[contains(@id,'react-select-5-option')]");
    By maritalStatusOption = By.xpath("//div[@id='marital-status']/div/label/div");
    By nameDropdownContainer = By.xpath("//div[@id='name']/div/div[7]/div");
    By militaryStatusContainer = By.xpath("//div[@id='military-status']/div");
    By drivingLicense1 = By.xpath("//div[@id='driving-license']/div/div/label/div");
    By drivingLicense2 = By.xpath("//div[@id='driving-license']/div[2]/div/label/div");
    By cityContainer = By.xpath("//div[@id='location']/div/div");
    By areaContainer = By.xpath("//div[@id='location']/div/div[2]");
    By primaryPhoneField = By.name("primaryPhone");
    By otherPhoneField = By.name("otherPhone");
    By saveButton = By.xpath("//div[@id='app']/div/div[2]/div[2]/form/button");
    By successModal = By.xpath("/html/body/div[1]/div/div[4]/div/div/div");
    By validationError = By.xpath("//div[@id='contact-info']/div[2]/div/span");


    System.setProperty("webdriver.chrome.driver", "/home/fax/Downloads/chromedriver-linux64/chromedriver");

    ChromeOptions option = new ChromeOptions();
    option.addArguments("--remote-allow-origins=*");
    option.addArguments("--start-maximized");
    ChromeDriver driver = new ChromeDriver(option);

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    try {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1: Login
        new WuzzufLogin(driver).login();
        Thread.sleep(2000);

        // Step 2: Navigate to profile
        wait.until(ExpectedConditions.elementToBeClickable(
            profileDropdown
        )).click();
        IO.println("clicked profile");
        Thread.sleep(1500);

        wait.until(ExpectedConditions.elementToBeClickable(
            editProfileOption
        )).click();
        IO.println("went to profile");
        Thread.sleep(2000);

        // Step 3: Fill personal information
        WebElement firstName = wait.until(ExpectedConditions.elementToBeClickable(firstNameField));
        firstName.click();
        Thread.sleep(200);
        firstName.sendKeys(Keys.CONTROL + "a");
        firstName.sendKeys(Keys.BACK_SPACE);
        firstName.sendKeys("dummy");
        IO.println("filled first name");
        Thread.sleep(500);

        WebElement middleName = driver.findElement(middleNameField);
        middleName.click();
        Thread.sleep(200);
        middleName.sendKeys(Keys.CONTROL + "a");
        middleName.sendKeys(Keys.BACK_SPACE);
        middleName.sendKeys("shams");
        IO.println("filled middle name");
        Thread.sleep(500);

        WebElement lastName = driver.findElement(lastNameField);
        lastName.click();
        Thread.sleep(200);
        lastName.sendKeys(Keys.CONTROL + "a");
        lastName.sendKeys(Keys.BACK_SPACE);
        lastName.sendKeys("bell");
        IO.println("filled last name");
        Thread.sleep(500);

        // Step 4: Select birth date
        driver.findElement(birthDayInput).click();
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(
            birthDayOption
        )).click();
        Thread.sleep(1000);

        driver.findElement(birthMonthInput).click();
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(
            birthMonthOption
        )).click();
        Thread.sleep(1000);

        driver.findElement(birthYearInput).click();
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(
            birthYearOption
        )).click();
        Thread.sleep(1000);

        // Step 5: Select gender and other fields
        driver.findElement(genderOption).click();
        Thread.sleep(800);

        driver.findElement(nationalityInput).click();
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(
            nationalityOption
        )).click();
        Thread.sleep(1000);

        driver.findElement(maritalStatusOption).click();
        Thread.sleep(800);

        // Step 6: Select additional fields
        WebElement nameDropdown = driver.findElement(nameDropdownContainer);
        WebElement nameInput = nameDropdown.findElement(By.xpath(".//input[contains(@id,'react-select-')]"));
        nameInput.click();
        Thread.sleep(1500);
        String nameInputId = nameInput.getAttribute("id").replace("-input", "");
        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[contains(@id,'" + nameInputId + "-option')]")
        )).click();
        Thread.sleep(1000);

        WebElement militaryStatus = driver.findElement(militaryStatusContainer);
        WebElement militaryInput = militaryStatus.findElement(By.xpath(".//input[contains(@id,'react-select-')]"));
        militaryInput.click();
        Thread.sleep(1500);
        String militaryInputId = militaryInput.getAttribute("id").replace("-input", "");
        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[contains(@id,'" + militaryInputId + "-option')]")
        )).click();
        Thread.sleep(1000);

        driver.findElement(drivingLicense1).click();
        Thread.sleep(800);
        driver.findElement(drivingLicense2).click();
        Thread.sleep(800);

        WebElement city = driver.findElement(cityContainer);
        WebElement cityInput = city.findElement(By.xpath(".//input[contains(@id,'react-select-')]"));
        cityInput.click();
        Thread.sleep(1500);
        String cityInputId = cityInput.getAttribute("id").replace("-input", "");
        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[contains(@id,'" + cityInputId + "-option')]")
        )).click();
        Thread.sleep(1000);

        WebElement area = driver.findElement(areaContainer);
        WebElement areaInput = area.findElement(By.xpath(".//input[contains(@id,'react-select-')]"));
        areaInput.click();
        Thread.sleep(1500);
        String areaInputId = areaInput.getAttribute("id").replace("-input", "");
        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[contains(@id,'" + areaInputId + "-option')]")
        )).click();
        Thread.sleep(1000);

        // Step 7: Fill primary phone but leave other phone blank
        WebElement primaryPhone = driver.findElement(primaryPhoneField);
        primaryPhone.click();
        Thread.sleep(200);
        primaryPhone.sendKeys(Keys.CONTROL + "a");
        primaryPhone.sendKeys(Keys.BACK_SPACE);
        primaryPhone.sendKeys("01116615352");
        IO.println("filled primary phone");
        Thread.sleep(500);

        WebElement otherPhone = driver.findElement(otherPhoneField);
        otherPhone.click();
        Thread.sleep(200);
        otherPhone.sendKeys(Keys.CONTROL + "a");
        otherPhone.sendKeys(Keys.BACK_SPACE);
        IO.println("left other phone blank");
        Thread.sleep(500);

        // Step 8: Click save
        driver.findElement(saveButton).click();
        IO.println("clicked save");
        Thread.sleep(2000);

        // Step 9: Verify validation error appears
        WebElement validation = wait.until(ExpectedConditions.presenceOfElementLocated(
            validationError
        ));

        String expectedErrorMessage = "This is a required field";
        String actualErrorMessage = validation.getText();

        if (validation.isDisplayed() && actualErrorMessage.equals(expectedErrorMessage)) {
            IO.println("validation error showed");
        } else {
            IO.println("test failed");
        }

    } catch (Exception e) {
        IO.println(e.getMessage());
        e.printStackTrace();
    } finally {
        driver.close();
        driver.quit();
    }
}
