package pages.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;

public class OrderSummary {
    private final WebDriverWait wait;
    private final WebDriver driver;

    public OrderSummary(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void orderItemTotalCorrect() {
        Double totalPrice = 0.0 ;

        List<WebElement> prices = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.className("item_pricebar")
                )
        );
        if (!prices.isEmpty()){
            for(WebElement price : prices) {
                String priceStr = price.getText();
                priceStr = priceStr.replace("$", "");
                totalPrice+= Double.parseDouble(priceStr);
            }
        }
        String actul = driver.findElement(By.cssSelector(".summary_subtotal_label")).getText();
        actul=actul.replace("Item total: $", "");
        Double expectedPrice = Double.parseDouble(actul);

        Assert.assertEquals(expectedPrice, totalPrice,  "The calculation of total items is incorrect");
    }
    public void orderTotalCorrect() {

        String itemStr = driver.findElement(By.cssSelector(".summary_subtotal_label")).getText().replace("Item total: $", "");
        Double item = Double.parseDouble(itemStr);

        String taxStr = driver.findElement(By.cssSelector(".summary_tax_label")).getText().replace("Tax: $", "");
        Double tax = Double.parseDouble(taxStr);

        Double expectedTotal = item + tax;

        String actualStr = driver.findElement(By.cssSelector(".summary_total_label")).getText().replace("Total: $", "");
        Double actualTotal = Double.parseDouble(actualStr);

        Assert.assertEquals(expectedTotal, actualTotal, "The calculation of total price is incorrect");
    }
    public void orderFinishSuccessfully() {

        WebElement finishBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("finish")));
        finishBtn.click();

        String confirmationMsg = driver.findElement(By.className("complete-header")).getText();
        String expectedMsg = "Thank you for your order!";

        Assert.assertEquals(confirmationMsg, expectedMsg, "The order does not finish successfully");
    }
    public void orderBackHome() {

        WebElement finishBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("finish")));
        finishBtn.click();

        WebElement HomeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("back-to-products")));
        HomeBtn.click();

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.saucedemo.com/inventory.html";

        Assert.assertEquals(actualUrl, expectedUrl, "The order does not finish successfully");
    }

}

