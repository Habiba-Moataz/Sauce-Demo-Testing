package pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.habiba.saucedemo.base.DriverManager;
import org.testng.Assert;

public class Login {
    WebDriver driver = DriverManager.getDriver();

    public Login (WebDriver driver){
        this.driver = driver;
    }

    public void loginHappyScenario() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(actualUrl,expectedUrl,"Login failed");
    }
    public void loginEmptyUsername() {
        driver.findElement(By.id("user-name")).sendKeys("");
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.id("login-button")).click();
        String actualMsg = driver.findElement(By.xpath("(//h3[normalize-space()='Epic sadface: Username is required'])[1]")).getText();
        String expectedMsg = "Epic sadface: Username is required";
        Assert.assertEquals(actualMsg,expectedMsg,"Login without putting username");
    }
    public void loginEmptyPassword() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.id("login-button")).click();
        String actualMsg = driver.findElement(By.xpath("//h3[normalize-space()='Epic sadface: Password is required']")).getText();
        String expectedMsg = "Epic sadface: Password is required";
        Assert.assertEquals(actualMsg,expectedMsg,"Login without putting password");
    }
    public void loginWrongUsername() {
        driver.findElement(By.id("user-name")).sendKeys("standard_use");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String actualMsg = driver.findElement(By.xpath("//h3[contains(text(),'Epic sadface: Username and password do not match a')]")).getText();
        String expectedMsg = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(actualMsg,expectedMsg,"Login with wrong username");
    }
    public void loginWrongPassword() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauc");
        driver.findElement(By.id("login-button")).click();
        String actualMsg = driver.findElement(By.xpath("//h3[contains(text(),'Epic sadface: Username and password do not match a')]")).getText();
        String expectedMsg = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(actualMsg,expectedMsg,"Login with wrong password");
    }
    public void loginSensitivityUsername() {
        driver.findElement(By.id("user-name")).sendKeys("STANDARD_USER");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String actualMsg = driver.findElement(By.xpath("//h3[contains(text(),'Epic sadface: Username and password do not match a')]")).getText();
        String expectedMsg = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(actualMsg,expectedMsg,"Login with wrong username");
    }
    public void loginSensitivityPassword() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("SECRET_SAUCE");
        driver.findElement(By.id("login-button")).click();
        String actualMsg = driver.findElement(By.xpath("//h3[contains(text(),'Epic sadface: Username and password do not match a')]")).getText();
        String expectedMsg = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(actualMsg,expectedMsg,"Login with wrong password");
    }
}