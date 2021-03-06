package org.example.base;

import org.example.utilities.TestUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {
    public WebDriver driver;
    public Properties properties;

    public WebDriver initializeDriver() throws IOException {
        properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\org\\example\\base\\config.properties");
        properties.load(fileInputStream);
        String browserName = properties.getProperty("browser");

        if(browserName.contains("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\selenium-drivers\\chrome\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            if(browserName.contains("headless")) {
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
        }

        else if (browserName.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\selenium-drivers\\firefox\\geckodriver.exe");
            driver = new FirefoxDriver();
        }

        driver.manage().timeouts().pageLoadTimeout(TestUtil.WAIT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.WAIT, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;
    }

    public void waitForVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TestUtil.WAIT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TestUtil.WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
