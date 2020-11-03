package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {
    WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    By title = By.className("sub-title");
    By username = By.cssSelector("#login");
    By password = By.cssSelector("#password");
    By repeatPassword = By.cssSelector("#password-repeat");
    By registerBtn = By.cssSelector("#register-btn");
    By loginBtn = By.cssSelector("#login-btn");
    By existingUserErrorMessage = By.cssSelector(".alert__content");
    By emptyFieldErrorMessage = By.cssSelector(".alert__content");

    public WebElement username() {
        return driver.findElement(username);
    }

    public WebElement password() {
        return driver.findElement(password);
    }

    public WebElement repeatPassword() {
        return driver.findElement(repeatPassword);
    }

    public WebElement loginBtn() {
        return driver.findElement(loginBtn);
    }

    public WebElement registerBtn() {
        return driver.findElement(registerBtn);
    }

    public WebElement getTitle() {
        return driver.findElement(title);
    }

    public WebElement getExistingUserErrorMessage() {
        return driver.findElement(existingUserErrorMessage);
    }

    public WebElement getEmptyFieldErrorMessage() {
        return driver.findElement(emptyFieldErrorMessage);
    }

    public LoginPage navigateToLoginPage() {
        loginBtn().click();
        return new LoginPage(driver);
    }


}
