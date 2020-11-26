package org.example.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.base.Base;
import org.example.utilities.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ItemsPageTest extends Base {
    WebDriver driver;
    LoginPage loginPage;
    TitlesPage titles;
    ItemsPage itemsPage;
    public static Logger log = LogManager.getLogger(ItemsPageTest.class.getName());

    @BeforeMethod
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
    public void addCopyOfTitle(String month, String day, String date) throws InterruptedException {
        itemsPage = titles.clickOnShowCopiesBtn();
        List<WebElement> itemsList = itemsPage.itemsList();
        int numberOfCopies = itemsList.size();
        System.out.println(numberOfCopies);
        itemsPage.addItem().click();
        waitForVisibility(itemsPage.getAddCopySubtitle());
        String subtitle = itemsPage.getAddCopySubtitle().getText();
        Assert.assertEquals(subtitle, "Add copy");
        itemsPage.purchaseDateField().click();
        waitForVisibility(itemsPage.getCalendarMonth());
        while(!itemsPage.getCalendarMonth().getText().contains(month)) {
            itemsPage.getCalendarNextBtn().click();
        }
        waitForVisibility(itemsPage.getCalendarDay());
        List<WebElement>calendarDays = itemsPage.calendarDays();
        calendarDays.stream().filter(d -> d.getText().equals(day)).forEach(WebElement::click);
        itemsPage.submitNewItem().click();

        WebDriverWait wait = new WebDriverWait(driver, TestUtil.WAIT);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("ul.items-list.list"))));

        List<WebElement> actualItemsList = itemsPage.itemsList();
        int actualNumberOfCopies = actualItemsList.size();
        Assert.assertTrue(actualNumberOfCopies > numberOfCopies);

        List<WebElement> purchaseDates = itemsPage.purchaseDates();
        boolean flag = purchaseDates.stream().anyMatch(d-> d.getText().contains(date));
        if(flag) {
            log.debug("Displayed purchase date is correct");
        }
        else {
            log.error("Displayed purchase date is not correct");
        }

    }

    @AfterMethod
    public void cleanUp() {
        driver.close();
        log.info("Driver closed");
        driver.quit();
    }

    @DataProvider
    public Object[] getData() {
        Object[][] data = new Object[1][3];
        data[0][0] = "Apr 2020";
        data[0][1] = "15";
        data[0][2] = "04-15";

        return data;
    }

}
