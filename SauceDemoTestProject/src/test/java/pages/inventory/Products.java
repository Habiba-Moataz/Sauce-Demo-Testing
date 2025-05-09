package pages.inventory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Products {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public Products(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void productsVerification() {
        List<WebElement> products = driver.findElements(By.className("inventory_item_name"));
        List<String> errors = new ArrayList<>();

        for (int i = 0; i < products.size(); i++) {
            products = driver.findElements(By.className("inventory_item_name"));
            String productName = products.get(i).getText();

            products.get(i).click();

            try {
                WebElement productTitle = driver.findElement(By.className("inventory_details_name"));
                Assert.assertEquals(productName, productTitle.getText(), "Mismatch in product: " + productName);
            } catch (AssertionError e) {
                errors.add(e.getMessage());
            }

            driver.navigate().back();
        }

        if (!errors.isEmpty()) {
            Assert.fail("There were product errors: " + errors);
        }
    }

}
