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
        IO.println("clicked profile");
        Thread.sleep(1500);

        // Step 3: go to profile page
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div[2]/div/div[4]/a/span"))).click();
        IO.println("went to profile");
        Thread.sleep(2000);

        // Step 4: click education tab
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Education"))).click();
        IO.println("clicked education");
        Thread.sleep(1500);

        // Step 5: click add education
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button']"))).click();
        IO.println("clicked add education");
        Thread.sleep(2000);

        // Step 6: select degree level
        WebElement degreeLevelControl = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-field='educationalDegree']//div[contains(@class, 'css-5usvjy-control') or contains(@class, 'control')]")));
        degreeLevelControl.click();
        IO.println("clicked degree level");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-1')]"))).click();
        IO.println("selected degree level");
        Thread.sleep(500);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@id, 'react-select') and contains(@class, 'menu')]")));
        Thread.sleep(500);

        // Step 7: fill degree name
        WebElement degreeNameField = driver.findElement(By.name("degreeDisplayName"));
        degreeNameField.click();
        degreeNameField.sendKeys(Keys.CONTROL + "a", Keys.DELETE, "MS");
        IO.println("filled degree name");
        Thread.sleep(500);

        // Step 8: select country
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(), 'Country')]/following-sibling::div//div[contains(@class, 'control')]"))).click();
        IO.println("clicked country");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-')]"))).click();
        IO.println("selected country");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@id, 'react-select') and contains(@class, 'menu')]")));
        Thread.sleep(500);

        // Step 9: fill school
        WebElement schoolField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("school")));
        driver.executeScript("arguments[0].scrollIntoView(true);", schoolField);
        Thread.sleep(300);
        driver.executeScript("arguments[0].focus();", schoolField);
        driver.executeScript("arguments[0].value = '';", schoolField);
        schoolField.sendKeys("BUE");
        IO.println("filled school");
        Thread.sleep(1000);
        schoolField.sendKeys(Keys.TAB);
        IO.println("moved to next field");
        Thread.sleep(500);

        // Step 10: fill field of study
        WebElement fieldOfStudyInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[contains(text(), 'Field')]/parent::div/following-sibling::div//input[@type='text']")));
        driver.executeScript("arguments[0].scrollIntoView(true);", fieldOfStudyInput);
        Thread.sleep(300);
        fieldOfStudyInput.click();
        fieldOfStudyInput.sendKeys("BUE");
        IO.println("filled field of study");
        Thread.sleep(1000);
        fieldOfStudyInput.sendKeys(Keys.TAB);
        IO.println("moved to next field");
        Thread.sleep(2000);

        // Step 11: select start year
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(), 'Start Year')]/following-sibling::div//div[contains(@class, 'control')]"))).click();
        IO.println("clicked start year");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-')]"))).click();
        IO.println("selected start year");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@id, 'react-select') and contains(@class, 'menu')]")));
        Thread.sleep(500);

        // Step 12: select end year
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(), 'End Year')]/following-sibling::div//div[contains(@class, 'control')]"))).click();
        IO.println("clicked end year");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-')]"))).click();
        IO.println("selected end year");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@id, 'react-select') and contains(@class, 'menu')]")));
        Thread.sleep(500);

        // Step 13: select grade
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(), 'Grade')]/following-sibling::div//div[contains(@class, 'control')]"))).click();
        IO.println("clicked grade");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-')]"))).click();
        IO.println("selected grade");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@id, 'react-select') and contains(@class, 'menu')]")));
        Thread.sleep(500);

        // Step 14: fill studied subjects
        WebElement studiedSubjectsField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("studiedSubjects")));
        driver.executeScript("arguments[0].scrollIntoView({block: 'center'});", studiedSubjectsField);
        Thread.sleep(500);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(studiedSubjectsField));
            studiedSubjectsField.click();
        } catch (Exception e) {
            driver.executeScript("arguments[0].click();", studiedSubjectsField);
        }
        studiedSubjectsField.sendKeys(Keys.CONTROL + "a", Keys.DELETE, "SQA");
        IO.println("filled studied subjects");
        Thread.sleep(500);

        // Step 15: fill notes
        WebElement notesField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("notes")));
        driver.executeScript("arguments[0].scrollIntoView({block: 'center'});", notesField);
        Thread.sleep(500);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(notesField));
            notesField.click();
        } catch (Exception e) {
            driver.executeScript("arguments[0].click();", notesField);
        }
        notesField.sendKeys(Keys.CONTROL + "a", Keys.DELETE, "SQA");
        IO.println("filled notes");
        Thread.sleep(500);

        // Step 16: click save
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancel'])[1]/following::button[1]")));
        saveButton.click();
        IO.println("clicked save");
        Thread.sleep(2000);

        // Step 17: check success
        WebElement successModal = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div[5]/div/div")));

        if (successModal != null && successModal.isDisplayed()) {
            IO.println("education added successfully");
        } else {
            IO.println("Test failed");
        }

    } catch (Exception e) {
        IO.println(e.getMessage());
        e.printStackTrace();
    } finally {
        driver.close();
        driver.quit();
    }
}
