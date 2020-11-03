package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RentPage {
    WebDriver driver;

    public RentPage(WebDriver driver) {
        this.driver = driver;
    }

    By rentItem = By.cssSelector("#add-rent-button");
    By customerName = By.xpath("//input[contains(@name,'customer')]");
    By submitRent = By.cssSelector("button[name='submit-button']");
    By edit = By.xpath("//button[contains(@class,'edit')]");
    By remove = By.xpath("//button[contains(@class,'remove')]");

    public WebElement createRent() {
        return driver.findElement(rentItem);
    }
    public WebElement enterCustomer() {
        return driver.findElement(customerName);
    }
    public WebElement submitRent() {
        return driver.findElement(submitRent);
    }
    public WebElement editItem() {
        return driver.findElement(edit);
    }
    public WebElement removeItem() {
        return driver.findElement(remove);
    }
}
