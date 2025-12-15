import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

void main() throws InterruptedException {
    // Locators
    By profileDropdown = By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div/div/div/div");
    By contactUsOption = By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div[2]/div/div[9]/a/div/span");
    By subjectField = By.id("subject");
    By messageField = By.id("message");
    By sendButton = By.id("popup_send");
    By captchaError = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Sending...'])[1]/following::p[1]");
    
    System.setProperty("webdriver.chrome.driver", "/home/fax/Downloads/chromedriver-linux64/chromedriver");

    ChromeOptions option = new ChromeOptions();
    option.addArguments("--remote-allow-origins=*");
    option.addArguments("--start-maximized");
    ChromeDriver driver = new ChromeDriver(option);

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    try {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1: Navigate to Wuzzuf
        driver.get("https://wuzzuf.net");
        Thread.sleep(1000);

        // Step 2: Login
        new WuzzufLogin(driver).login();
        Thread.sleep(2000);

        // Step 3: Click on profile dropdown
        wait.until(ExpectedConditions.elementToBeClickable(profileDropdown)).click();
        IO.println("Clicked profile dropdown");
        Thread.sleep(1000);

        // Step 4: Click on Contact Us option
        wait.until(ExpectedConditions.elementToBeClickable(contactUsOption)).click();
        IO.println("Clicked contact us");
        Thread.sleep(2000);

        // Step 5: Fill in subject field
        WebElement subject = wait.until(ExpectedConditions.elementToBeClickable(subjectField));
        subject.click();
        Thread.sleep(500);
        subject.clear();
        subject.sendKeys("Partnership Request");
        IO.println("Filled subject field");
        Thread.sleep(1000);

        // Step 6: Fill in message field
        WebElement message = wait.until(ExpectedConditions.elementToBeClickable(messageField));
        message.click();
        Thread.sleep(500);
        message.clear();
        message.sendKeys("Partnership message test");
        IO.println("Filled message field");
        Thread.sleep(1000);

        // Step 7: Click send button without completing reCAPTCHA
        driver.findElement(sendButton).click();
        IO.println("Clicked send button");
        Thread.sleep(2000);

        // Step 8: Verify error message appears
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(captchaError));

        String expectedErrorMessage = "The Recaptcha field is required.";
        String actualErrorMessage = errorMessage.getText();

        if (actualErrorMessage.equals(expectedErrorMessage)) {
            IO.println("captcha error displayed");
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
