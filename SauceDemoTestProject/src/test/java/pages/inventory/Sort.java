package pages.inventory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Sort {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public Sort(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void sortNameAZ() {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("product_sort_container")));

        Select sortSelect = new Select(dropdown);
        sortSelect.selectByVisibleText("Name (A to Z)");

        List<WebElement> productElements = driver.findElements(By.className("inventory_item_name"));
        List<String> actualProductNames = new ArrayList<>();
        for (WebElement product : productElements) {
            actualProductNames.add(product.getText());
        }

        List<String> expectedProductNames = new ArrayList<>(actualProductNames);
        expectedProductNames.sort(String::compareToIgnoreCase);

        Assert.assertEquals(actualProductNames, expectedProductNames,
                "Products are not sorted from A-Z!");

        System.out.println(" Sorting from A-Z test passed!");
    }

    public void sortNameZA() {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("product_sort_container")));

        Select sortSelect = new Select(dropdown);
        sortSelect.selectByVisibleText("Name (Z to A)");

        List<WebElement> productElements = driver.findElements(By.className("inventory_item_name"));
        List<String> actualProductNames = new ArrayList<>();
        for (WebElement product : productElements) {
            actualProductNames.add(product.getText());
        }

        List<String> expectedProductNames = new ArrayList<>(actualProductNames);
        expectedProductNames.sort((a, b) -> b.compareToIgnoreCase(a));

        Assert.assertEquals(actualProductNames, expectedProductNames,
                "Products are not sorted from Z-A!");

        System.out.println("Sorting from Z-A test passed!");
    }

    public void sortPriceHighLow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("product_sort_container")));


        // Get prices BEFORE sorting
        List<WebElement> priceElementsBefore = driver.findElements(By.className("inventory_item_price"));
        List<String> pricesBefore = priceElementsBefore.stream().map(WebElement::getText).collect(Collectors.toList());

        // Select "Price (high to low)"
        Select sortSelect = new Select(dropdown);
        sortSelect.selectByVisibleText("Price (high to low)");

        // Wait until first price changes
        wait.until(driver1 -> {
            List<WebElement> priceElementsAfter = driver1.findElements(By.className("inventory_item_price"));
            return !priceElementsAfter.get(0).getText().equals(pricesBefore.get(0));
        });

        // Get and parse prices AFTER sorting
        List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));
        List<Double> actualPrices = new ArrayList<>();
        for (WebElement el : priceElements) {
            actualPrices.add(Double.parseDouble(el.getText().replace("$", "")));
        }

        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        expectedPrices.sort(Collections.reverseOrder());

        Assert.assertEquals(actualPrices, expectedPrices, "Prices are not sorted high to low!");

        System.out.println("Prices sorted high to low test passed!");
    }

    public void sortPriceLowHigh() {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("product_sort_container")));

        // Get prices BEFORE sorting
        List<WebElement> priceElementsBefore = driver.findElements(By.className("inventory_item_price"));
        List<String> pricesBefore = priceElementsBefore.stream().map(WebElement::getText).collect(Collectors.toList());

        // Select "Price (low to high)"
        Select sortSelect = new Select(dropdown);
        sortSelect.selectByVisibleText("Price (low to high)");

        // Wait until first price changes
        wait.until(driver1 -> {
            List<WebElement> priceElementsAfter = driver1.findElements(By.className("inventory_item_price"));
            return !priceElementsAfter.get(0).getText().equals(pricesBefore.get(0));
        });

        // Get and parse prices AFTER sorting
        List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));
        List<Double> actualPrices = new ArrayList<>();
        for (WebElement el : priceElements) {
            actualPrices.add(Double.parseDouble(el.getText().replace("$", "")));
        }

        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        expectedPrices.sort(Double::compareTo);

        Assert.assertEquals(actualPrices, expectedPrices, "Prices are not sorted low to high!");

        System.out.println(" Prices sorted low to high test passed!");
    }
}