package tests.cartTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.cart.Checkout;

public class CheckoutTest {
    WebDriver driver;
    Checkout checkout;

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
        checkout = new Checkout(driver);
        driver.manage().window().maximize();
    }
    @BeforeMethod
    public void setUpMethod() {
        // Clear session and login fresh for each test
        driver.get("https://www.saucedemo.com/");
        driver.manage().deleteAllCookies();

        // Optimized login flow
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Add brief wait for page load
        try { Thread.sleep(1000); }
        catch (InterruptedException e) { e.printStackTrace(); }
    }
    @Test
    public void ckeckoutClickedTest(){
        checkout.checkoutBtnClicked();
    }
    @Test
    public void checkoutWithEmptyCartTest(){
        checkout.checkoutWithEmptyCart();
    }
    @Test
    public void checkoutHappyScenarioTest(){
        checkout.checkoutHappyScenario();
    }
    @Test
    public void emptyFirstNameTest(){
        checkout.checkoutFirstNameEmpty();
    }
    @Test
    public void numberFirstNameTest(){
        checkout.checkoutFirstNameNumbers();
    }
    @Test
    public void specialCharacterFirstNameTest(){
        checkout.checkoutFirstNameSpecialChar();
    }
    @Test
    public void emptyLastNameTest(){
        checkout.checkoutEmptyLastName();
    }
    @Test
    public void numberLastNameTest(){
        checkout.checkoutLastNameNumbers();
    }
    @Test
    public void specialCharacterLastNameTest(){
        checkout.checkoutLastNameSpecialChar();
    }
    @Test
    public void emptyPostalCodeTest(){
        checkout.checkoutEmptyPostalCode();
    }
    @Test
    public void letterPostalCodeTest(){
        checkout.checkoutPostalCodeLetters();
    }
    @Test
    public void specialCharacterPostalCodeTest(){
        checkout.checkoutPostalCodeSpecialChar();
    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
