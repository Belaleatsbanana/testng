package StepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TrainingSteps {
    
    // Locators
    private final By addTrainingButton = By.xpath("//div[@id='app']/div/div[2]/div[2]/div[5]/button");
    private final By trainingTitleField = By.name("title");
    private final By trainingOrganizationField = By.name("organization");
    private final By trainingStartMonthDropdown = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Month'])[1]/following::div[4]");
    private final By trainingStartYearDropdown = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Year'])[1]/following::div[4]");
    private final By monthYearOption = By.xpath("//div[contains(@id, 'react-select') and contains(@id, 'option-0')]");
    private final By trainingDescriptionField = By.name("description");
    private final By saveTrainingButton = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancel'])[1]/following::button[1]");
    private final By deleteButtonTraining = By.xpath("//button[contains(text(),'Delete')]");
    private final By organizationError = By.xpath("//span[contains(text(),'organization') or contains(text(),'Organization') or contains(text(),'required')]");
    private final By successMessage = By.xpath("//*[contains(text(),'success') or contains(text(),'Success') or contains(text(),'added')]");

    @And("user clicks Add Training button")
    public void user_clicks_add_training_button() throws InterruptedException {
        WebElement addButton = TestContext.wait.until(ExpectedConditions.elementToBeClickable(addTrainingButton));
        JavascriptExecutor js = (JavascriptExecutor) TestContext.driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", addButton);
        addButton.click();
        System.out.println("Clicked Add Training button");
        Thread.sleep(2000);
    }

    @And("user fills training title with {string}")
    public void user_fills_training_title_with(String title) throws InterruptedException {
        TestContext.wait.until(ExpectedConditions.elementToBeClickable(trainingTitleField)).sendKeys(title);
        System.out.println("Filled training title: " + title);
        Thread.sleep(500);
    }

    @And("user fills training organization with {string}")
    public void user_fills_training_organization_with(String organization) throws InterruptedException {
        TestContext.wait.until(ExpectedConditions.elementToBeClickable(trainingOrganizationField)).sendKeys(organization);
        System.out.println("Filled training organization: " + organization);
        Thread.sleep(500);
    }
    
    @And("user leaves organization empty")
    public void user_leaves_organization_empty() throws InterruptedException {
        WebElement orgField = TestContext.wait.until(ExpectedConditions.elementToBeClickable(trainingOrganizationField));
        orgField.click();
        Thread.sleep(200);
        orgField.clear();
        System.out.println("Left organization empty");
        Thread.sleep(500);
    }

    @And("user selects training start month")
    public void user_selects_training_start_month() throws InterruptedException {
        TestContext.wait.until(ExpectedConditions.elementToBeClickable(trainingStartMonthDropdown)).click();
        Thread.sleep(500);
        
        WebElement monthOption = TestContext.wait.until(ExpectedConditions.presenceOfElementLocated(monthYearOption));
        JavascriptExecutor js = (JavascriptExecutor) TestContext.driver;
        js.executeScript("arguments[0].click();", monthOption);
        System.out.println("Selected training start month");
        Thread.sleep(500);
    }

    @And("user selects training start year")
    public void user_selects_training_start_year() throws InterruptedException {
        TestContext.wait.until(ExpectedConditions.elementToBeClickable(trainingStartYearDropdown)).click();
        Thread.sleep(500);
        
        WebElement yearOption = TestContext.wait.until(ExpectedConditions.presenceOfElementLocated(monthYearOption));
        JavascriptExecutor js = (JavascriptExecutor) TestContext.driver;
        js.executeScript("arguments[0].click();", yearOption);
        System.out.println("Selected training start year");
        Thread.sleep(500);
    }

    @And("user fills training description with {string}")
    public void user_fills_training_description_with(String description) throws InterruptedException {
        TestContext.wait.until(ExpectedConditions.elementToBeClickable(trainingDescriptionField)).sendKeys(description);
        System.out.println("Filled training description: " + description);
        Thread.sleep(500);
    }

    @And("clicks save training button")
    public void clicks_save_training_button() throws InterruptedException {
        TestContext.wait.until(ExpectedConditions.elementToBeClickable(saveTrainingButton)).click();
        System.out.println("Clicked save training button");
        Thread.sleep(2000);
    }

    @And("user clicks delete button for training item")
    public void user_clicks_delete_button_for_training_item() throws InterruptedException {
        String deleteButtonXpath = "/html/body/div[1]/div/div[2]/div[2]/div[5]/ul/li/div[1]/div";
        
        WebElement deleteButton = TestContext.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(deleteButtonXpath)));
        
        JavascriptExecutor js = (JavascriptExecutor) TestContext.driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", deleteButton);
        System.out.println("Scrolled to delete button");
        Thread.sleep(500);
        
        deleteButton.click();
        System.out.println("Clicked delete button");
        Thread.sleep(1000);
    }

    @Then("training is added successfully")
    public void training_is_added_successfully() throws InterruptedException {
        Thread.sleep(2000);
        
        By successModal = By.xpath("/html/body/div[1]/div/div[4]/div/div");
        WebElement successElement = TestContext.wait.until(ExpectedConditions.visibilityOfElementLocated(successModal));

        if (successElement.isDisplayed()) {
            System.out.println("training added");
        } else {
            System.out.println("test failed");
        }
    }

    @Then("training is deleted successfully")
    public void training_is_deleted_successfully() throws InterruptedException {
        Thread.sleep(2000);
        
        By successModal = By.xpath("/html/body/div[1]/div/div[4]/div/div");
        WebElement successElement = TestContext.wait.until(ExpectedConditions.visibilityOfElementLocated(successModal));

        if (successElement.isDisplayed()) {
            System.out.println("training deleted");
        } else {
            System.out.println("test failed");
        }
    }
    
    @Then("validation error for organization is displayed")
    public void validation_error_for_organization_is_displayed() throws InterruptedException {
        Thread.sleep(1000);
        WebElement errorMessage = TestContext.wait.until(ExpectedConditions.visibilityOfElementLocated(organizationError));

        if (errorMessage.isDisplayed()) {
            System.out.println("organization validation error displayed");
        } else {
            System.out.println("test failed");
        }
    }
}
