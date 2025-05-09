package pages.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class Cart {

    private final WebDriverWait wait;
    private final WebDriver driver;

    public Cart (WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void cartOpened() {
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.saucedemo.com/cart.html";
        Assert.assertEquals(actualUrl,expectedUrl,"Cart page does not open");
    }
    public void cartItemCountMatchesPageItems(){
        List<WebElement> addButtons = driver.findElements(By.id("add-to-cart-sauce-labs-bike-light"));

        if (!addButtons.isEmpty() && addButtons.get(0).isDisplayed()) {
            addButtons.get(0).click();
        }
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();

        String cartBadgeText = driver.findElement(By.className("shopping_cart_badge")).getText();
        int badgeCount = Integer.parseInt(cartBadgeText);

        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        int cartPageItemCount = cartItems.size();

        // Assertion
        Assert.assertEquals(badgeCount, cartPageItemCount,"Cart badge count doesn't match number of items in cart page");
    }

    public void cartQtyEditable() {
        List<WebElement> addButtons = driver.findElements(By.id("add-to-cart-sauce-labs-bike-light"));

        if (!addButtons.isEmpty() && addButtons.get(0).isDisplayed()) {
            addButtons.get(0).click();
        }

        driver.findElement(By.cssSelector(".shopping_cart_link")).click();

        // Try to find input field for QTY (if it exists)
        List<WebElement> qtyFields = driver.findElements(By.cssSelector("input[type='number'], input[type='text']"));

        // Assert failure if no editable field is found
        Assert.assertTrue(qtyFields.size() > 0,"Quantity field is not editable or not present");
    }

    public void cartRemoveWithBadge() {
        List<WebElement> addButtons = driver.findElements(By.id("add-to-cart-sauce-labs-bike-light"));

        if (!addButtons.isEmpty() && addButtons.get(0).isDisplayed()) {
            addButtons.get(0).click();
        }

        driver.findElement(By.cssSelector(".shopping_cart_link")).click();

        List<WebElement> initialBadge = driver.findElements(
                By.xpath("//span[@class='shopping_cart_badge']")
        );

        int initialOrderCount = Integer.parseInt(initialBadge.get(0).getText());

        driver.findElement(By.cssSelector("#remove-sauce-labs-bike-light")).click();
        List<WebElement> updatedBadge = driver.findElements(
                By.xpath("//span[@class='shopping_cart_badge']")
        );
        int finalOrderCount =  updatedBadge.isEmpty() ? 0 :
                Integer.parseInt(updatedBadge.get(0).getText());

        Assert.assertEquals(finalOrderCount, initialOrderCount - 1,
                "Cart should show decreases 1 item after adding product");

    }

    public void cartRemoveWithProductPage() {
        List<WebElement> addButtons = driver.findElements(By.id("add-to-cart-sauce-labs-bike-light"));

        if (!addButtons.isEmpty() && addButtons.get(0).isDisplayed()) {
            addButtons.get(0).click();
        }

        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        int initialPageItemCount = cartItems.size();
        driver.findElement(By.cssSelector("#remove-sauce-labs-bike-light")).click();
        List<WebElement> updatedCartItems = driver.findElements(
                By.xpath("//span[@class='shopping_cart_badge']")
        );
        int finalPageItemCount =  updatedCartItems.isEmpty() ? 0 :updatedCartItems.size();

        Assert.assertEquals(finalPageItemCount, initialPageItemCount - 1,
                "Page Cart should decrease 1 item after adding product");

    }
    public void cartContinueShoppingBtn() {
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        driver.findElement(By.cssSelector("#continue-shopping")).click();
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(actualUrl,expectedUrl,"Continue shopping does not return to inventory page");
    }
}