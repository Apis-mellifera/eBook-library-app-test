package org.example.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.base.Base;
import org.example.utilities.TestUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RegisterPageTest extends Base {
    WebDriver driver;
    public static Logger log = LogManager.getLogger(RegisterPageTest.class.getName());

    @BeforeMethod
    public void setUp() throws IOException {
        driver = initializeDriver();
        log.info("Driver is initialized");
        driver.get(properties.getProperty("url"));
        log.info("Navigated to Login Page");
    }

    @Test(dataProvider = "getData")
    public void registerUsingExistingUser(String username, String password, String repeatPassword) throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = loginPage.register();
        log.debug("Clicked on Register button");
        registerPage.username().sendKeys(username);
        registerPage.password().sendKeys(password);
        registerPage.repeatPassword().sendKeys(repeatPassword);
        log.info("Enter existing user credentials");
        registerPage.registerBtn().click();
        driver.manage().timeouts().implicitlyWait(TestUtil.WAIT, TimeUnit.SECONDS);

        Assert.assertTrue(registerPage.getExistingUserErrorMessage().isDisplayed());
        Assert.assertTrue(registerPage.getExistingUserErrorMessage().getText().contains("user already exist"));
        log.info("User already exist message validated");
    }

    @Test
    public void registerWithEmptyFieldLeft() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = loginPage.register();
        log.debug("Clicked on Register button");
        String subtitle = registerPage.getTitle().getText();
        log.debug("Validated subtitle: " + subtitle);
        registerPage.registerBtn().click();
        driver.manage().timeouts().implicitlyWait(TestUtil.WAIT, TimeUnit.SECONDS);

        Assert.assertTrue(registerPage.getEmptyFieldErrorMessage().isDisplayed());
        Assert.assertTrue(registerPage.getExistingUserErrorMessage().getText().contains("fields empty"));
        log.info("Empty fields error message validated");
    }

    @DataProvider
    public Object[] getData() {
        Object[][] data = new Object[1][3];
        data[0][0] = "user55";
        data[0][1] = "password55";
        data[0][2] = "password55";

        return data;
    }

    @AfterMethod
    public void cleanUp() {
        driver.close();
        log.info("Driver closed");
    }
}
