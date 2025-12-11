import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

void main() throws InterruptedException {
    System.setProperty("webdriver.chrome.driver", "/home/fax/Downloads/chromedriver-linux64/chromedriver");

    ChromeOptions option = new ChromeOptions();
    option.addArguments("--remote-allow-origins=*");
    option.addArguments("--start-maximized");
    ChromeDriver driver = new ChromeDriver(option);


    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    try {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1: go to wuzzuf
        driver.get("https://wuzzuf.net");
        Thread.sleep(1000);

        // Step 2: login
        new WuzzufLogin(driver).login();
        Thread.sleep(2000);



        // Step 3: wait then click on profile
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div/div/div/div")
                )
        ).click();
        IO.println("clicked profile");
        Thread.sleep(1000);

        // Step 4: wait then click on contact us
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@id='app']/div/div/header/div/div[2]/div[2]/div/div/div[2]/div/div[9]/a/div/span")
                )
        ).click();
        IO.println("clicked contact us");
        Thread.sleep(2000);

        // Step 5: wait then type in subject
        WebElement subjectField = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("subject"))
        );
        subjectField.click();
        Thread.sleep(500);
        subjectField.clear();
        subjectField.sendKeys("Partnership Request");
        IO.println("typed in subject field");
        Thread.sleep(1000);

        // Step 6: wait then type in message field
        WebElement messageField = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("message"))
        );
        messageField.click();
        Thread.sleep(500);
        messageField.clear();
        messageField.sendKeys("Partnership message test");
        IO.println("typed in message field");
        Thread.sleep(1000);

        // Step 7: click the send
        driver.findElement(By.id("popup_send")).click();
        IO.println("clicked send");
        Thread.sleep(2000);

        // Step 8: wait then read error message
        WebElement errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Sending...'])[1]/following::p[1]")
                )
        );

        String expectedErrorMessage = "The Recaptcha field is required.";
        String actualErrorMessage = errorMessage.getText();

        if (actualErrorMessage.equals(expectedErrorMessage)) {
            IO.println("Recaptcha error displayed correctly");
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