package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WuzzufLogin {

    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private final By emailField = By.name("email");
    private final By passwordField = By.name("password");
    private final By loginButton = By.xpath("/html/body/div/div/div[1]/div/div[3]/form/button");

    public WuzzufLogin(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void login() {
        String email = "dummy236990@gmail.com";
        String password = "dummyaccount123";

        driver.get("https://wuzzuf.net/login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));

        // Fill email
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);

        // Fill password
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);

        // Click submit using  XPath
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginBtn.click();

        System.out.println("logged in successfully");
    }
}
