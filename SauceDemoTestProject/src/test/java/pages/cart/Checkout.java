package pages.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Checkout {
    private final WebDriverWait wait;
    private final WebDriver driver;

    public Checkout(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    public void checkoutBtnClicked(){
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        driver.findElement(By.cssSelector("#checkout")).click();
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.saucedemo.com/checkout-step-one.html";
        Assert.assertEquals(actualUrl,expectedUrl,"OrderSummary Button does not lead us to the right page");
    }
    public void checkoutWithEmptyCart() {
        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        menuButton.click();

        WebElement resetLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("reset_sidebar_link")));
        resetLink.click();

        driver.findElement(By.cssSelector(".shopping_cart_link")).click();

        boolean isClickable;
        try {
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#checkout"))).click();
            isClickable = true;
        } catch (TimeoutException e) {
            isClickable = false;
        }

        Assert.assertFalse(isClickable,"❌ The 'OrderSummary' button should not be clickable when the cart is empty." );
    }

        public void checkoutHappyScenario() {
            WebElement cartLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".shopping_cart_link")));
            cartLink.click();

            WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#checkout")));
            checkout.click();

            WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
            firstName.sendKeys("Habiba");

            WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("last-name")));
            lastName.sendKeys("Moataz");

            WebElement postalCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postal-code")));
            postalCode.sendKeys("02");

            WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
            continueBtn.click();

            String actualUrl = driver.getCurrentUrl();
            String expectedUrl = "https://www.saucedemo.com/checkout-step-two.html";

            Assert.assertEquals(actualUrl, expectedUrl, "The process of checkout does not work as expected");
        }
    public void checkoutFirstNameEmpty() {
        WebElement cartLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(".shopping_cart_link")));
        cartLink.click();

        WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#checkout")));
        checkout.click();

        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
        firstName.sendKeys("");

        WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("last-name")));
        lastName.sendKeys("Moataz");

        WebElement postalCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postal-code")));
        postalCode.sendKeys("02");

        WebElement continueBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("continue")));
        continueBtn.click();

        String actualMsg = driver.findElement(By.xpath("//div[@class='error-message-container error']")).getText();

        String expectedMsg = "Error: First Name is required";

        Assert.assertEquals(actualMsg, expectedMsg, "OrderSummary without putting First Name");
    }
    public void checkoutFirstNameNumbers() {
        WebElement cartLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(".shopping_cart_link")));
        cartLink.click();

        WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#checkout")));
        checkout.click();

        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
        firstName.sendKeys("123Habiba");

        WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("last-name")));
        lastName.sendKeys("Moataz");

        WebElement postalCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postal-code")));
        postalCode.sendKeys("02");

        WebElement continueBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("continue")));
        continueBtn.click();

        String actualMsg = driver.findElement(By.xpath("//div[@class='error-message-container error']")).getText();

        String expectedMsg = "Error: Last name can not contain numbers";

        Assert.assertEquals(actualMsg, expectedMsg, "OrderSummary with putting incorrect First name");
    }
    public void checkoutFirstNameSpecialChar() {
        WebElement cartLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(".shopping_cart_link")));
        cartLink.click();

        WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#checkout")));
        checkout.click();

        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
        firstName.sendKeys("/Habiba");

        WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("last-name")));
        lastName.sendKeys("Moataz");

        WebElement postalCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postal-code")));
        postalCode.sendKeys("02");

        WebElement continueBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("continue")));
        continueBtn.click();

        String actualMsg = driver.findElement(By.xpath("//div[@class='error-message-container error']")).getText();

        String expectedMsg = "Error: First Name can not contain special characters";

        Assert.assertEquals(actualMsg, expectedMsg, "OrderSummary with putting incorrect First name");
    }
    public void checkoutEmptyLastName() {
        WebElement cartLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(".shopping_cart_link")));
        cartLink.click();

        WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#checkout")));
        checkout.click();

        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
        firstName.sendKeys("Habiba");

        WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("last-name")));
        lastName.sendKeys("");

        WebElement postalCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postal-code")));
        postalCode.sendKeys("02");

        WebElement continueBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("continue")));
        continueBtn.click();

        String actualMsg = driver.findElement(By.xpath("//div[@class='error-message-container error']")).getText();

        String expectedMsg = "Error: Last Name is required";

        Assert.assertEquals(actualMsg, expectedMsg, "OrderSummary without putting Last Name");
    }
    public void checkoutLastNameNumbers() {
        WebElement cartLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(".shopping_cart_link")));
        cartLink.click();

        WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#checkout")));
        checkout.click();

        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
        firstName.sendKeys("Habiba");

        WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("last-name")));
        lastName.sendKeys("123Moataz");

        WebElement postalCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postal-code")));
        postalCode.sendKeys("02");

        WebElement continueBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("continue")));
        continueBtn.click();

        String actualMsg = driver.findElement(By.xpath("//div[@class='error-message-container error']")).getText();

        String expectedMsg = "Error: Last name can not contain numbers";

        Assert.assertEquals(actualMsg, expectedMsg, "OrderSummary with putting incorrect Last name");
    }
    public void checkoutLastNameSpecialChar() {
    WebElement cartLink = wait.until(
            ExpectedConditions.elementToBeClickable(By.cssSelector(".shopping_cart_link")));
    cartLink.click();

    WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#checkout")));
    checkout.click();

    WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
    firstName.sendKeys("Habiba");

    WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("last-name")));
    lastName.sendKeys("/Moataz");

    WebElement postalCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postal-code")));
    postalCode.sendKeys("02");

    WebElement continueBtn = wait.until(
            ExpectedConditions.elementToBeClickable(By.id("continue")));
    continueBtn.click();

    String actualMsg = driver.findElement(By.xpath("//div[@class='error-message-container error']")).getText();

    String expectedMsg = "Error: Last Name can not contain special characters";

    Assert.assertEquals(actualMsg, expectedMsg, "OrderSummary with putting incorrect Last name");
}
    public void checkoutEmptyPostalCode() {
        WebElement cartLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(".shopping_cart_link")));
        cartLink.click();

        WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#checkout")));
        checkout.click();

        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
        firstName.sendKeys("Habiba");

        WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("last-name")));
        lastName.sendKeys("Moataz");

        WebElement postalCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postal-code")));
        postalCode.sendKeys("");

        WebElement continueBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("continue")));
        continueBtn.click();

        String actualMsg = driver.findElement(By.xpath("//div[@class='error-message-container error']")).getText();

        String expectedMsg = "Error: Postal Code is required";

        Assert.assertEquals(actualMsg, expectedMsg, "OrderSummary without putting postal code");
    }
    public void checkoutPostalCodeLetters() {
        WebElement cartLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(".shopping_cart_link")));
        cartLink.click();

        WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#checkout")));
        checkout.click();

        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
        firstName.sendKeys("Habiba");

        WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("last-name")));
        lastName.sendKeys("Moataz");

        WebElement postalCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postal-code")));
        postalCode.sendKeys("Abc02");

        WebElement continueBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("continue")));
        continueBtn.click();

        String actualMsg = driver.findElement(By.xpath("//div[@class='error-message-container error']")).getText();

        String expectedMsg = "Error: Postal code can not contain letters";

        Assert.assertEquals(actualMsg, expectedMsg, "OrderSummary with putting incorrect Postal Code");
    }
    public void checkoutPostalCodeSpecialChar() {
        WebElement cartLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(".shopping_cart_link")));
        cartLink.click();

        WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#checkout")));
        checkout.click();

        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
        firstName.sendKeys("Habiba");

        WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("last-name")));
        lastName.sendKeys("Moataz");

        WebElement postalCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postal-code")));
        postalCode.sendKeys("///02");

        WebElement continueBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("continue")));
        continueBtn.click();

        String actualMsg = driver.findElement(By.xpath("//div[@class='error-message-container error']")).getText();

        String expectedMsg = "Error: Postal code can not contain special characters";

        Assert.assertEquals(actualMsg, expectedMsg, "OrderSummary with putting incorrect Postal Code");
    }
}

