package tests.cartTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.cart.Cart;

public class CartTest {
    WebDriver driver;
    Cart cart;

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
        cart = new Cart(driver);
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
    public void cartOpensTest (){
        cart.cartOpened();
    }
    @Test
    public void cartItemCountMatchesPageItemsTest(){
        cart.cartItemCountMatchesPageItems();
    }
    @Test
    public void qtyEditableTest(){
        cart.cartQtyEditable();
    }
    @Test
    public void removeWithBadgeTest(){
        cart.cartRemoveWithBadge();
    }
    @Test
    public void removeWithProductPageTest(){
        cart.cartRemoveWithProductPage();
    }
    @Test
    public void continueShoppingBtnTest(){
        cart.cartContinueShoppingBtn();
    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
