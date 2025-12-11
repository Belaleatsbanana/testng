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

        // Step 4: fill first name
        WebElement firstNameField = wait.until(ExpectedConditions.elementToBeClickable(By.name("firstName")));
        firstNameField.click();
        firstNameField.sendKeys(Keys.CONTROL + "a");
        firstNameField.sendKeys(Keys.DELETE);
        firstNameField.sendKeys("dummy");
        IO.println("filled first name");
        Thread.sleep(500);

        // Step 5: fill middle name
        WebElement middleNameField = driver.findElement(By.name("middleName"));
        middleNameField.click();
        middleNameField.sendKeys(Keys.CONTROL + "a", Keys.DELETE, "shams");
        IO.println("filled middle name");
        Thread.sleep(500);

        // Step 6: fill last name
        WebElement lastNameField = driver.findElement(By.name("lastName"));
        lastNameField.click();
        lastNameField.sendKeys(Keys.CONTROL + "a", Keys.DELETE, "bell");
        IO.println("filled last name");
        Thread.sleep(500);

        // Step 7: select birth day
        driver.findElement(By.id("react-select-2-input")).click();
        IO.println("clicked birth day");
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'react-select-2-option')]"))).click();
        IO.println("selected birth day");
        Thread.sleep(1000);

        // Step 8: select birth month
        driver.findElement(By.id("react-select-3-input")).click();
        IO.println("clicked birth month");
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'react-select-3-option')]"))).click();
        IO.println("selected birth month");
        Thread.sleep(1000);

        // Step 9: select birth year
        driver.findElement(By.id("react-select-4-input")).click();
        IO.println("clicked birth year");
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'react-select-4-option')]"))).click();
        IO.println("selected birth year");
        Thread.sleep(1000);

        // Step 10: select gender
        driver.findElement(By.xpath("//div[@id='gender']/div/label[2]/div")).click();
        IO.println("selected gender");
        Thread.sleep(800);

        // Step 11: select nationality
        driver.findElement(By.id("react-select-5-input")).click();
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'react-select-5-option')]"))).click();
        IO.println("selected nationality");
        Thread.sleep(1000);

        // Step 12: select marital status
        driver.findElement(By.xpath("//div[@id='marital-status']/div/label/div")).click();
        IO.println("selected marital status");
        Thread.sleep(800);

        // Step 13: select name dropdown
        WebElement nameDropdownContainer = driver.findElement(By.xpath("//div[@id='name']/div/div[7]/div"));
        WebElement nameInput = nameDropdownContainer.findElement(By.xpath(".//input[contains(@id,'react-select-')]"));
        nameInput.click();
        Thread.sleep(1500);
        String nameInputId = nameInput.getAttribute("id").replace("-input", "");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'" + nameInputId + "-option')]"))).click();
        IO.println("selected name option");
        Thread.sleep(1000);

        // Step 14: select military status
        WebElement militaryStatusContainer = driver.findElement(By.xpath("//div[@id='military-status']/div"));
        WebElement militaryInput = militaryStatusContainer.findElement(By.xpath(".//input[contains(@id,'react-select-')]"));
        militaryInput.click();
        Thread.sleep(1500);
        String militaryInputId = militaryInput.getAttribute("id").replace("-input", "");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'" + militaryInputId + "-option')]"))).click();
        IO.println("selected military status");
        Thread.sleep(1000);

        // Step 15: select driving license
        driver.findElement(By.xpath("//div[@id='driving-license']/div/div/label/div")).click();
        IO.println("selected driving license 1");
        Thread.sleep(800);
        driver.findElement(By.xpath("//div[@id='driving-license']/div[2]/div/label/div")).click();
        IO.println("selected driving license 2");
        Thread.sleep(800);

        // Step 16: select city
        WebElement cityContainer = driver.findElement(By.xpath("//div[@id='location']/div/div"));
        WebElement cityInput = cityContainer.findElement(By.xpath(".//input[contains(@id,'react-select-')]"));
        cityInput.click();
        Thread.sleep(1500);
        String cityInputId = cityInput.getAttribute("id").replace("-input", "");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'" + cityInputId + "-option')]"))).click();
        IO.println("selected city");
        Thread.sleep(1000);

        // Step 17: select area
        WebElement areaContainer = driver.findElement(By.xpath("//div[@id='location']/div/div[2]"));
        WebElement areaInput = areaContainer.findElement(By.xpath(".//input[contains(@id,'react-select-')]"));
        areaInput.click();
        Thread.sleep(1500);
        String areaInputId = areaInput.getAttribute("id").replace("-input", "");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'" + areaInputId + "-option')]"))).click();
        IO.println("selected area");
        Thread.sleep(1000);

        // Step 18: fill primary phone
        WebElement primaryPhoneField = driver.findElement(By.name("primaryPhone"));
        primaryPhoneField.click();
        primaryPhoneField.sendKeys(Keys.CONTROL + "a", Keys.DELETE, "01116615352");
        IO.println("filled primary phone");
        Thread.sleep(500);

        // Step 19: leave other phone blank
        WebElement otherPhoneField = driver.findElement(By.name("otherPhone"));
        otherPhoneField.click();
        otherPhoneField.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        IO.println("left other phone blank");
        Thread.sleep(500);

        // Step 20: click save
        driver.findElement(By.xpath("//div[@id='app']/div/div[2]/div[2]/form/button")).click();
        IO.println("clicked save");
        Thread.sleep(2000);

        // Step 21: check error
        WebElement validationError = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='contact-info']/div[2]/div/span")));
        String expectedErrorMessage = "This is a required field";
        String actualErrorMessage = validationError.getText();

        if (validationError.isDisplayed() && actualErrorMessage.equals(expectedErrorMessage)) {
            IO.println("validation error showed correctly");
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
