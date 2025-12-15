import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
    By addEducationButton = By.xpath("//button[@type='button']");
    By degreeLevelDropdown = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Degree Level'])[1]/following::div[3]");
    By degreeOption1 = By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-1')]");
    By degreeNameField = By.name("degreeDisplayName");
    By countryDropdown = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Country'])[1]/following::div[2]");
    By schoolNameField = By.name("school");
    By fieldOfStudyDropdown = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Field of Study'])[1]/following::div[2]");
    By startYearDropdown = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Start Year'])[1]/following::div[2]");
    By endYearDropdown = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='End Year'])[1]/following::div[2]");
    By gradeDropdown = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Grade'])[1]/following::div[2]");
    By selectOption = By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-')]");
    By studiedSubjectsField = By.name("studiedSubjects");
    By saveButton = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancel'])[1]/following::button[1]");
    By validationError = By.xpath("//span[@class='css-10o27u4']");
    
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
        Thread.sleep(1500);
        
        // Step 3: go to profile
        wait.until(ExpectedConditions.elementToBeClickable(editProfileOption)).click();
        Thread.sleep(2000);
        
        // Step 4: click education
        wait.until(ExpectedConditions.elementToBeClickable(educationTab)).click();
        Thread.sleep(1500);
        
        // Step 5: click add education
        wait.until(ExpectedConditions.elementToBeClickable(addEducationButton)).click();
        Thread.sleep(2000);

        // Step 6: select degree level
        wait.until(ExpectedConditions.elementToBeClickable(degreeLevelDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(degreeOption1)).click();

        // Step 7: fill degree name
        WebElement degreeNameFld = wait.until(ExpectedConditions.elementToBeClickable(degreeNameField));
        degreeNameFld.click();
        degreeNameFld.sendKeys(Keys.CONTROL + "a", Keys.DELETE, "MS");

        // Step 8: select country
        wait.until(ExpectedConditions.elementToBeClickable(countryDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(selectOption)).click();

        // Step 9: fill school
        WebElement schoolField = wait.until(ExpectedConditions.presenceOfElementLocated(schoolNameField));
        driver.executeScript("arguments[0].scrollIntoView(true);", schoolField);
        schoolField.click();
        schoolField.sendKeys(Keys.CONTROL + "a", Keys.DELETE, "BUE");
        IO.println("filled school");
        Thread.sleep(500);

        // Step 10: scroll to field of study but dont fill
        WebElement fieldOfStudyFld = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[contains(text(), 'Field')]/parent::div/following-sibling::div//input[@type='text']")));
        driver.executeScript("arguments[0].scrollIntoView({block: 'center'});", fieldOfStudyFld);
        Thread.sleep(300);
        IO.println("field of study left empty");

        // Step 11: select start year
        WebElement startYearDrop = wait.until(ExpectedConditions.elementToBeClickable(startYearDropdown));
        driver.executeScript("arguments[0].scrollIntoView({block: 'center'});", startYearDrop);
        startYearDrop.click();
        WebElement startYearOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-3')]")));
        driver.executeScript("arguments[0].click();", startYearOption);
        IO.println("selected start year");

        // Step 12: select end year
        WebElement endYearDrop = wait.until(ExpectedConditions.elementToBeClickable(endYearDropdown));
        driver.executeScript("arguments[0].scrollIntoView({block: 'center'});", endYearDrop);
        endYearDrop.click();
        WebElement endYearOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-0')]")));
        driver.executeScript("arguments[0].click();", endYearOption);
        IO.println("selected end year");

        // Step 13: select grade
        WebElement gradeDrop = wait.until(ExpectedConditions.elementToBeClickable(gradeDropdown));
        driver.executeScript("arguments[0].scrollIntoView({block: 'center'});", gradeDrop);
        gradeDrop.click();
        WebElement gradeOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-0')]")));
        driver.executeScript("arguments[0].click();", gradeOption);
        IO.println("selected grade");
        Thread.sleep(500);

        // Step 14: click save
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        driver.executeScript("arguments[0].scrollIntoView({block: 'center'});", saveBtn);
        Thread.sleep(500);
        saveBtn.click();
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
