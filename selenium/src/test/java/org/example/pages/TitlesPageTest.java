package org.example.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.base.Base;
import org.example.utilities.TestUtil;
import org.graalvm.compiler.core.common.alloc.Trace;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TitlesPageTest extends Base {
    WebDriver driver;
    LoginPage loginPage;
    TitlesPage titles;
    public static Logger log = LogManager.getLogger(TitlesPageTest.class.getName());

    @BeforeTest
    public void setUp() throws IOException {
        driver = initializeDriver();
        log.info("Driver is initialized");
        driver.get(properties.getProperty("url"));
        log.info("Navigated to Login Page");
        loginPage = new LoginPage(driver);
        titles = loginPage.successfulLogin();
        log.info("User has successfully logged in");
    }

    @Test(dataProvider = "getData")
    public void createTitle(String title, String author, String year) throws InterruptedException {
        waitForElementToBeClickable(titles.addTitleBtn());
        titles.addTitleBtn().click();
        log.debug("Add Title button has been clicked");
        titles.enterTitle().sendKeys(title);
        titles.enterAuthor().sendKeys(author);
        titles.enterYear().sendKeys(year);
        titles.submitNewTitle().click();
        log.debug("New Title has been created");

        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, 10);

        List<WebElement> titlesList = titles.getTitlesList();
        int titlesNum = titlesList.size();
        Assert.assertTrue(titlesNum > 0);

        List<WebElement> titlesNames = titles.getTitlesNames();
        int titlesNamesNum = titlesNames.size();
        Assert.assertEquals(titlesNum, titlesNamesNum);
        log.debug("Titles list size and Titles' names number has been validated");

        String s = title.toUpperCase();
        boolean flag = titlesNames.stream()
                .map(WebElement::getText)
                .anyMatch(t -> t.equals(s));

        Assert.assertTrue(flag);
        log.debug("New Title addition to Title list has been validated");
    }

    @Test(dataProvider = "getData")
    public void showCopies(String title, String author, String year) {
        List<WebElement> titlesNames = titles.getTitlesNames();
        String s = title.toUpperCase();
        titlesNames.stream()
                .filter(t -> t.getText().equals(s))
                .findFirst()
                .get()
                .findElement((By.xpath("parent::div/following-sibling::div/a/button")))
                .click();
        log.info("Show copies button of a specyfic Title has been clicked on");

        ItemsPage items = new ItemsPage(driver);

        waitForVisibility(items.getTitleText());
        String text = items.getSubtitle().getText();
        Assert.assertEquals(text, "LIST OF COPIES");
        log.debug("Navigation to List of Items Page has been validated");

        String alert = items.getNoCopyMessage().getText();
        Assert.assertTrue(alert.contains("No copies"));
        items.getReturnButton().click();
        log.info("Return to Titles Page button has been clicked on");
        waitForVisibility(titles.getSubtitle());
        Assert.assertEquals(titles.getSubtitle().getText(), "TITLES CATALOG");
        log.debug("Returning to Titles Page validated");
    }

    @Test(dataProvider = "getData")
    public void editTitle(String title, String author, String year) {
        List<WebElement> titlesNames = titles.getTitlesNames();
        String s = title.toUpperCase();
        titlesNames.stream()
                .filter(t -> t.getText().equals(s))
                .findFirst()
                .get()
                .findElement((By.xpath("parent::div/following-sibling::div/button[1]")))
                .click();
        log.info("Edit button of specific Title has been clicked on");

        waitForVisibility(titles.getEditSubtitle());
        String text = titles.getEditSubtitle().getText();
        Assert.assertEquals(text, "EDIT TITLE");
        log.debug("Edit's view Title validated");

        titles.enterTitle().clear();
        titles.enterTitle().sendKeys("Changed title");
        titles.enterAuthor().clear();
        titles.enterAuthor().sendKeys("Changed author");
        titles.enterYear().clear();
        titles.enterYear().sendKeys("1999");
        log.info("Edition of title, author nad year");
        titles.submitNewTitle().click();
        log.info("Edit Title button has been clicked on");

        waitForVisibility(titles.getSubtitle());
        boolean flag = titlesNames.stream()
                .map(WebElement::getText)
                .anyMatch(t -> !t.equals(s));
        Assert.assertTrue(flag);
        log.debug("Edition of the Title validated");
    }

    @Test
    public void deleteTitle() {
        waitForVisibility(titles.deleteTitle());
        List<WebElement> titlesNames = titles.getTitlesNames();
        String changedTitle = "Changed title";
        titlesNames.stream()
                .filter(t -> t.getText().equals(changedTitle.toUpperCase()))
                .findFirst()
                .get()
                .findElement((By.xpath("parent::div/following-sibling::div/button[2]")))
                .click();
        log.info("Remove Button of the Title has been clicked on");

        boolean flag = titlesNames.stream()
                .map(WebElement::getText)
                .anyMatch(t -> !t.equals(changedTitle.toUpperCase()));
        Assert.assertTrue(flag);
        log.debug("Removing Title of the list has been validated");
    }

    @DataProvider
    public Object[] getData() {
        Object[][] data = new Object[1][3];
        data[0][0] = "Testing book";
        data[0][1] = "J.J. Morgan";
        data[0][2] = "2008";

        return data;
    }

    @AfterTest
    public void cleanUp() {
        driver.close();
        log.info("Driver closed");
    }
}
