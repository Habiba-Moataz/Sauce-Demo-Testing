package tests.inventoryTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.inventory.AddAndRemove;

public class AddAndRemoveTest {
    WebDriver driver;
    AddAndRemove addAndRemove;

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
        addAndRemove = new AddAndRemove(driver);
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

    @org.testng.annotations.Test(priority = 1)
    public void addToRemoveTest(){
        addAndRemove.addToRemove();
    }
    @org.testng.annotations.Test(priority = 2)
    public void removeToAddTest(){
        addAndRemove.removeToAdd();
    }
    @org.testng.annotations.Test(priority = 3)
    public void cartBadgeIncreasesCorrectlyTest(){
        addAndRemove.addCartBadgeIncreasesCorrectly();
    }
    @org.testng.annotations.Test(priority = 4)
    public void cartBadgeDecreasesCorrectlyTest(){
        addAndRemove.removeCartBadgeDecreasesCorrectly();
    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
