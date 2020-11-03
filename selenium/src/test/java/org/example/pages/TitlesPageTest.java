package org.example.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.base.Base;
import org.example.utilities.TestUtil;
import org.graalvm.compiler.core.common.alloc.Trace;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TitlesPageTest extends Base {
    WebDriver driver;
    public static Logger log = LogManager.getLogger(TitlesPageTest.class.getName());

    @BeforeMethod
    public void setUp() throws IOException {
        driver = initializeDriver();
        log.info("Driver is initialized");
        driver.get(properties.getProperty("url"));
        log.info("Navigated to Login Page");
    }

    @Test(dataProvider = "getData")
    public void createTitle(String title, String author, String year) {
        LoginPage loginPage = new LoginPage(driver);
        TitlesPage titles = loginPage.successfulLogin();
        log.info("User has successfully logged in");
        waitForElementToBeClickable(titles.addTitleBtn());
        titles.addTitleBtn().click();
        log.debug("Add Title button has been clicked");
        titles.enterTitle().sendKeys(title);
        titles.enterAuthor().sendKeys(author);
        titles.enterYear().sendKeys(year);
        titles.submitNewTitle().click();
        log.debug("New Title has been created");

        driver.manage().timeouts().implicitlyWait(TestUtil.WAIT, TimeUnit.SECONDS);
        List<WebElement> titlesList = titles.getTitlesList();
        int titlesNum = titlesList.size();
        Assert.assertTrue(titlesNum > 0);

        List<WebElement> titlesNames = titles.getTitlesNames();
        int titlesNamesNum = titlesNames.size();
        Assert.assertEquals(titlesNum, titlesNamesNum);
        log.debug("Titles list size and Titles' names number has been validated");

        String s = title.toUpperCase();
        boolean flag = titlesNames.stream().map(WebElement::getText).anyMatch(t -> t.equals(s));
        Assert.assertTrue(flag);
        log.debug("New Title addition to Title list has been validated");
    }

    @DataProvider
    public Object[] getData() {
        Object[][] data = new Object[1][3];
        data[0][0] = "Testing book";
        data[0][1] = "JJ.Morgan";
        data[0][2] = "2008";

        return data;
    }

    @AfterMethod
    public void cleanUp() {
        driver.close();
        log.info("Driver closed");
    }

}
