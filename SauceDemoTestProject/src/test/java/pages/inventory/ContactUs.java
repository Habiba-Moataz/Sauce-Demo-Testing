package pages.inventory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;

public class ContactUs {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public ContactUs(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void contactX() {
        String originalWindow = driver.getWindowHandle();

        WebElement xPlatform = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a[href='https://twitter.com/saucelabs']")));
        xPlatform.click();

        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        tabs.remove(originalWindow);
        driver.switchTo().window(tabs.get(0));

        wait.until(ExpectedConditions.urlToBe("https://x.com/saucelabs"));

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://x.com/saucelabs";

        Assert.assertEquals(actualUrl, expectedUrl, "The button does not lead to the X account");

        driver.close();
        driver.switchTo().window(originalWindow);
    }
    public void contactFacebook() {
        String originalWindow = driver.getWindowHandle();

        WebElement facebookPlatform = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a[href='https://www.facebook.com/saucelabs']")));
        facebookPlatform.click();

        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        tabs.remove(originalWindow);
        driver.switchTo().window(tabs.get(0));

        wait.until(ExpectedConditions.urlToBe("https://www.facebook.com/saucelabs"));

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.facebook.com/saucelabs";

        Assert.assertEquals(actualUrl, expectedUrl, "The button does not lead to the Facebook account");

        driver.close();
        driver.switchTo().window(originalWindow);
    }
    public void contactLinkedin() {
        String originalWindow = driver.getWindowHandle();

        WebElement linkedinPlatform = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a[href='https://www.linkedin.com/company/sauce-labs/']")));
        linkedinPlatform.click();

        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        tabs.remove(originalWindow);
        driver.switchTo().window(tabs.get(0));

        wait.until(ExpectedConditions.urlToBe("https://www.linkedin.com/company/sauce-labs/"));

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.linkedin.com/company/sauce-labs/";

        Assert.assertEquals(actualUrl, expectedUrl, "The button does not lead to the Linkedin account");

        driver.close();
        driver.switchTo().window(originalWindow);
    }
}