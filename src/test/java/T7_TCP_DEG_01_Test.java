import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import java.time.Duration;

public class T7_TCP_DEG_01_Test {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        System.out.println("Setting up test...");
        System.setProperty("webdriver.chrome.driver", "/home/fax/Downloads/chromedriver-linux64/chromedriver");
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        option.addArguments("--start-maximized");
        driver = new ChromeDriver(option);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testAddEducation() throws InterruptedException {
        // Step 1: Login
        new WuzzufLogin(driver).login();
        Thread.sleep(2000);

        // Step 2: Navigate to profile
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div/div/div/div")
        )).click();
        Thread.sleep(1500);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div[2]/div/div[4]/a/span")
        )).click();
        System.out.println("Navigated to profile page");
        Thread.sleep(2000);

        // Step 3: Click Education tab
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Education"))).click();
        System.out.println("Clicked Education tab");
        Thread.sleep(1500);

        // Step 4: Click Add Education button
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button']"))).click();
        System.out.println("Clicked Add Education button");
        Thread.sleep(2000);

        // Step 5: Select Degree Level
        WebElement degreeLevelControl = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@data-field='educationalDegree']//div[contains(@class, 'css-5usvjy-control') or contains(@class, 'control')]")
        ));
        degreeLevelControl.click();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-1')]")
        )).click();
        System.out.println("Selected Degree Level");
        Thread.sleep(500);

        // Wait for dropdown to close
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[contains(@id, 'react-select') and contains(@class, 'menu')]")
        ));
        Thread.sleep(500);

        // Step 6: Fill Degree Display Name
        WebElement degreeNameField = driver.findElement(By.name("degreeDisplayName"));
        degreeNameField.click();
        degreeNameField.clear();
        degreeNameField.sendKeys("MS");
        System.out.println("Filled degree name");
        Thread.sleep(500);

        // Step 7: Select Country
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[contains(text(), 'Country')]/following-sibling::div//div[contains(@class, 'control')]")
        )).click();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-')]")
        )).click();
        System.out.println("Selected Country");
        Thread.sleep(1000);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[contains(@id, 'react-select') and contains(@class, 'menu')]")
        ));
        Thread.sleep(500);

        // Step 8: Fill School/University name
        WebElement schoolField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("school")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", schoolField);
        Thread.sleep(300);
        js.executeScript("arguments[0].focus();", schoolField);
        js.executeScript("arguments[0].value = '';", schoolField);
        schoolField.sendKeys("BUE");
        System.out.println("Filled school name");
        Thread.sleep(1000);

        schoolField.sendKeys(Keys.TAB);
        Thread.sleep(500);

        // Step 9: Fill Field of Study
        WebElement fieldOfStudyInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//label[contains(text(), 'Field')]/parent::div/following-sibling::div//input[@type='text']")
        ));
        js.executeScript("arguments[0].scrollIntoView(true);", fieldOfStudyInput);
        Thread.sleep(300);
        fieldOfStudyInput.click();
        fieldOfStudyInput.sendKeys("BUE");
        System.out.println("Filled field of study");
        Thread.sleep(1000);

        fieldOfStudyInput.sendKeys(Keys.TAB);
        Thread.sleep(2000);

        // Step 10: Select Start Year
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[contains(text(), 'Start Year')]/following-sibling::div//div[contains(@class, 'control')]")
        )).click();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-')]")
        )).click();
        System.out.println("Selected Start Year");

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[contains(@id, 'react-select') and contains(@class, 'menu')]")
        ));
        Thread.sleep(500);

        // Step 11: Select End Year
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[contains(text(), 'End Year')]/following-sibling::div//div[contains(@class, 'control')]")
        )).click();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-')]")
        )).click();
        System.out.println("Selected End Year");

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[contains(@id, 'react-select') and contains(@class, 'menu')]")
        ));
        Thread.sleep(500);

        // Step 12: Select Grade
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[contains(text(), 'Grade')]/following-sibling::div//div[contains(@class, 'control')]")
        )).click();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-')]")
        )).click();
        System.out.println("Selected Grade");

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[contains(@id, 'react-select') and contains(@class, 'menu')]")
        ));
        Thread.sleep(500);

        // Step 13: Fill Studied Subjects
        WebElement studiedSubjectsField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("studiedSubjects")));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", studiedSubjectsField);
        Thread.sleep(500);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(studiedSubjectsField));
            studiedSubjectsField.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", studiedSubjectsField);
        }

        studiedSubjectsField.clear();
        studiedSubjectsField.sendKeys("SQA");
        System.out.println("Filled studied subjects");
        Thread.sleep(500);

        // Step 14: Fill Notes
        WebElement notesField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("notes")));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", notesField);
        Thread.sleep(500);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(notesField));
            notesField.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", notesField);
        }

        notesField.clear();
        notesField.sendKeys("SQA");
        System.out.println("Filled notes");
        Thread.sleep(500);

        // Step 15: Click Save button
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancel'])[1]/following::button[1]")
        ));
        saveButton.click();
        System.out.println("Clicked Save button");
        Thread.sleep(2000);


    }

    @AfterMethod
    public void tearDown() {
        WebElement successModal = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("/html/body/div[1]/div/div[4]/div/div")
        ));

        if (successModal.isDisplayed()) {
            System.out.println("education added successfully");
        } else {
            System.out.println("test failed");
        }
        driver.close();
        driver.quit();

    }
}
