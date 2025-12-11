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
                By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div/div/div/div")
        )).click();
        System.out.println("Clicked profile dropdown");
        Thread.sleep(1500);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div[2]/div/div[4]/a/span")
        )).click();
        System.out.println("Navigated to profile page");
        Thread.sleep(2000);

        // Step 3: Fill personal information
        WebElement firstNameField = wait.until(ExpectedConditions.elementToBeClickable(By.name("firstName")));
        firstNameField.click();
        Thread.sleep(200);
        firstNameField.sendKeys(Keys.CONTROL + "a");
        firstNameField.sendKeys(Keys.BACK_SPACE);
        firstNameField.sendKeys("dummy");
        System.out.println("Filled first name");
        Thread.sleep(500);

        WebElement middleNameField = driver.findElement(By.name("middleName"));
        middleNameField.click();
        Thread.sleep(200);
        middleNameField.sendKeys(Keys.CONTROL + "a");
        middleNameField.sendKeys(Keys.BACK_SPACE);
        middleNameField.sendKeys("shams");
        System.out.println("Filled middle name");
        Thread.sleep(500);

        WebElement lastNameField = driver.findElement(By.name("lastName"));
        lastNameField.click();
        Thread.sleep(200);
        lastNameField.sendKeys(Keys.CONTROL + "a");
        lastNameField.sendKeys(Keys.BACK_SPACE);
        lastNameField.sendKeys("bell");
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
        WebElement nameDropdownContainer = driver.findElement(By.xpath("//div[@id='name']/div/div[7]/div"));
        WebElement nameInput = nameDropdownContainer.findElement(By.xpath(".//input[contains(@id,'react-select-')]"));
        nameInput.click();
        Thread.sleep(1500);
        String nameInputId = nameInput.getAttribute("id").replace("-input", "");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@id,'" + nameInputId + "-option')]")
        )).click();
        Thread.sleep(1000);

        WebElement militaryStatusContainer = driver.findElement(By.xpath("//div[@id='military-status']/div"));
        WebElement militaryInput = militaryStatusContainer.findElement(By.xpath(".//input[contains(@id,'react-select-')]"));
        militaryInput.click();
        Thread.sleep(1500);
        String militaryInputId = militaryInput.getAttribute("id").replace("-input", "");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@id,'" + militaryInputId + "-option')]")
        )).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//div[@id='driving-license']/div/div/label/div")).click();
        Thread.sleep(800);
        driver.findElement(By.xpath("//div[@id='driving-license']/div[2]/div/label/div")).click();
        Thread.sleep(800);

        WebElement cityContainer = driver.findElement(By.xpath("//div[@id='location']/div/div"));
        WebElement cityInput = cityContainer.findElement(By.xpath(".//input[contains(@id,'react-select-')]"));
        cityInput.click();
        Thread.sleep(1500);
        String cityInputId = cityInput.getAttribute("id").replace("-input", "");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@id,'" + cityInputId + "-option')]")
        )).click();
        Thread.sleep(1000);

        WebElement areaContainer = driver.findElement(By.xpath("//div[@id='location']/div/div[2]"));
        WebElement areaInput = areaContainer.findElement(By.xpath(".//input[contains(@id,'react-select-')]"));
        areaInput.click();
        Thread.sleep(1500);
        String areaInputId = areaInput.getAttribute("id").replace("-input", "");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@id,'" + areaInputId + "-option')]")
        )).click();
        Thread.sleep(1000);

        // Step 7: Fill primary phone but leave other phone blank
        WebElement primaryPhoneField = driver.findElement(By.name("primaryPhone"));
        primaryPhoneField.click();
        Thread.sleep(200);
        primaryPhoneField.sendKeys(Keys.CONTROL + "a");
        primaryPhoneField.sendKeys(Keys.BACK_SPACE);
        primaryPhoneField.sendKeys("01116615352");
        System.out.println("Filled primary phone");
        Thread.sleep(500);

        WebElement otherPhoneField = driver.findElement(By.name("otherPhone"));
        otherPhoneField.click();
        Thread.sleep(200);
        otherPhoneField.sendKeys(Keys.CONTROL + "a");
        otherPhoneField.sendKeys(Keys.BACK_SPACE);
        System.out.println("Left other phone field blank");
        Thread.sleep(500);

        // Step 8: Click save
        driver.findElement(By.xpath("//div[@id='app']/div/div[2]/div[2]/form/button")).click();
        System.out.println("Clicked save button");
        Thread.sleep(2000);


    }

    @AfterMethod
    public void tearDown() {
        // Step 9: Verify validation error appears
        WebElement validationError = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@id='contact-info']/div[2]/div/span")
        ));

        String expectedErrorMessage = "This is a required field";
        String actualErrorMessage = validationError.getText();

        if (validationError.isDisplayed() && actualErrorMessage.equals(expectedErrorMessage)) {
            System.out.println("validation error showed");
        } else {
            System.out.println("Test failed");
        }
        driver.close();
        driver.quit();

    }
}
