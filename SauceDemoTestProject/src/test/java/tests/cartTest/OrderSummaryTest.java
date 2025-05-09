package tests.cartTest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import pages.cart.OrderSummary;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class OrderSummaryTest {
    WebDriver driver;
    WebDriverWait wait;
    OrderSummary orderSummary;

    @BeforeClass
    public void setUp() {
        // Setup Chrome with options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-autofill-keyboard-accessory-view");
        options.addArguments("--incognito");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));  // ✅ بعد تعريف driver

        orderSummary = new OrderSummary(driver);
    }

    @BeforeMethod
    public void setUpMethod() {
        driver.get("https://www.saucedemo.com/");
        driver.manage().deleteAllCookies();

        // Login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // انتظر حتى تحميل صفحة المنتجات بشكل كامل
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".inventory_list"))
        );

        List<WebElement> addToCartButtons = driver.findElements(
                By.cssSelector("[id^='add-to-cart-']")
        );

        // الضغط على الأزرار المتاحة فقط
        if (!addToCartButtons.isEmpty()) {
            for (WebElement button : addToCartButtons) {
                try {
                    wait.until(ExpectedConditions.elementToBeClickable(button)).click();
                } catch (StaleElementReferenceException e) {
                    WebElement freshButton = driver.findElement(By.id(button.getAttribute("id")));
                    wait.until(ExpectedConditions.elementToBeClickable(freshButton)).click();
                } catch (Exception e) {
                    System.out.println("Error clicking button: " + e.getMessage());
                }
            }
        }

        WebElement cartLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(".shopping_cart_link"))
        );
        cartLink.click();


        WebElement checkoutBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("checkout"))
        );
        checkoutBtn.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")))
                .sendKeys("Habiba");
        driver.findElement(By.id("last-name")).sendKeys("Moataz");
        driver.findElement(By.id("postal-code")).sendKeys("02");
        driver.findElement(By.id("continue")).click();

        new WebDriverWait(driver, Duration.ofSeconds(1));
    }

    @Test
    public void itemTotalCorrectTest() {
        orderSummary.orderItemTotalCorrect();
    }

    @Test
    public void totalCorrectTest() {
        orderSummary.orderTotalCorrect();
    }

    @Test
    public void finishSuccessfullyTest() {
        orderSummary.orderFinishSuccessfully();
    }
    @Test
    public void BackHomeTest() {
        orderSummary.orderBackHome();
    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
