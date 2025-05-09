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

public class AddAndRemove {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public AddAndRemove(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void addToRemove(){
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
        List<WebElement> removeButtons = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector("[id^='remove-']")
                )
        );
        Assert.assertEquals(addToCartButtons.size(), removeButtons.size(),
                "Some 'Add to Cart' buttons did not change to 'Remove' after being clicked.");
    }
    public void removeToAdd(){
        List<WebElement> addToCartButtons = driver.findElements(By.cssSelector("[id^='add-to-cart-']"));
        if(!addToCartButtons.isEmpty()) {
            for (WebElement button : addToCartButtons) {
                try {
                    // Wait for each button to be clickable before clicking
                    wait.until(ExpectedConditions.elementToBeClickable(button)).click();
                } catch (StaleElementReferenceException e) {
                    // Handle stale elements by re-finding the buttons
                    addToCartButtons = driver.findElements(By.cssSelector("[id^='add-to-cart-']"));
                    button.click();
                }
            }
        }
        List<WebElement> removeButtons = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector("[id^='remove-']")
                )
        );
        if(removeButtons.isEmpty()) {
            throw new RuntimeException("No Remove buttons found");
        }

        for(WebElement button : removeButtons) {
            try {
                // Wait for each button to be clickable before clicking
                wait.until(ExpectedConditions.elementToBeClickable(button)).click();
            } catch (StaleElementReferenceException e) {
                // Handle stale elements by re-finding the buttons
                removeButtons = driver.findElements(
                        By.cssSelector("[id^='remove-']"));
                button.click();
            }
        }
        List<WebElement> restoredAddButtons = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector("[id^='add-to-cart-']")
                )
        );

        Assert.assertEquals(removeButtons.size(), restoredAddButtons.size(),
                "Some 'Remove' buttons did not change to 'Add to Cart' after being clicked.");
    }
    public void addCartBadgeIncreasesCorrectly() {
        int initialOrderCount;

        // 1. Check initial cart state safely
        List<WebElement> initialBadge = driver.findElements(
                By.xpath("//span[@class='shopping_cart_badge']")
        );

        initialOrderCount = initialBadge.isEmpty() ? 0 :
                Integer.parseInt(initialBadge.get(0).getText());
        // 2. Add first item
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("add-to-cart-sauce-labs-bike-light")
        ));
        addButton.click();

        // 3. Wait for badge update and verify
        WebElement updatedBadge = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[@class='shopping_cart_badge']")
        ));
        int finalOrderCount = Integer.parseInt(updatedBadge.getText());

        // 4. Clear validation
        Assert.assertEquals(finalOrderCount, initialOrderCount + 1,
                "Cart should show 1 item after adding product");
    }
    public void removeCartBadgeDecreasesCorrectly() {
        int initialOrderCount;
        List<WebElement> addButtons = driver.findElements(
                By.id("add-to-cart-sauce-labs-backpack")
        );

        if (!addButtons.isEmpty()) {
            WebElement addButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.id("add-to-cart-sauce-labs-backpack")
                    )
            );
            addButton.click();
        }

        // 1. Check initial cart state safely
        List<WebElement> initialBadge = driver.findElements(
                By.xpath("//span[@class='shopping_cart_badge']")
        );

        initialOrderCount = Integer.parseInt(initialBadge.get(0).getText());

        WebElement removeButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("remove-sauce-labs-backpack")
                )
        );
        removeButton.click();
        List<WebElement> updatedBadge = driver.findElements(
                By.xpath("//span[@class='shopping_cart_badge']")
        );
        int finalOrderCount =  updatedBadge.isEmpty() ? 0 :
                Integer.parseInt(updatedBadge.get(0).getText());

        Assert.assertEquals(finalOrderCount, initialOrderCount - 1,
                "Cart should show decreases 1 item after adding product");
    }

}
