import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

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
        Thread.sleep(1500);
        
        // Step 3: go to profile
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div[2]/div/div[4]/a/span"))).click();
        Thread.sleep(2000);
        
        // Step 4: click education
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Education"))).click();
        Thread.sleep(1500);
        
        // Step 5: click add education
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button']"))).click();
        Thread.sleep(2000);

        // Step 6: select degree level
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Degree Level'])[1]/following::div[3]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-1')]"))).click();

        // Step 7: fill degree name
        WebElement degreeNameField = wait.until(ExpectedConditions.elementToBeClickable(By.name("degreeDisplayName")));
        degreeNameField.click();
        degreeNameField.sendKeys(Keys.CONTROL + "a", Keys.DELETE, "MS");

        // Step 8: select country
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Country'])[1]/following::div[3]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-0')]"))).click();

        // Step 9: fill school
        WebElement schoolField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("school")));
        driver.executeScript("arguments[0].scrollIntoView(true);", schoolField);
        schoolField.click();
        schoolField.sendKeys(Keys.CONTROL + "a", Keys.DELETE, "BUE");
        IO.println("filled school");
        Thread.sleep(500);

        // Step 10: scroll to field of study but dont fill
        WebElement fieldOfStudyInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[contains(text(), 'Field')]/parent::div/following-sibling::div//input[@type='text']")));
        driver.executeScript("arguments[0].scrollIntoView({block: 'center'});", fieldOfStudyInput);
        Thread.sleep(300);
        IO.println("field of study left empty");

        // Step 11: select start year
        WebElement startYearDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Start Year'])[1]/following::div[3]")));
        driver.executeScript("arguments[0].scrollIntoView({block: 'center'});", startYearDropdown);
        startYearDropdown.click();
        WebElement startYearOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-3')]")));
        driver.executeScript("arguments[0].click();", startYearOption);
        IO.println("selected start year");

        // Step 12: select end year
        WebElement endYearDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='End Year'])[1]/following::div[4]")));
        driver.executeScript("arguments[0].scrollIntoView({block: 'center'});", endYearDropdown);
        endYearDropdown.click();
        WebElement endYearOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-0')]")));
        driver.executeScript("arguments[0].click();", endYearOption);
        IO.println("selected end year");

        // Step 13: select grade
        WebElement gradeDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Grade'])[1]/following::div[3]")));
        driver.executeScript("arguments[0].scrollIntoView({block: 'center'});", gradeDropdown);
        gradeDropdown.click();
        WebElement gradeOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-0')]")));
        driver.executeScript("arguments[0].click();", gradeOption);
        IO.println("selected grade");
        Thread.sleep(500);

        // Step 14: click save
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancel'])[1]/following::button[1]")));
        driver.executeScript("arguments[0].scrollIntoView({block: 'center'});", saveButton);
        Thread.sleep(500);
        saveButton.click();
        IO.println("clicked save");
        Thread.sleep(2000);

        // Step 15: check error
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Please add at least 1 studies')]")));
        String actualErrorMessage = errorMessage.getText();

        if (actualErrorMessage.contains("Please add at least")) {
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
