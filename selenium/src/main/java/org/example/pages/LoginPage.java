package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    By username = By.cssSelector("#login");
    By password = By.cssSelector("#password");
    By loginBtn = By.cssSelector("#login-btn");
    By registerBtn = By.cssSelector("#register-btn");
    By errorMessage =By.cssSelector(".alert");
    By emptyFieldErrorMessage =By.cssSelector(".alert__content");

    public WebElement username() {
        return driver.findElement(username);
    }

    public WebElement password() {
        return driver.findElement(password);
    }

    public WebElement loginBtn() {
        return driver.findElement(loginBtn);
    }

    public WebElement registerBtn() {
        return driver.findElement(registerBtn);
    }

    public WebElement getErrorMessage() {
        return driver.findElement(errorMessage);
    }

    public WebElement getEmptyFieldErrorMessage() {
        return driver.findElement(emptyFieldErrorMessage);
    }

    public TitlesPage successfulLogin() {
        username().sendKeys("user55");
        password().sendKeys("password55");
        loginBtn().click();
        return new TitlesPage(driver);
    }

    public RegisterPage register() throws InterruptedException {
        registerBtn().click();
        Thread.sleep(3000);
        String currentWindowHandle = driver.getWindowHandle();
        driver.switchTo().window(currentWindowHandle);
        return new RegisterPage(driver);
    }
}
