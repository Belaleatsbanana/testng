package StepDefinitions;

import utils.WuzzufLogin;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class T5_TCP_PF_01_Steps {
    
    // Locators
    private final By profileDropdown = By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div/div/div/div");
    private final By editProfileOption = By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div[2]/div/div[4]/a/span");
    private final By firstNameField = By.name("firstName");
    private final By middleNameField = By.name("middleName");
    private final By lastNameField = By.name("lastName");
    private final By birthDayInput = By.id("react-select-2-input");
    private final By birthDayOption = By.xpath("//div[contains(@id,'react-select-2-option')]");
    private final By birthMonthInput = By.id("react-select-3-input");
    private final By birthMonthOption = By.xpath("//div[contains(@id,'react-select-3-option')]");
    private final By birthYearInput = By.id("react-select-4-input");
    private final By birthYearOption = By.xpath("//div[contains(@id,'react-select-4-option')]");
    private final By genderOption = By.xpath("//div[@id='gender']/div/label[2]/div");
    private final By nationalityInput = By.id("react-select-5-input");
    private final By nationalityOption = By.xpath("//div[contains(@id,'react-select-5-option')]");
    private final By maritalStatusOption = By.xpath("//div[@id='marital-status']/div/label/div");
    private final By nameDropdownContainer = By.xpath("//div[@id='name']/div/div[7]/div");
    private final By militaryStatusContainer = By.xpath("//div[@id='military-status']/div");
    private final By drivingLicense1 = By.xpath("//div[@id='driving-license']/div/div/label/div");
    private final By drivingLicense2 = By.xpath("//div[@id='driving-license']/div[2]/div/label/div");
    private final By cityContainer = By.xpath("//div[@id='location']/div/div");
    private final By areaContainer = By.xpath("//div[@id='location']/div/div[2]");
    private final By primaryPhoneField = By.name("primaryPhone");
    private final By otherPhoneField = By.name("otherPhone");
    private final By saveButton = By.xpath("//div[@id='app']/div/div[2]/div[2]/form/button");
    private final By successModal = By.xpath("/html/body/div[1]/div/div[4]/div/div/div");

    @When("user clicks on edit profile")
    public void user_clicks_on_edit_profile() throws InterruptedException {
        TestContext.wait.until(ExpectedConditions.elementToBeClickable(profileDropdown)).click();
        System.out.println("Clicked profile dropdown");
        Thread.sleep(1500);

        TestContext.wait.until(ExpectedConditions.elementToBeClickable(editProfileOption)).click();
        System.out.println("Navigated to profile page");
        Thread.sleep(2000);
    }

    @And("user fills personal information")
    public void user_fills_personal_information() throws InterruptedException {
        WebElement firstName = TestContext.wait.until(ExpectedConditions.elementToBeClickable(firstNameField));
        firstName.click();
        Thread.sleep(200);
        firstName.sendKeys(Keys.CONTROL + "a");
        firstName.sendKeys(Keys.BACK_SPACE);
        firstName.sendKeys("dummy");
        System.out.println("Filled first name");
        Thread.sleep(500);

        WebElement middleName = TestContext.driver.findElement(middleNameField);
        middleName.click();
        Thread.sleep(200);
        middleName.sendKeys(Keys.CONTROL + "a");
        middleName.sendKeys(Keys.BACK_SPACE);
        middleName.sendKeys("shams");
        System.out.println("Filled middle name");
        Thread.sleep(500);

        WebElement lastName = TestContext.driver.findElement(lastNameField);
        lastName.click();
        Thread.sleep(200);
        lastName.sendKeys(Keys.CONTROL + "a");
        lastName.sendKeys(Keys.BACK_SPACE);
        lastName.sendKeys("bell");
        System.out.println("Filled last name");
        Thread.sleep(500);
    }

    @And("user selects birth date")
    public void user_selects_birth_date() throws InterruptedException {
        TestContext.driver.findElement(birthDayInput).click();
        Thread.sleep(1500);
        TestContext.wait.until(ExpectedConditions.elementToBeClickable(birthDayOption)).click();
        Thread.sleep(1000);

        TestContext.driver.findElement(birthMonthInput).click();
        Thread.sleep(1500);
        TestContext.wait.until(ExpectedConditions.elementToBeClickable(birthMonthOption)).click();
        Thread.sleep(1000);

        TestContext.driver.findElement(birthYearInput).click();
        Thread.sleep(1500);
        TestContext.wait.until(ExpectedConditions.elementToBeClickable(birthYearOption)).click();
        Thread.sleep(1000);
    }

    @And("user selects gender and nationality")
    public void user_selects_gender_and_nationality() throws InterruptedException {
        TestContext.driver.findElement(genderOption).click();
        Thread.sleep(800);

        TestContext.driver.findElement(nationalityInput).click();
        Thread.sleep(1500);
        TestContext.wait.until(ExpectedConditions.elementToBeClickable(nationalityOption)).click();
        Thread.sleep(1000);
    }

    @And("user selects marital status")
    public void user_selects_marital_status() throws InterruptedException {
        TestContext.driver.findElement(maritalStatusOption).click();
        Thread.sleep(800);
    }

    @And("user selects additional profile fields")
    public void user_selects_additional_profile_fields() throws InterruptedException {
        WebElement nameDropdown = TestContext.driver.findElement(nameDropdownContainer);
        WebElement nameInput = nameDropdown.findElement(By.xpath(".//input[contains(@id,'react-select-')]"));
        nameInput.click();
        Thread.sleep(1500);
        String nameInputId = nameInput.getAttribute("id").replace("-input", "");
        TestContext.wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@id,'" + nameInputId + "-option')]")
        )).click();
        Thread.sleep(1000);

        WebElement militaryStatus = TestContext.driver.findElement(militaryStatusContainer);
        WebElement militaryInput = militaryStatus.findElement(By.xpath(".//input[contains(@id,'react-select-')]"));
        militaryInput.click();
        Thread.sleep(1500);
        String militaryInputId = militaryInput.getAttribute("id").replace("-input", "");
        TestContext.wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@id,'" + militaryInputId + "-option')]")
        )).click();
        Thread.sleep(1000);
    }

    @And("user selects driving licenses")
    public void user_selects_driving_licenses() throws InterruptedException {
        TestContext.driver.findElement(drivingLicense1).click();
        Thread.sleep(800);
        TestContext.driver.findElement(drivingLicense2).click();
        Thread.sleep(800);
    }

    @And("user selects location")
    public void user_selects_location() throws InterruptedException {
        WebElement city = TestContext.driver.findElement(cityContainer);
        WebElement cityInput = city.findElement(By.xpath(".//input[contains(@id,'react-select-')]"));
        cityInput.click();
        Thread.sleep(1500);
        String cityInputId = cityInput.getAttribute("id").replace("-input", "");
        TestContext.wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@id,'" + cityInputId + "-option')]")
        )).click();
        Thread.sleep(1000);

        WebElement area = TestContext.driver.findElement(areaContainer);
        WebElement areaInput = area.findElement(By.xpath(".//input[contains(@id,'react-select-')]"));
        areaInput.click();
        Thread.sleep(1500);
        String areaInputId = areaInput.getAttribute("id").replace("-input", "");
        TestContext.wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@id,'" + areaInputId + "-option')]")
        )).click();
        Thread.sleep(1000);
    }

    @And("user fills contact information")
    public void user_fills_contact_information() throws InterruptedException {
        WebElement primaryPhone = TestContext.driver.findElement(primaryPhoneField);
        primaryPhone.click();
        Thread.sleep(200);
        primaryPhone.sendKeys(Keys.CONTROL + "a");
        primaryPhone.sendKeys(Keys.BACK_SPACE);
        primaryPhone.sendKeys("01116615352");
        System.out.println("Filled primary phone");
        Thread.sleep(500);

        WebElement otherPhone = TestContext.driver.findElement(otherPhoneField);
        otherPhone.click();
        Thread.sleep(200);
        otherPhone.sendKeys(Keys.CONTROL + "a");
        otherPhone.sendKeys(Keys.BACK_SPACE);
        otherPhone.sendKeys("01116615352");
        System.out.println("Filled other phone");
        Thread.sleep(500);
    }

    @And("clicks save profile button")
    public void clicks_save_profile_button() throws InterruptedException {
        TestContext.driver.findElement(saveButton).click();
        System.out.println("Clicked save button");
        Thread.sleep(2000);
    }

    @Then("success message is displayed")
    public void success_message_is_displayed() {
        WebElement success = TestContext.wait.until(ExpectedConditions.presenceOfElementLocated(successModal));

        if (success.isDisplayed()) {
            System.out.println("profile updated successfully");
        } else {
            System.out.println("test failed");
        }
    }
}
