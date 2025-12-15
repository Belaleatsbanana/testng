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

public class T9_TCP_DEG_02_Test {
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    // Locators
    private final By profileDropdown = By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div/div/div/div");
    private final By editProfileOption = By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div[2]/div/div[4]/a/span");
    private final By educationTab = By.linkText("Education");
    private final By deleteButton = By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[2]/ul/li[2]/div[1]/div");
    private final By confirmDeleteButton = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancel'])[1]/following::button[1]");
    private final By deletedEducationItem = By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[2]/ul/li[2]");

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
    public void testDeleteEducation() throws InterruptedException {
        // Step 1: Login
        new WuzzufLogin(driver).login();
        Thread.sleep(2000);

        // Step 2: Navigate to profile
        wait.until(ExpectedConditions.elementToBeClickable(profileDropdown)).click();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(editProfileOption)).click();
        Thread.sleep(2000);

        // Step 3: Click Education tab
        wait.until(ExpectedConditions.elementToBeClickable(educationTab)).click();
        System.out.println("Clicked Education tab");
        Thread.sleep(2000);

        // Step 4: Click delete button for second education item
        WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(deleteButton));

        // Scroll to delete button
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", deleteBtn);
        Thread.sleep(500);

        deleteBtn.click();
        System.out.println("Clicked delete button");
        Thread.sleep(1000);

        // Step 5: Confirm deletion
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton)).click();
        System.out.println("Confirmed deletion");
        Thread.sleep(2000);


    }

    @AfterMethod
    public void tearDown() {
        List<WebElement> targetItem = driver.findElements(deletedEducationItem);

        if (targetItem.isEmpty()) {
            System.out.println("education deleted successfully");
        } else {
            System.out.println("test failed");
        }
        driver.close();
        driver.quit();

    }
}
