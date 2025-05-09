package tests.inventoryTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.inventory.Menu;

public class MenuTest {
    WebDriver driver;
    Menu menu;

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
        menu = new Menu(driver);
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
    public void opensSuccessfullyTest() {
        menu.menuOpensSuccessfully();
    }

    @Test
    public void allItemsTest() {
        menu.menuAllItems();
    }

    @Test
    public void aboutTest() {
        menu.menuAbout();
    }

    @Test
    public void logoutTest() {
        menu.menuLogout();
    }
    @Test
    public void resetAppBadgeTest(){
        menu.menuResetAppBadge();
    }
    @Test
    public void resetAppRemoveTest(){
        menu.menuResetAppRemove();
    }
    @Test
    public void resetAppCartPageTest(){
        menu.menuResetAppCartPage();
    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}