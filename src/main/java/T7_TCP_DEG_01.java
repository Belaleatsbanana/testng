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
    By degreeLevelControl = By.xpath("//div[@data-field='educationalDegree']//div[contains(@class, 'css-5usvjy-control') or contains(@class, 'control')]");
    By degreeOption1 = By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-1')]");
    By degreeNameField = By.name("degreeDisplayName");
    By countryControl = By.xpath("//label[contains(text(), 'Country')]/following-sibling::div//div[contains(@class, 'control')]");
    By schoolNameField = By.name("school");
    By fieldOfStudyInput = By.xpath("//label[contains(text(), 'Field')]/parent::div/following-sibling::div//input[@type='text']");
    By startYearControl = By.xpath("//label[contains(text(), 'Start Year')]/following-sibling::div//div[contains(@class, 'control')]");
    By endYearControl = By.xpath("//label[contains(text(), 'End Year')]/following-sibling::div//div[contains(@class, 'control')]");
    By gradeControl = By.xpath("//label[contains(text(), 'Grade')]/following-sibling::div//div[contains(@class, 'control')]");
    By selectOption = By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-')]");
    By selectMenuClose = By.xpath("//div[contains(@id, 'react-select') and contains(@class, 'menu')]");
    By studiedSubjectsField = By.name("studiedSubjects");
    By notesField = By.name("notes");
    By saveButton = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancel'])[1]/following::button[1]");
    By successModal = By.xpath("/html/body/div[1]/div/div[4]/div/div");
    
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
        IO.println("clicked profile");
        Thread.sleep(1500);

        // Step 3: go to profile page
        wait.until(ExpectedConditions.elementToBeClickable(editProfileOption)).click();
        IO.println("went to profile");
        Thread.sleep(2000);

        // Step 4: click education tab
        wait.until(ExpectedConditions.elementToBeClickable(educationTab)).click();
        IO.println("clicked education");
        Thread.sleep(1500);

        // Step 5: click add education
        wait.until(ExpectedConditions.elementToBeClickable(addEducationButton)).click();
        IO.println("clicked add education");
        Thread.sleep(2000);

        // Step 6: select degree level
        WebElement degreeLevelCtrl = wait.until(ExpectedConditions.elementToBeClickable(degreeLevelControl));
        degreeLevelCtrl.click();
        IO.println("clicked degree level");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(degreeOption1)).click();
        IO.println("selected degree level");
        Thread.sleep(500);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(selectMenuClose));
        Thread.sleep(500);

        // Step 7: fill degree name
        WebElement degreeNameFld = wait.until(ExpectedConditions.presenceOfElementLocated(degreeNameField));
        driver.executeScript("arguments[0].scrollIntoView({block: 'center'});", degreeNameFld);
        Thread.sleep(300);
        driver.executeScript("arguments[0].focus();", degreeNameFld);
        driver.executeScript("arguments[0].value = '';", degreeNameFld);
        degreeNameFld.click();
        Thread.sleep(200);
        degreeNameFld.sendKeys("MS");
        IO.println("filled degree name");
        Thread.sleep(500);

        // Step 8: select country
        wait.until(ExpectedConditions.elementToBeClickable(countryControl)).click();
        IO.println("clicked country");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(selectOption)).click();
        IO.println("selected country");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(selectMenuClose));
        Thread.sleep(500);

        // Step 9: fill school
        WebElement schoolField = wait.until(ExpectedConditions.presenceOfElementLocated(schoolNameField));
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
        WebElement fieldOfStudyFld = wait.until(ExpectedConditions.presenceOfElementLocated(fieldOfStudyInput));
        driver.executeScript("arguments[0].scrollIntoView(true);", fieldOfStudyFld);
        Thread.sleep(300);
        fieldOfStudyFld.click();
        fieldOfStudyFld.sendKeys("BUE");
        IO.println("filled field of study");
        Thread.sleep(1000);
        fieldOfStudyFld.sendKeys(Keys.TAB);
        IO.println("moved to next field");
        Thread.sleep(2000);

        // Step 11: select start year
        wait.until(ExpectedConditions.elementToBeClickable(startYearControl)).click();
        IO.println("clicked start year");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(selectOption)).click();
        IO.println("selected start year");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(selectMenuClose));
        Thread.sleep(500);

        // Step 12: select end year
        wait.until(ExpectedConditions.elementToBeClickable(endYearControl)).click();
        IO.println("clicked end year");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(selectOption)).click();
        IO.println("selected end year");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(selectMenuClose));
        Thread.sleep(500);

        // Step 13: select grade
        wait.until(ExpectedConditions.elementToBeClickable(gradeControl)).click();
        IO.println("clicked grade");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(selectOption)).click();
        IO.println("selected grade");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(selectMenuClose));
        Thread.sleep(500);

        // Step 14: fill studied subjects
        WebElement studiedSubjectsFld = wait.until(ExpectedConditions.presenceOfElementLocated(studiedSubjectsField));
        driver.executeScript("arguments[0].scrollIntoView({block: 'center'});", studiedSubjectsFld);
        Thread.sleep(500);
        driver.executeScript("arguments[0].focus();", studiedSubjectsFld);
        driver.executeScript("arguments[0].value = '';", studiedSubjectsFld);
        Thread.sleep(200);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(studiedSubjectsFld));
            studiedSubjectsFld.click();
        } catch (Exception e) {
            driver.executeScript("arguments[0].click();", studiedSubjectsFld);
        }
        Thread.sleep(200);
        studiedSubjectsFld.sendKeys("SQA");
        IO.println("filled studied subjects");
        Thread.sleep(500);

        // Step 15: fill notes
        WebElement notesFld = wait.until(ExpectedConditions.presenceOfElementLocated(notesField));
        driver.executeScript("arguments[0].scrollIntoView({block: 'center'});", notesFld);
        Thread.sleep(500);
        driver.executeScript("arguments[0].focus();", notesFld);
        driver.executeScript("arguments[0].value = '';", notesFld);
        Thread.sleep(200);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(notesFld));
            notesFld.click();
        } catch (Exception e) {
            driver.executeScript("arguments[0].click();", notesFld);
        }
        Thread.sleep(200);
        notesFld.sendKeys("SQA");
        IO.println("filled notes");
        Thread.sleep(500);

        // Step 16: click save
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveBtn.click();
        IO.println("clicked save");
        Thread.sleep(2000);

        // Step 17: check success
        WebElement successModalElement = wait.until(ExpectedConditions.presenceOfElementLocated(successModal));

        if (successModalElement != null && successModalElement.isDisplayed()) {
            IO.println("education added");
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
