import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class T10_TCP_TRAIN_01_Test {
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    // Locators
    private final By profileDropdown = By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div/div/div/div");
    private final By editProfileOption = By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div[2]/div/div[4]/a/span");
    private final By educationTab = By.linkText("Education");
    private final By addTrainingButton = By.xpath("//div[@id='app']/div/div[2]/div[2]/div[5]/button");
    private final By titleField = By.name("title");
    private final By organizationField = By.name("organization");
    private final By startMonthDropdown = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Month'])[1]/following::div[4]");
    private final By startYearDropdown = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Year'])[1]/following::div[4]");
    private final By monthYearOption = By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-0')]");
    private final By descriptionField = By.name("description");
    private final By saveButton = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancel'])[1]/following::button[1]");
    private final By successModal = By.xpath("/html/body/div[1]/div/div[4]/div/div");

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
    public void testAddTrainingCertification() throws InterruptedException {
        // Step 1: Login
        new WuzzufLogin(driver).login();
        Thread.sleep(2000);

        // Step 2: Navigate to profile
        wait.until(ExpectedConditions.elementToBeClickable(profileDropdown)).click();
        System.out.println("Clicked profile dropdown");
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(editProfileOption)).click();
        System.out.println("Navigated to profile page");
        Thread.sleep(2000);

        // Step 3: Click Education tab
        wait.until(ExpectedConditions.elementToBeClickable(educationTab)).click();
        System.out.println("Clicked Education tab");
        Thread.sleep(1500);

        // Step 4: Click Add Training/Certification button
        WebElement addTrainingBtn = wait.until(ExpectedConditions.elementToBeClickable(addTrainingButton));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", addTrainingBtn);
        addTrainingBtn.click();
        System.out.println("Clicked Add Training button");
        Thread.sleep(2000);

        // Step 5: Fill Title
        wait.until(ExpectedConditions.elementToBeClickable(titleField)).sendKeys("SQA");
        System.out.println("Filled title");
        Thread.sleep(500);

        // Step 6: Fill Organization
        wait.until(ExpectedConditions.elementToBeClickable(organizationField)).sendKeys("BUE");
        System.out.println("Filled organization");
        Thread.sleep(500);

        // Step 7: Select Start Month
        wait.until(ExpectedConditions.elementToBeClickable(startMonthDropdown)).click();
        Thread.sleep(500);

        WebElement monthOption = wait.until(ExpectedConditions.presenceOfElementLocated(monthYearOption));
        js.executeScript("arguments[0].click();", monthOption);
        System.out.println("Selected Start Month");
        Thread.sleep(500);

        // Step 8: Select Start Year
        wait.until(ExpectedConditions.elementToBeClickable(startYearDropdown)).click();
        Thread.sleep(500);

        WebElement yearOption = wait.until(ExpectedConditions.presenceOfElementLocated(monthYearOption));
        js.executeScript("arguments[0].click();", yearOption);
        System.out.println("Selected Start Year");
        Thread.sleep(500);

        // Step 9: Fill Description
        wait.until(ExpectedConditions.elementToBeClickable(descriptionField)).sendKeys("SQA");
        System.out.println("Filled description");
        Thread.sleep(500);

        // Step 10: Click Save button
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        System.out.println("Clicked Save button");
        Thread.sleep(2000);


    }

    @AfterMethod
    public void tearDown() {
        WebElement successElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                successModal
        ));

        if (successElement.isDisplayed()) {
            System.out.println("training added");
        } else {
            System.out.println("test failed");
        }
        driver.close();
        driver.quit();

    }
}
