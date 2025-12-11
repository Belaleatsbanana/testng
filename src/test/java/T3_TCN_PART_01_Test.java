import org.openqa.selenium.By;
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

public class T3_TCN_PART_01_Test {
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
    public void testRecaptchaValidation() throws InterruptedException {
        // Step 1: Navigate to Wuzzuf
        driver.get("https://wuzzuf.net");
        Thread.sleep(1000);

        // Step 2: Login
        new WuzzufLogin(driver).login();
        Thread.sleep(2000);

        // Step 3: Click on profile dropdown
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div/div/div/div")
        )).click();
        System.out.println("Clicked profile dropdown");
        Thread.sleep(1000);

        // Step 4: Click on Contact Us option
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div[2]/div/div[9]/a/div/span")
        )).click();
        System.out.println("Clicked contact us");
        Thread.sleep(2000);

        // Step 5: Fill in subject field
        WebElement subjectField = wait.until(ExpectedConditions.elementToBeClickable(By.id("subject")));
        subjectField.click();
        Thread.sleep(500);
        subjectField.clear();
        subjectField.sendKeys("Partnership Request");
        System.out.println("Filled subject field");
        Thread.sleep(1000);

        // Step 6: Fill in message field
        WebElement messageField = wait.until(ExpectedConditions.elementToBeClickable(By.id("message")));
        messageField.click();
        Thread.sleep(500);
        messageField.clear();
        messageField.sendKeys("Partnership message test");
        System.out.println("Filled message field");
        Thread.sleep(1000);

        // Step 7: Click send button without completing reCAPTCHA
        driver.findElement(By.id("popup_send")).click();
        System.out.println("Clicked send button");
        Thread.sleep(2000);


    }

    @AfterMethod
    public void tearDown() {
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Sending...'])[1]/following::p[1]")
        ));

        String expectedErrorMessage = "The Recaptcha field is required.";
        String actualErrorMessage = errorMessage.getText();

        if (actualErrorMessage.equals(expectedErrorMessage)) {
            System.out.println("captcha error displayed");
        } else {
            System.out.println("Test failed");
        }

        driver.close();
        driver.quit();

    }
}
