import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class T8_TCN_DEG_01_Test {
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    // Locators
    private final By profileDropdown = By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div/div/div/div");
    private final By editProfileOption = By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div[2]/div/div[4]/a/span");
    private final By educationTab = By.linkText("Education");
    private final By addEducationButton = By.xpath("//button[@type='button']");
    private final By degreeLevelDropdown = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Degree Level'])[1]/following::div[3]");
    private final By degreeOption1 = By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-1')]");
    private final By degreeNameField = By.name("degreeDisplayName");
    private final By countryDropdown = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Country'])[1]/following::div[3]");
    private final By schoolNameField = By.name("school");
    private final By fieldOfStudyInput = By.xpath("//label[contains(text(), 'Field')]/parent::div/following-sibling::div//input[@type='text']");
    private final By startYearDropdown = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Start Year'])[1]/following::div[3]");
    private final By endYearDropdown = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='End Year'])[1]/following::div[4]");
    private final By gradeDropdown = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Grade'])[1]/following::div[3]");
    private final By selectOption = By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-')]");
    private final By studiedSubjectsField = By.name("studiedSubjects");
    private final By saveButton = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancel'])[1]/following::button[1]");
    private final By validationError = By.xpath("//*[contains(text(), 'Please add at least 1 studies')]");

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
    public void testAddEducationMissingFieldOfStudy() throws InterruptedException {
        // Step 1: Login
        new WuzzufLogin(driver).login();
        Thread.sleep(2000);

        // Step 2: Navigate to profile
        wait.until(ExpectedConditions.elementToBeClickable(
                profileDropdown
        )).click();
        Thread.sleep(1500);

        wait.until(ExpectedConditions.elementToBeClickable(
                editProfileOption
        )).click();
        Thread.sleep(2000);

        // Step 3: Click Education tab
        wait.until(ExpectedConditions.elementToBeClickable(educationTab)).click();
        Thread.sleep(1500);

        // Step 4: Click Add Education button
        wait.until(ExpectedConditions.elementToBeClickable(addEducationButton)).click();
        System.out.println("Clicked Add Education button");
        Thread.sleep(2000);

        // Step 5: Select Degree Level
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Degree Level'])[1]/following::div[3]")
        )).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                degreeOption1
        )).click();

        // Step 6: Fill Degree Name
        WebElement degreeName = wait.until(ExpectedConditions.elementToBeClickable(degreeNameField));
        degreeName.click();
        degreeName.clear();
        degreeName.sendKeys("MS");
        System.out.println("Filled degree name");

        // Step 7: Select Country
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Country'])[1]/following::div[3]")
        )).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                selectOption
        )).click();

        // Step 8: Fill School
        WebElement schoolField = wait.until(ExpectedConditions.presenceOfElementLocated(schoolNameField));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", schoolField);
        schoolField.click();
        schoolField.clear();
        schoolField.sendKeys("BUE");
        System.out.println("Filled school name");
        Thread.sleep(500);

        // Step 9: Scroll to Field of Study but do NOT fill it (negative test)
        WebElement fieldOfStudy = wait.until(ExpectedConditions.presenceOfElementLocated(
                fieldOfStudyInput
        ));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", fieldOfStudy);
        Thread.sleep(300);
        System.out.println("Field of Study left empty intentionally");

        // Step 10: Select Start Year
        WebElement startYearDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Start Year'])[1]/following::div[3]")
        ));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", startYearDropdown);
        startYearDropdown.click();
        WebElement startYearOption = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-3')]")
        ));
        js.executeScript("arguments[0].click();", startYearOption);
        System.out.println("Selected Start Year");

        // Step 11: Select End Year
        WebElement endYearDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='End Year'])[1]/following::div[4]")
        ));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", endYearDropdown);
        endYearDropdown.click();
        WebElement endYearOption = wait.until(ExpectedConditions.presenceOfElementLocated(
                selectOption
        ));
        js.executeScript("arguments[0].click();", endYearOption);
        System.out.println("Selected End Year");

        // Step 12: Select Grade
        WebElement gradeDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Grade'])[1]/following::div[3]")
        ));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", gradeDropdown);
        gradeDropdown.click();
        WebElement gradeOption = wait.until(ExpectedConditions.presenceOfElementLocated(
                selectOption
        ));
        js.executeScript("arguments[0].click();", gradeOption);
        System.out.println("Selected Grade");
        Thread.sleep(500);

        // Step 13: Click Save button
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancel'])[1]/following::button[1]")
        ));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", saveButton);
        Thread.sleep(500);
        saveButton.click();
        System.out.println("Clicked Save button");
        Thread.sleep(2000);


    }

    @AfterMethod
    public void tearDown() {
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'Please add at least 1 studies')]")
        ));

        String actualErrorMessage = errorMessage.getText();

        if (actualErrorMessage.contains("Please add at least")) {
            System.out.println("validation error showed");
        } else {
            System.out.println("test failed");
        }
        driver.close();
        driver.quit();

    }
}
