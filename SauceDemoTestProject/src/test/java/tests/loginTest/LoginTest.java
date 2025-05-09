package tests.loginTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import pages.login.Login;

public class LoginTest {

    WebDriver driver;
    Login login;

    @BeforeClass
    public void setUp() {
        // Configure Chrome to suppress password popups
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-autofill-keyboard-accessory-view");
        options.addArguments("--incognito");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");

        driver = new ChromeDriver(options);
        login = new Login(driver);
        driver.manage().window().maximize();
    }
    @BeforeMethod
    public void setUpMethod() {
        // Clear session and login fresh for each test
        driver.get("https://www.saucedemo.com/");
        driver.manage().deleteAllCookies();
    }
    @Test
    public void happyScenarioTest() {
        login.loginHappyScenario();
    }
    @Test
    public void emptyUsernameTest() {
        login.loginEmptyUsername();
    }
    @Test
    public void emptyPasswordTest() {
        login.loginEmptyPassword();
    }
    @Test
    public void WrongUsernameTest() {
        login.loginWrongUsername();
    }
    @Test
    public void WrongPasswordTest() {
        login.loginWrongPassword();
    }
    @Test
    public void SensitivityUsernameTest() {
        login.loginSensitivityUsername();
    }
    @Test
    public void SensitivityPasswordTest() {
        login.loginSensitivityPassword();
    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
