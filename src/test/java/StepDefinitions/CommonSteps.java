package StepDefinitions;

import utils.WuzzufLogin;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CommonSteps {
    
    // Locators
    private final By profileDropdown = By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div/div/div/div");
    private final By editProfileOption = By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div[2]/div/div[4]/a/span");
    
    @Given("user is logged in to wuzzuf")
    public void user_is_logged_in_to_wuzzuf() throws InterruptedException {
        TestContext.driver.get("https://wuzzuf.net");
        Thread.sleep(1000);
        new WuzzufLogin(TestContext.driver).login();
        Thread.sleep(2000);
    }
    
    @Given("user is logged in to Wuzzuf")
    public void user_is_logged_in_to_wuzzuf_capital() throws InterruptedException {
        TestContext.driver.get("https://wuzzuf.net");
        Thread.sleep(1000);
        new WuzzufLogin(TestContext.driver).login();
        Thread.sleep(2000);
    }
    
    @When("user navigates to profile page")
    public void user_navigates_to_profile_page() throws InterruptedException {
        TestContext.wait.until(ExpectedConditions.elementToBeClickable(profileDropdown)).click();
        System.out.println("Clicked profile dropdown");
        Thread.sleep(1500);

        TestContext.wait.until(ExpectedConditions.elementToBeClickable(editProfileOption)).click();
        System.out.println("Navigated to profile page");
        Thread.sleep(2000);
    }
}
