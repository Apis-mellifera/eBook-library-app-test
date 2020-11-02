package org.example.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class TitlesPageTest extends Base {
    WebDriver driver;
   // public static Logger log = LogManager.getLogger(TitlesPageTest.class.getName());

    @BeforeMethod
    public void setUp() throws IOException {
        driver = initializeDriver();
       // log.info("Driver is initialized");
        driver.get(properties.getProperty("url"));
       // log.info("Navigated to Login Page");
    }

    @Test
    public void createTitle() {
        LoginPage loginPage = new LoginPage(driver);
        TitlesPage titles = loginPage.successfulLogin();
        waitForElementToBeClickable(titles.addTitleBtn());
        titles.addTitleBtn().click();
        titles.enterAuthor().sendKeys("Testing book");
        titles.enterTitle().sendKeys("JJ.Morgan");
        titles.enterYear().sendKeys("2008");
        titles.submitNewTitle().click();
        List<WebElement> count = driver.findElements(By.xpath("li[contains(@id='title')]"));
        System.out.println(count);
    }

}
