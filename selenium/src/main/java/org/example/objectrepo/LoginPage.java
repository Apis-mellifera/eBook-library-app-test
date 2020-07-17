package org.example.objectrepo;

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

    public WebElement login() {
        return driver.findElement(username);
    }

    public WebElement password() {
        return driver.findElement(password);
    }

    public WebElement submit() {
        return driver.findElement(loginBtn);
    }

    public WebElement register() {
        return driver.findElement(registerBtn);
    }
}
