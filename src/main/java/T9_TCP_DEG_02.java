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

    try {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1: login
        new WuzzufLogin(driver).login();
        Thread.sleep(2000);

        // Step 2: click profile
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div/div/div/div"))).click();
        Thread.sleep(1000);
        
        // Step 3: go to profile
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div[2]/div/div[4]/a/span"))).click();
        Thread.sleep(2000);
        
        // Step 4: click education
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Education"))).click();
        IO.println("clicked education");
        Thread.sleep(2000);

        // Step 5: click delete button
        String deleteButtonXpath = "/html/body/div[1]/div/div[2]/div[2]/div[2]/ul/li[2]/div[1]/div";
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(deleteButtonXpath)));
        driver.executeScript("arguments[0].scrollIntoView({block: 'center'});", deleteButton);
        Thread.sleep(500);
        deleteButton.click();
        IO.println("clicked delete");
        Thread.sleep(1000);

        // Step 6: confirm delete
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancel'])[1]/following::button[1]"))).click();
        IO.println("confirmed delete");
        Thread.sleep(2000);

        // Step 7: verify deleted
        List<WebElement> targetItem = driver.findElements(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[2]/ul/li[2]"));

        if (targetItem.isEmpty()) {
            IO.println("education deleted");
        } else {
            IO.println("test failed");
        }

    } catch (Exception e) {
        IO.println(e.getMessage());
    } finally {
        driver.close();
        driver.quit();
    }
}
