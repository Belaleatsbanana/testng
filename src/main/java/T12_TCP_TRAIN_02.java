import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

void main() throws InterruptedException {
    // Locators
    By profileDropdown = By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div/div/div/div");
    By editProfileOption = By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div[2]/div/div[4]/a/span");
    By educationTab = By.linkText("Education");
    By deleteButton = By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[5]/ul/li/div[1]/div");
    By confirmDeleteButton = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancel'])[1]/following::button[1]");
    By remainingTrainingItems = By.xpath("//div[@id='app']/div/div[2]/div[2]/div[5]/ul/li");
    
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
        wait.until(ExpectedConditions.elementToBeClickable(profileDropdown)).click();
        Thread.sleep(1000);
        
        // Step 3: go to profile
        wait.until(ExpectedConditions.elementToBeClickable(editProfileOption)).click();
        IO.println("went to profile");
        Thread.sleep(2000);

        // Step 4: click education
        wait.until(ExpectedConditions.elementToBeClickable(educationTab)).click();
        IO.println("clicked education");
        Thread.sleep(2000);

        // Step 5: click delete
        WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
        driver.executeScript("arguments[0].scrollIntoView({block: 'center'});", deleteBtn);
        IO.println("scrolled to delete");
        Thread.sleep(500);
        deleteBtn.click();
        IO.println("clicked delete");
        Thread.sleep(1000);

        // Step 6: confirm delete
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton)).click();
        IO.println("confirmed delete");
        Thread.sleep(2000);

        // Step 7: verify training deleted
        List<WebElement> remainingTrainings = driver.findElements(remainingTrainingItems);

        if (remainingTrainings.isEmpty()) {
            IO.println("training deleted");
        } else {
            IO.println("test failed");
        }

    } catch (Exception e) {
        IO.println(e.getMessage());
        e.printStackTrace();
    } finally {
        driver.close();
        driver.quit();
    }
}
