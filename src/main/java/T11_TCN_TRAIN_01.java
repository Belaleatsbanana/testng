import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

void main() throws InterruptedException {
    // Locators
    By profileDropdown = By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div/div/div/div");
    By editProfileOption = By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div[2]/div/div[4]/a/span");
    By educationTab = By.linkText("Education");
    By addTrainingButton = By.xpath("//div[@id='app']/div/div[2]/div[2]/div[5]/button");
    By titleField = By.name("title");
    By startMonthDropdown = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Month'])[1]/following::div[3]");
    By startYearDropdown = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Year'])[1]/following::div[4]");
    By monthYearOption = By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-0')]");
    By descriptionField = By.name("description");
    By saveButton = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancel'])[1]/following::button[1]");
    
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

        // Step 2: click on profile
        wait.until(ExpectedConditions.elementToBeClickable(profileDropdown)).click();
        Thread.sleep(1000);
        
        // Step 3: go to profile page
        wait.until(ExpectedConditions.elementToBeClickable(editProfileOption)).click();
        IO.println("went to profile");
        Thread.sleep(2000);

        // Step 4: click education tab
        wait.until(ExpectedConditions.elementToBeClickable(educationTab)).click();
        IO.println("clicked education");
        Thread.sleep(1500);

        // Step 5: click add training button
        WebElement addTrainingBtn = wait.until(ExpectedConditions.elementToBeClickable(addTrainingButton));
        driver.executeScript("arguments[0].scrollIntoView({block: 'center'});", addTrainingBtn);
        addTrainingBtn.click();
        IO.println("clicked add training");
        Thread.sleep(2000);

        // Step 6: fill title
        wait.until(ExpectedConditions.elementToBeClickable(titleField)).sendKeys("SQA");
        IO.println("filled title");
        Thread.sleep(500);

        // Step 7: select start month
        wait.until(ExpectedConditions.elementToBeClickable(startMonthDropdown)).click();
        Thread.sleep(500);
        WebElement monthOption = wait.until(ExpectedConditions.presenceOfElementLocated(monthYearOption));
        driver.executeScript("arguments[0].click();", monthOption);
        IO.println("selected month");
        Thread.sleep(500);

        // Step 8: select start year
        wait.until(ExpectedConditions.elementToBeClickable(startYearDropdown)).click();
        Thread.sleep(500);
        WebElement yearOption = wait.until(ExpectedConditions.presenceOfElementLocated(monthYearOption));
        driver.executeScript("arguments[0].click();", yearOption);
        IO.println("selected year");
        Thread.sleep(500);

        // Step 9: fill description
        wait.until(ExpectedConditions.elementToBeClickable(descriptionField)).sendKeys("SQA");
        IO.println("filled description");
        Thread.sleep(500);

        // Step 10: click save
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        IO.println("clicked save");
        Thread.sleep(1500);

        // Step 11: check for error
        String expectedErrorMessage = "This is a required field";
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), '" + expectedErrorMessage + "')]")));
        String actualErrorMessage = errorMessage.getText().trim();

        if (actualErrorMessage.equals(expectedErrorMessage)) {
            IO.println("validation error showed");
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
