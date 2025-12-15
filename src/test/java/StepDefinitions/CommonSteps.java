package StepDefinitions;

import utils.WuzzufLogin;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CommonSteps {
    
    // Locators
    private final By profileDropdown = By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div/div/div/div");
    private final By editProfileOption = By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div[2]/div/div[4]/a/span");
    private final By educationTab = By.linkText("Education");
    private final By deleteConfirmButton = By.xpath("//button[contains(text(),'Delete') or contains(text(),'Confirm')]");
    
    @Given("user is logged in to wuzzuf")
    public void user_is_logged_in_to_wuzzuf() throws InterruptedException {
        TestContext.driver.get("https://wuzzuf.net");
        Thread.sleep(1000);
        new WuzzufLogin(TestContext.driver).login();
        Thread.sleep(2000);
    }
    
    @When("user clicks on edit profile")
    public void user_clicks_on_edit_profile() throws InterruptedException {
        TestContext.wait.until(ExpectedConditions.elementToBeClickable(profileDropdown)).click();
        System.out.println("Clicked profile dropdown");
        Thread.sleep(1500);

        TestContext.wait.until(ExpectedConditions.elementToBeClickable(editProfileOption)).click();
        System.out.println("Navigated to profile page");
        Thread.sleep(2000);
    }
    
    @And("user clicks on Education tab")
    public void user_clicks_on_education_tab() throws InterruptedException {
        TestContext.wait.until(ExpectedConditions.elementToBeClickable(educationTab)).click();
        System.out.println("Clicked Education tab");
        Thread.sleep(1500);
    }
    
    @And("user confirms deletion")
    public void user_confirms_deletion() throws InterruptedException {
        TestContext.wait.until(ExpectedConditions.elementToBeClickable(deleteConfirmButton)).click();
        System.out.println("Confirmed deletion");
        Thread.sleep(2000);
    }
}
