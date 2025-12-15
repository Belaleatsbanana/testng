package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Hooks {
    
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/home/fax/Downloads/chromedriver-linux64/chromedriver");
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        option.addArguments("--start-maximized");
        TestContext.driver = new ChromeDriver(option);
        TestContext.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        TestContext.wait = new WebDriverWait(TestContext.driver, Duration.ofSeconds(10));
    }
    
    @After
    public void tearDown() {
        if (TestContext.driver != null) {
            TestContext.driver.close();
            TestContext.driver.quit();
        }
    }
}
