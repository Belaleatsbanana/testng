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
import java.util.List;

public class T12_TCP_TRAIN_02_Test {
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
    public void testDeleteTrainingCertification() throws InterruptedException {
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
        Thread.sleep(2000);

        // Step 4: Click delete button for training/certification
        String deleteButtonXpath = "/html/body/div[1]/div/div[2]/div[2]/div[5]/ul/li/div[1]/div";

        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(deleteButtonXpath)));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", deleteButton);
        System.out.println("Scrolled to delete button");
        Thread.sleep(500);

        deleteButton.click();
        System.out.println("Clicked delete button");
        Thread.sleep(1000);

        // Step 5: Confirm deletion
        WebElement confirmDeleteButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancel'])[1]/following::button[1]")
        ));
        confirmDeleteButton.click();
        System.out.println("Confirmed deletion");
        Thread.sleep(2000);


    }

    @AfterMethod
    public void tearDown() {
        String assertionXpath = "//div[@id='app']/div/div[2]/div[2]/div[5]/ul/li";
        List<WebElement> remainingTrainings = driver.findElements(By.xpath(assertionXpath));

        if (remainingTrainings.isEmpty()) {
            System.out.println("training deleted");
        } else {
            System.out.println("test failed");
        }
        driver.close();
        driver.quit();

    }
}
