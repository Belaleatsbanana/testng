import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import java.time.Duration;

public class T6_TCN_PF_01_Test {
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    // Locators
    private final By profileDropdown = By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div/div/div/div");
    private final By editProfileOption = By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div[2]/div/div[4]/a/span");
    private final By firstNameField = By.name("firstName");
    private final By middleNameField = By.name("middleName");
    private final By lastNameField = By.name("lastName");
    private final By birthDayInput = By.id("react-select-2-input");
    private final By birthDayOption = By.xpath("//div[contains(@id,'react-select-2-option')]");
    private final By birthMonthInput = By.id("react-select-3-input");
    private final By birthMonthOption = By.xpath("//div[contains(@id,'react-select-3-option')]");
    private final By birthYearInput = By.id("react-select-4-input");
    private final By birthYearOption = By.xpath("//div[contains(@id,'react-select-4-option')]");
    private final By genderOption = By.xpath("//div[@id='gender']/div/label[2]/div");
    private final By nationalityInput = By.id("react-select-5-input");
    private final By nationalityOption = By.xpath("//div[contains(@id,'react-select-5-option')]");
    private final By maritalStatusOption = By.xpath("//div[@id='marital-status']/div/label/div");
    private final By nameDropdownContainer = By.xpath("//div[@id='name']/div/div[7]/div");
    private final By militaryStatusContainer = By.xpath("//div[@id='military-status']/div");
    private final By drivingLicense1 = By.xpath("//div[@id='driving-license']/div/div/label/div");
    private final By drivingLicense2 = By.xpath("//div[@id='driving-license']/div[2]/div/label/div");
    private final By cityContainer = By.xpath("//div[@id='location']/div/div");
    private final By areaContainer = By.xpath("//div[@id='location']/div/div[2]");
    private final By primaryPhoneField = By.name("primaryPhone");
    private final By otherPhoneField = By.name("otherPhone");
    private final By saveButton = By.xpath("//div[@id='app']/div/div[2]/div[2]/form/button");
    private final By validationError = By.xpath("//div[@id='contact-info']/div[2]/div/span");
    private final By dynamicReactSelectInput = By.xpath(".//input[contains(@id,'react-select-')]");

