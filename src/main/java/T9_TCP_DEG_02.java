import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

void main() throws InterruptedException {
    System.setProperty("webdriver.chrome.driver", "/home/fax/Downloads/chromedriver-linux64/chromedriver");

    ChromeOptions option = new ChromeOptions();
    option.addArguments("--remote-allow-origins=*");
    option.addArguments("--start-maximized");
    ChromeDriver driver = new ChromeDriver(option);
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    IO.println("Starting Delete Education Test...");

    try {
        WuzzufLogin loginHelper = new WuzzufLogin(driver);
        loginHelper.login();
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Go to Profile -> Education
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div/div/div/div"))).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div[2]/div/div[4]/a/span"))).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Education"))).click();
        IO.println("Clicked on Education tab");
        Thread.sleep(2000);

        // --- STEP 5: Click Delete Button ---
        String deleteButtonXpath = "/html/body/div[1]/div/div[2]/div[2]/div[2]/ul/li[2]/div[1]/div";

        try {
            WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(deleteButtonXpath)));

            // Scroll to it to ensure its not hidden
            driver.executeScript("arguments[0].scrollIntoView({block: 'center'});", deleteButton);
            Thread.sleep(500);

            // Click
            deleteButton.click();
            IO.println("Clicked delete button (X icon)");
        } catch (Exception e) {
            IO.println("Could not find the delete button at: " + deleteButtonXpath);
            throw e;
        }

        // --- STEP 6: Confirm Deletion ---
        Thread.sleep(1000);
        WebElement confirmDeleteButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancel'])[1]/following::button[1]")
                )
        );
        confirmDeleteButton.click();
        IO.println("Clicked Confirm Delete button");
        Thread.sleep(2000);

        // --- STEP 7: Verify Deletion ---
        List<WebElement> targetItem = driver.findElements(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[2]/ul/li[2]"));

        if (targetItem.isEmpty()) {
            IO.println("Test PASSED: The second education item was removed.");
        } else {
            IO.println("Test FAILED: The second education item was not removed.");
        }

    } catch (Exception e) {
        IO.println("Test FAILED with exception: " + e.getMessage());
    } finally {
        driver.close();
        driver.quit();
        IO.println("Browser session closed");
    }
}