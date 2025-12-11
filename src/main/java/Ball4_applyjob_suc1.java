import org.openqa.selenium.By;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ball4_applyjob_suc1 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/home/fax/Downloads/chromedriver-linux64/chromedriver");

        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(option);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WuzzufLogin login = new WuzzufLogin(driver);
        login.login();

        // Implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Launch the Wuzzuf Website
        driver.get("https://wuzzuf.net/explore");
        Thread.sleep(20000);

        driver.findElement(By.xpath("//*[@id='app']/div/div[2]/div[2]/div[1]/div/div[7]/div[1]/div[1]/div/div/div/h3/a")).click();
        System.out.println("clicked on job listing");
        Thread.sleep(8000);

        // Switch tabs
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            driver.switchTo().window(handle);
            if (driver.getTitle().contains("Senior Application Support job at Universities of Canada in Egypt in New Cairo, Cairo – Apply on Wuzzuf")) {
                break;
            }
        }

        driver.findElement(By.xpath("//*[@id='app']/div/main/article/section[1]/div/div[4]/button")).click();
        System.out.println("clicked on apply");

        // Wait for and interact with first textarea field
        WebElement firstField = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='app']/div/div[2]/div/div[2]/div/form/div[3]/textarea")));
        firstField.sendKeys("IDK");
        System.out.println("first field");

        // Scroll to make second field visible
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");

        // Try alternative waits for second field
        try {
            // Wait for presence first, then visibility
            WebElement secondField = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//*[@id='app']/div/div[2]/div/div[2]/div/form/div[7]/textarea")));

            // Scroll to element
            js.executeScript("arguments[0].scrollIntoView(true);", secondField);
            Thread.sleep(500);

            // Wait for it to be clickable
            wait.until(ExpectedConditions.elementToBeClickable(secondField));
            secondField.sendKeys("IDK");
            System.out.println("second field");

        } catch (Exception e) {
            System.out.println("Could not find second field with div[7], trying alternative approach...");

            // Alternative: Find all textareas in the form and use the second one
            WebElement form = driver.findElement(By.xpath("//*[@id='app']/div/div[2]/div/div[2]/div/form"));
            var textareas = form.findElements(By.tagName("textarea"));

            if (textareas.size() >= 2) {
                WebElement secondField = textareas.get(1);
                js.executeScript("arguments[0].scrollIntoView(true);", secondField);
                Thread.sleep(500);
                secondField.sendKeys("IDK");
                System.out.println("second field (found by index)");
            } else {
                System.out.println("Only found " + textareas.size() + " textarea(s)");
            }
        }

        // Scroll to and click submit button
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='app']/div/div[2]/div/div[3]/div/button[2]")));
        js.executeScript("arguments[0].scrollIntoView(true);", submitButton);
        Thread.sleep(500);
        submitButton.click();
        System.out.println("button clicked");

        // Wait and click confirmation if needed
        try {
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id='app']/div/div[2]/div/div[3]/div/button[2]"))).click();
            System.out.println("confirmation button clicked");
        } catch (Exception e) {
            System.out.println("No confirmation button needed");
        }

        if (driver.findElement(By.xpath("//*[@id='app']/div/main/section[1]/div/div/span")).isDisplayed()) {
            System.out.println("applied successfully");
        } else {
            System.out.println("Failed!");
        }
    }
}