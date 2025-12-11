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

    IO.println("Starting Delete Training/Certification Test...");

    try {
        // Step 1: Login
        WuzzufLogin loginHelper = new WuzzufLogin(driver);
        loginHelper.login();
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 2 & 3: Navigate to Profile
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div/div/div/div"))).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div[2]/div/div[4]/a/span"))).click();
        IO.println("Navigated to profile page");
        Thread.sleep(2000);

        // Step 4: Click Education Tab
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Education"))).click();
        IO.println("Clicked on Education tab");
        Thread.sleep(2000);

        // --- Step 5: Locate, Scroll, and Click Delete Icon for training ---
        String deleteButtonXpath = "/html/body/div[1]/div/div[2]/div[2]/div[5]/ul/li/div[1]/div";

        try {
            WebElement deleteButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(deleteButtonXpath))
            );

            driver.executeScript("arguments[0].scrollIntoView({block: 'center'});", deleteButton);
            IO.println("Scrolled to delete button.");
            Thread.sleep(500);

            deleteButton.click();
            IO.println("Clicked delete icon using absolute XPath.");
        } catch (Exception e) {
            IO.println("Could not find the training delete button at the specified XPath. Element not clickable or present.");
            throw e;
        }
        Thread.sleep(1000);

        // --- Step 6: Click Confirm Delete button in the modal ---
        WebElement confirmDeleteButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancel'])[1]/following::button[1]")
                )
        );
        confirmDeleteButton.click();
        IO.println("Clicked Confirm Delete button");
        Thread.sleep(2000);

        // --- Step 7: Assert Element Not Present ---
        String assertionXpath = "//div[@id='app']/div/div[2]/div[2]/div[5]/ul/li";

        List<WebElement> remainingTrainings = driver.findElements(By.xpath(assertionXpath));

        if (remainingTrainings.isEmpty()) {
            IO.println("Test PASSED: Training entry was successfully deleted and no training items remain");
        } else {
            IO.println("Test FAILED:training items remain");
        }

    } catch (Exception e) {
        IO.println("Test FAILED with exception: " + e.getMessage());
        e.printStackTrace();
    } finally {
        Thread.sleep(2000);
        driver.close();
        driver.quit();

        IO.println("Browser closed");

    }
}