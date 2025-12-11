import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import java.time.Duration;

public class LoginTest {
    protected WebDriver driver;


    @BeforeMethod
    public void setUp() {
        System.out.println("Setting up test...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void simpleTest() throws InterruptedException {
        driver.get("https://www.saucedemo.com/");

        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        loginButton.click();
        Thread.sleep(3000);

        //Assert.assertTrue(successMessage.isDisplayed(), "Login success message should be displayed");
        //assertEquals(2 + 2, 4, "Basic math should work");
    }


    @AfterMethod
    public void tearDown() {
        WebElement successMessage = driver.findElement(By.cssSelector("#header_container > div.primary_header > div.header_label > div"));

        if(successMessage.isDisplayed()) {
            System.out.println("Test passed");
        }else{
            System.out.println("Test failed");
        }

        System.out.println("Cleaning up test...");
        driver.close();
        driver.quit();
    }
}