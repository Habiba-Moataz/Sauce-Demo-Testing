package pages.inventory;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class Menu {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public Menu(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void menuOpensSuccessfully() {
        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        menuButton.click();

        WebElement resetLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reset_sidebar_link")));
        assert resetLink.isDisplayed();
    }
    public void menuAllItems() {
        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        menuButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reset_sidebar_link")));
        driver.findElement(By.id("inventory_sidebar_link")).click();
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(actualUrl,expectedUrl,"All items are not opened successfully");
    }
    public void menuAbout() {
        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        menuButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reset_sidebar_link")));
        driver.findElement(By.id("about_sidebar_link")).click();
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://saucelabs.com/";
        Assert.assertEquals(actualUrl,expectedUrl,"All items are not opened successfully");
    }
    public void menuLogout() {
        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        menuButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reset_sidebar_link")));
        driver.findElement(By.id("logout_sidebar_link")).click();
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.saucedemo.com/";
        Assert.assertEquals(actualUrl,expectedUrl,"All items are not opened successfully");
    }
    public void menuResetAppBadge() {
        List<WebElement> addToCartButtons = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector("[id^='add-to-cart-']")
                )
        );

        if(addToCartButtons.isEmpty()) {
            throw new RuntimeException("No Add to Cart buttons found");
        }

        for(WebElement button : addToCartButtons) {
            try {
                // Wait for each button to be clickable before clicking
                wait.until(ExpectedConditions.elementToBeClickable(button)).click();
            } catch (StaleElementReferenceException e) {
                // Handle stale elements by re-finding the buttons
                addToCartButtons = driver.findElements(
                        By.cssSelector("[id^='add-to-cart-']"));
                button.click();
            }
        }

        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("react-burger-menu-btn")));
        menuButton.click();

        WebElement resetLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("reset_sidebar_link")));
        resetLink.click();

            WebElement cartBadge = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
            String badgeText = cartBadge.getText();
            Assert.assertTrue(badgeText.isEmpty(), "Cart badge should be empty after reset, but found: " + badgeText);
    }

    public void menuResetAppRemove() {
        List<WebElement> addToCartButtons = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector("[id^='add-to-cart-']")
                )
        );

        if(addToCartButtons.isEmpty()) {
            throw new RuntimeException("No Add to Cart buttons found");
        }

        for(WebElement button : addToCartButtons) {
            try {
                // Wait for each button to be clickable before clicking
                wait.until(ExpectedConditions.elementToBeClickable(button)).click();
            } catch (StaleElementReferenceException e) {
                // Handle stale elements by re-finding the buttons
                addToCartButtons = driver.findElements(
                        By.cssSelector("[id^='add-to-cart-']"));
                button.click();
            }
        }

        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("react-burger-menu-btn")));
        menuButton.click();

        WebElement resetLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("reset_sidebar_link")));
        resetLink.click();

        List<WebElement> removetButtons = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector("[id^='remove-']")
                )
        );
        Assert.assertTrue(removetButtons.isEmpty(),"There is " + removetButtons.size() + " Remove Buttons even if you click on Reset App State");
    }
    public void menuResetAppCartPage() {
        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("react-burger-menu-btn")));
        menuButton.click();

        WebElement resetLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("reset_sidebar_link")));
        resetLink.click();

        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        driver.findElement(By.cssSelector("#checkout")).click();

        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));

        Assert.assertTrue(cartItems.isEmpty(),
                "There is an items in the cart page even after click Reset App State");

    }

}