    @BeforeMethod
    public void setUp() {
        System.out.println("Setting up test...");
        System.setProperty("webdriver.chrome.driver", "/home/fax/Downloads/chromedriver-linux64/chromedriver");
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        option.addArguments("--start-maximized");
        driver = new ChromeDriver(option);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testProfileValidationBlankAlternatePhone() throws InterruptedException {
        // Step 1: Login
        new WuzzufLogin(driver).login();
        Thread.sleep(2000);

        // Step 2: Navigate to profile
        wait.until(ExpectedConditions.elementToBeClickable(
                profileDropdown
        )).click();
        System.out.println("Clicked profile dropdown");
        Thread.sleep(1500);

        wait.until(ExpectedConditions.elementToBeClickable(
                editProfileOption
        )).click();
        System.out.println("Navigated to profile page");
        Thread.sleep(2000);

        // Step 3: Fill personal information
        WebElement firstNameFld = wait.until(ExpectedConditions.elementToBeClickable(firstNameField));
        firstNameFld.click();
        Thread.sleep(200);
        firstNameFld.sendKeys(Keys.CONTROL + "a");
        firstNameFld.sendKeys(Keys.BACK_SPACE);
        firstNameFld.sendKeys("dummy");
        System.out.println("Filled first name");
        Thread.sleep(500);

        WebElement middleNameFld = driver.findElement(middleNameField);
        middleNameFld.click();
        Thread.sleep(200);
        middleNameFld.sendKeys(Keys.CONTROL + "a");
        middleNameFld.sendKeys(Keys.BACK_SPACE);
        middleNameFld.sendKeys("shams");
        System.out.println("Filled middle name");
        Thread.sleep(500);

        WebElement lastNameFld = driver.findElement(lastNameField);
        lastNameFld.click();
        Thread.sleep(200);
        lastNameFld.sendKeys(Keys.CONTROL + "a");
        lastNameFld.sendKeys(Keys.BACK_SPACE);
        lastNameFld.sendKeys("bell");
        System.out.println("Filled last name");
        Thread.sleep(500);

        // Step 4: Select birth date
        driver.findElement(By.id("react-select-2-input")).click();
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@id,'react-select-2-option')]")
        )).click();
        Thread.sleep(1000);

        driver.findElement(By.id("react-select-3-input")).click();
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@id,'react-select-3-option')]")
        )).click();
        Thread.sleep(1000);

        driver.findElement(By.id("react-select-4-input")).click();
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@id,'react-select-4-option')]")
        )).click();
        Thread.sleep(1000);

        // Step 5: Select gender and other fields
        driver.findElement(By.xpath("//div[@id='gender']/div/label[2]/div")).click();
        Thread.sleep(800);

        driver.findElement(By.id("react-select-5-input")).click();
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@id,'react-select-5-option')]")
        )).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//div[@id='marital-status']/div/label/div")).click();
        Thread.sleep(800);

        // Step 6: Select additional fields
        WebElement nameDropdownCont = driver.findElement(nameDropdownContainer);
        WebElement nameInput = nameDropdownCont.findElement(dynamicReactSelectInput);
        nameInput.click();
        Thread.sleep(1500);
        String nameInputId = nameInput.getAttribute("id").replace("-input", "");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@id,'" + nameInputId + "-option')]")
        )).click();
        Thread.sleep(1000);

        WebElement militaryStatusCont = driver.findElement(militaryStatusContainer);
        WebElement militaryInput = militaryStatusCont.findElement(dynamicReactSelectInput);
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

        WebElement cityCont = driver.findElement(cityContainer);
        WebElement cityInput = cityCont.findElement(dynamicReactSelectInput);
        cityInput.click();
        Thread.sleep(1500);
        String cityInputId = cityInput.getAttribute("id").replace("-input", "");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@id,'" + cityInputId + "-option')]")
        )).click();
        Thread.sleep(1000);

        WebElement areaCont = driver.findElement(areaContainer);
        WebElement areaInput = areaCont.findElement(dynamicReactSelectInput);
        areaInput.click();
        Thread.sleep(1500);
        String areaInputId = areaInput.getAttribute("id").replace("-input", "");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@id,'" + areaInputId + "-option')]")
        )).click();
        Thread.sleep(1000);

        // Step 7: Fill primary phone but leave other phone blank
        WebElement primaryPhoneFld = driver.findElement(primaryPhoneField);
        primaryPhoneFld.click();
        Thread.sleep(200);
        primaryPhoneFld.sendKeys(Keys.CONTROL + "a");
        primaryPhoneFld.sendKeys(Keys.BACK_SPACE);
        primaryPhoneFld.sendKeys("01116615352");
        System.out.println("Filled primary phone");
        Thread.sleep(500);

        WebElement otherPhoneFld = driver.findElement(otherPhoneField);
        otherPhoneFld.click();
        Thread.sleep(200);
        otherPhoneFld.sendKeys(Keys.CONTROL + "a");
        otherPhoneFld.sendKeys(Keys.BACK_SPACE);
        System.out.println("Left other phone field blank");
        Thread.sleep(500);

        // Step 8: Click save
        driver.findElement(saveButton).click();
        System.out.println("Clicked save button");
        Thread.sleep(2000);


    }

    @AfterMethod
    public void tearDown() {
        // Step 9: Verify validation error appears
        WebElement validationErr = wait.until(ExpectedConditions.presenceOfElementLocated(validationError));

        String expectedErrorMessage = "This is a required field";
        String actualErrorMessage = validationErr.getText();

        if (validationErr.isDisplayed() && actualErrorMessage.equals(expectedErrorMessage)) {
            System.out.println("validation error showed");
        } else {
            System.out.println("test failed");
        }
        driver.close();
        driver.quit();

    }
}
