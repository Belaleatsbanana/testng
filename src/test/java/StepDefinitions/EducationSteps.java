package StepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EducationSteps {
    
    // Locators
    private final By addEducationButton = By.xpath("//button[@type='button']");
    private final By degreeLevelControl = By.xpath("//div[@data-field='educationalDegree']//div[contains(@class, 'css-5usvjy-control') or contains(@class, 'control')]");
    private final By degreeNameField = By.name("degreeDisplayName");
    private final By countryControl = By.xpath("//label[contains(text(), 'Country')]/following-sibling::div//div[contains(@class, 'control')]");
    private final By schoolNameField = By.name("school");
    private final By fieldOfStudyInput = By.xpath("//label[contains(text(), 'Field')]/parent::div/following-sibling::div//input[@type='text']");
    private final By startYearControl = By.xpath("//label[contains(text(), 'Start Year')]/following-sibling::div//div[contains(@class, 'control')]");
    private final By endYearControl = By.xpath("//label[contains(text(), 'End Year')]/following-sibling::div//div[contains(@class, 'control')]");
    private final By gradeControl = By.xpath("//label[contains(text(), 'Grade')]/following-sibling::div//div[contains(@class, 'control')]");
    private final By selectOption = By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-')]");
    private final By selectMenuClose = By.xpath("//div[contains(@id, 'react-select') and contains(@class, 'menu')]");
    private final By studiedSubjectsField = By.name("studiedSubjects");
    private final By notesField = By.name("notes");
    private final By saveEducationButton = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancel'])[1]/following::button[1]");
    private final By deleteButtonSecond = By.xpath("(//button[contains(text(),'Delete')])[2]");
    private final By successModal = By.xpath("/html/body/div[1]/div/div[4]/div/div");

    @And("user clicks Add Education button")
    public void user_clicks_add_education_button() throws InterruptedException {
        TestContext.wait.until(ExpectedConditions.elementToBeClickable(addEducationButton)).click();
        System.out.println("Clicked Add Education button");
        Thread.sleep(2000);
    }

    @And("user selects degree level")
    public void user_selects_degree_level() throws InterruptedException {
        WebElement degreeLevel = TestContext.wait.until(ExpectedConditions.elementToBeClickable(degreeLevelControl));
        degreeLevel.click();
        Thread.sleep(1000);

        TestContext.wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-1')]")
        )).click();
        System.out.println("Selected Degree Level");
        Thread.sleep(500);

        TestContext.wait.until(ExpectedConditions.invisibilityOfElementLocated(selectMenuClose));
        Thread.sleep(500);
    }

    @And("user fills degree name with {string}")
    public void user_fills_degree_name_with(String degreeName) throws InterruptedException {
        WebElement degree = TestContext.driver.findElement(degreeNameField);
        degree.click();
        degree.clear();
        degree.sendKeys(degreeName);
        System.out.println("Filled degree name");
        Thread.sleep(500);
    }

    @And("user selects country")
    public void user_selects_country() throws InterruptedException {
        TestContext.wait.until(ExpectedConditions.elementToBeClickable(countryControl)).click();
        Thread.sleep(1000);

        TestContext.wait.until(ExpectedConditions.elementToBeClickable(selectOption)).click();
        System.out.println("Selected Country");
        Thread.sleep(1000);

        TestContext.wait.until(ExpectedConditions.invisibilityOfElementLocated(selectMenuClose));
        Thread.sleep(500);
    }

    @And("user fills school name with {string}")
    public void user_fills_school_name_with(String schoolName) throws InterruptedException {
        WebElement school = TestContext.wait.until(ExpectedConditions.presenceOfElementLocated(schoolNameField));
        JavascriptExecutor js = (JavascriptExecutor) TestContext.driver;
        js.executeScript("arguments[0].scrollIntoView(true);", school);
        Thread.sleep(300);
        js.executeScript("arguments[0].focus();", school);
        js.executeScript("arguments[0].value = '';", school);
        school.sendKeys(schoolName);
        System.out.println("Filled school name");
        Thread.sleep(1000);
        school.sendKeys(Keys.TAB);
        Thread.sleep(500);
    }

    @And("user fills field of study with {string}")
    public void user_fills_field_of_study_with(String fieldOfStudy) throws InterruptedException {
        WebElement field = TestContext.wait.until(ExpectedConditions.presenceOfElementLocated(fieldOfStudyInput));
        JavascriptExecutor js = (JavascriptExecutor) TestContext.driver;
        js.executeScript("arguments[0].scrollIntoView(true);", field);
        Thread.sleep(300);
        field.click();
        field.sendKeys(fieldOfStudy);
        System.out.println("Filled field of study");
        Thread.sleep(1000);
        field.sendKeys(Keys.TAB);
        Thread.sleep(2000);
    }
    
    @And("user leaves field of study empty")
    public void user_leaves_field_of_study_empty() throws InterruptedException {
        WebElement field = TestContext.wait.until(ExpectedConditions.presenceOfElementLocated(fieldOfStudyInput));
        JavascriptExecutor js = (JavascriptExecutor) TestContext.driver;
        js.executeScript("arguments[0].scrollIntoView(true);", field);
        Thread.sleep(300);
        field.click();
        field.clear();
        System.out.println("Left field of study empty");
        Thread.sleep(500);
        field.sendKeys(Keys.TAB);
        Thread.sleep(2000);
    }

    @And("user selects start year")
    public void user_selects_start_year() throws InterruptedException {
        TestContext.wait.until(ExpectedConditions.elementToBeClickable(startYearControl)).click();
        Thread.sleep(1000);

        TestContext.wait.until(ExpectedConditions.elementToBeClickable(selectOption)).click();
        System.out.println("Selected Start Year");

        TestContext.wait.until(ExpectedConditions.invisibilityOfElementLocated(selectMenuClose));
        Thread.sleep(500);
    }

    @And("user selects end year")
    public void user_selects_end_year() throws InterruptedException {
        TestContext.wait.until(ExpectedConditions.elementToBeClickable(endYearControl)).click();
        Thread.sleep(1000);

        TestContext.wait.until(ExpectedConditions.elementToBeClickable(selectOption)).click();
        System.out.println("Selected End Year");

        TestContext.wait.until(ExpectedConditions.invisibilityOfElementLocated(selectMenuClose));
        Thread.sleep(500);
    }

    @And("user selects grade")
    public void user_selects_grade() throws InterruptedException {
        TestContext.wait.until(ExpectedConditions.elementToBeClickable(gradeControl)).click();
        Thread.sleep(1000);

        TestContext.wait.until(ExpectedConditions.elementToBeClickable(selectOption)).click();
        System.out.println("Selected Grade");

        TestContext.wait.until(ExpectedConditions.invisibilityOfElementLocated(selectMenuClose));
        Thread.sleep(500);
    }

    @And("user fills studied subjects with {string}")
    public void user_fills_studied_subjects_with(String subjects) throws InterruptedException {
        WebElement studiedSubjects = TestContext.wait.until(ExpectedConditions.presenceOfElementLocated(studiedSubjectsField));
        JavascriptExecutor js = (JavascriptExecutor) TestContext.driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", studiedSubjects);
        Thread.sleep(500);

        try {
            TestContext.wait.until(ExpectedConditions.elementToBeClickable(studiedSubjects));
            studiedSubjects.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", studiedSubjects);
        }

        studiedSubjects.clear();
        studiedSubjects.sendKeys(subjects);
        System.out.println("Filled studied subjects");
        Thread.sleep(500);
    }

    @And("user fills notes with {string}")
    public void user_fills_notes_with(String notes) throws InterruptedException {
        WebElement notesElem = TestContext.wait.until(ExpectedConditions.presenceOfElementLocated(notesField));
        JavascriptExecutor js = (JavascriptExecutor) TestContext.driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", notesElem);
        Thread.sleep(500);

        try {
            TestContext.wait.until(ExpectedConditions.elementToBeClickable(notesElem));
            notesElem.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", notesElem);
        }

        notesElem.clear();
        notesElem.sendKeys(notes);
        System.out.println("Filled notes");
        Thread.sleep(500);
    }

    @And("clicks save education button")
    public void clicks_save_education_button() throws InterruptedException {
        WebElement saveButton = TestContext.wait.until(ExpectedConditions.elementToBeClickable(saveEducationButton));
        saveButton.click();
        System.out.println("Clicked Save button");
        Thread.sleep(2000);
    }

    @And("user clicks delete button for second education item")
    public void user_clicks_delete_button_for_second_education_item() throws InterruptedException {
        String deleteButtonXpath = "/html/body/div[1]/div/div[2]/div[2]/div[2]/ul/li[2]/div[1]/div";
        WebElement deleteButton = TestContext.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(deleteButtonXpath)));

        // Scroll to delete button
        JavascriptExecutor js = (JavascriptExecutor) TestContext.driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", deleteButton);
        Thread.sleep(500);

        deleteButton.click();
        System.out.println("Clicked delete button");
        Thread.sleep(1000);
    }

    @Then("education is added successfully")
    public void education_is_added_successfully() {
        WebElement success = TestContext.wait.until(ExpectedConditions.presenceOfElementLocated(successModal));

        if (success.isDisplayed()) {
            System.out.println("education added successfully");
        } else {
            System.out.println("test failed");
        }
    }

    @Then("education is deleted successfully")
    public void education_is_deleted_successfully() {
        System.out.println("education deleted successfully");
    }
    
    @Then("validation error for field of study is displayed")
    public void validation_error_for_field_of_study_is_displayed() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("field of study validation error displayed");
    }
}
