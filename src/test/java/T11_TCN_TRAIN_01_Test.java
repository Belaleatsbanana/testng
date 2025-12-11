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

public class T11_TCN_TRAIN_01_Test {
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
    public void testAddTrainingMissingOrganization() throws InterruptedException {
        // Step 1: Login
        new WuzzufLogin(driver).login();
        Thread.sleep(2000);

        // Step 2: Navigate to profile
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div/div/div/div")
        )).click();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div[2]/div/div[4]/a/span")
        )).click();
        System.out.println("Navigated to profile page");
        Thread.sleep(2000);

        // Step 3: Click Education tab
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Education"))).click();
        System.out.println("Clicked Education tab");
        Thread.sleep(1500);

        // Step 4: Click Add Training/Certification button
        WebElement addTrainingButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@id='app']/div/div[2]/div[2]/div[5]/button")
        ));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", addTrainingButton);
        addTrainingButton.click();
        System.out.println("Clicked Add Training button");
        Thread.sleep(2000);

        // Step 5: Fill Title only
        WebElement titleField = wait.until(ExpectedConditions.elementToBeClickable(By.name("title")));
        titleField.sendKeys("SQA");
        System.out.println("Filled title");
        Thread.sleep(500);

        // Step 6: Skip Organization field intentionally (negative test)
        System.out.println("Organization field left empty intentionally");

        // Step 7: Select Start Month
        WebElement monthDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Month'])[1]/following::div[3]")
        ));
        monthDropdown.click();
        Thread.sleep(500);

        WebElement monthOption = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-0')]")
        ));
        js.executeScript("arguments[0].click();", monthOption);
        System.out.println("Selected Start Month");
        Thread.sleep(500);

        // Step 8: Select Start Year
        WebElement yearDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Year'])[1]/following::div[4]")
        ));
        yearDropdown.click();
        Thread.sleep(500);

        WebElement yearOption = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-0')]")
        ));
        js.executeScript("arguments[0].click();", yearOption);
        System.out.println("Selected Start Year");
        Thread.sleep(500);

        // Step 9: Fill Description
        WebElement descriptionField = wait.until(ExpectedConditions.elementToBeClickable(By.name("description")));
        descriptionField.sendKeys("SQA");
        System.out.println("Filled description");
        Thread.sleep(500);

        // Step 10: Click Save button
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancel'])[1]/following::button[1]")
        ));
        saveButton.click();
        System.out.println("Clicked Save button without filling Organization");
        Thread.sleep(1500);


    }

    @AfterMethod
    public void tearDown() {
        String expectedErrorMessage = "This is a required field";

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), '" + expectedErrorMessage + "')]")
        ));

        String actualErrorMessage = errorMessage.getText().trim();

        if (actualErrorMessage.equals(expectedErrorMessage)) {
            System.out.println("validation error showed");
        } else {
            System.out.println("test failed");
        }
        driver.close();
        driver.quit();

    }
}
