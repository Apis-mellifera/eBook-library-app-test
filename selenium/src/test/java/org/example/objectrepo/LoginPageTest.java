package org.example.objectrepo;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LoginPageTest {

    @Test
    public void e2eTestPositivePath() {
        //given
        System.setProperty("webdriver.gecko.driver", "c:\\selenium-drivers\\firefox\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://ta-ebookrental-fe.herokuapp.com");
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        LoginPage loginPage = new LoginPage(driver);
        Title title = new Title(driver);
        Item item = new Item(driver);
        Rent rent = new Rent(driver);

        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.username));
        loginPage.login().sendKeys("user55");
        loginPage.password().sendKeys("password55");
        loginPage.submit().click();

        wait.until(ExpectedConditions.elementToBeClickable(title.addTitle));
        title.addTitle().click();
        title.enterAuthor().sendKeys("Testing book");
        title.enterTitle().sendKeys("JJ.Morgan");
        title.enterYear().sendKeys("2008");
        title.submitNewTitle().click();

        wait.until(ExpectedConditions.elementToBeClickable(title.items));
        title.getItems().click();

        wait.until(ExpectedConditions.elementToBeClickable(item.addItem));
        item.addItem().click();

        wait.until(ExpectedConditions.elementToBeClickable(item.purchaseDate));
        item.purchaseDateField().click();
        List<WebElement> dates = driver.findElements(item.day);
        int count = driver.findElements(item.day).size();

        for (int i = 0; i< count ; i++) {
            String text = driver.findElements(item.day).get(i).getText();
            if(text.equalsIgnoreCase("15")) {
                driver.findElements(item.day).get(i).click();
                break;
            }
        }

        wait.until(ExpectedConditions.elementToBeClickable(item.newItem));
        item.submitNewItem().click();

        wait.until(ExpectedConditions.elementToBeClickable(item.history));
        item.getItemsList().click();

        wait.until(ExpectedConditions.elementToBeClickable(rent.rentItem));
        rent.createRent().click();
        rent.enterCustomer().sendKeys("J.Doe");
        rent.submitRent().click();
    }
}