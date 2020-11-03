package org.example.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.base.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;


public class LoginPageTest extends Base {
    WebDriver driver;
    public static Logger log = LogManager.getLogger(LoginPageTest.class.getName());

    @BeforeMethod
    public void setUp() throws IOException {
        driver = initializeDriver();
        log.info("Driver is initialized");
        driver.get(properties.getProperty("url"));
        log.info("Navigated to Login Page");
    }

    @Test(dataProvider = "getData")
    public void loginUsingIncorrectDataTest(String Username, String Password) throws IOException, InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.username().sendKeys(Username);
        loginPage.password().sendKeys(Password);
        log.info("Enter incorrect user credentials");
        loginPage.loginBtn().click();
        String errorMsg = loginPage.getErrorMessage().getText();
        Assert.assertTrue(loginPage.getErrorMessage().isDisplayed());
        Assert.assertEquals(errorMsg, "Login failed");
        log.info(errorMsg + " - error message validated");
    }

    @Test
    public void loginWithEmptyFieldsLeft() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginBtn().click();
        String errorMsg = loginPage.getEmptyFieldErrorMessage().getText();

        Assert.assertTrue(loginPage.getEmptyFieldErrorMessage().isDisplayed());
        Assert.assertEquals(errorMsg, "You can't leave fields empty");
        log.info("Empty field error message validated");
    }

    @DataProvider
    public Object[] getData() {
        Object[][] data = new Object[3][2];
        data[0][0] = "user55";
        data[0][1] = "password";

        data[1][0] = " ";
        data[1][1] = "password55";

        data[2][0] = "user55";
        data[2][1] = "pasword55";

        return data;
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}