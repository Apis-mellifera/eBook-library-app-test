package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Item {
    WebDriver driver;

    public Item(WebDriver driver) {
        this.driver = driver;
    }

    By addItem = By.cssSelector("#add-item-button");
    By purchaseDate = By.cssSelector("#id");
    By day = By.cssSelector("span[class*='day']");
    By newItem = By.cssSelector("button[name='submit-button']");
    By history = By.xpath("//button[contains(@class,'rents')]");
    By edit = By.xpath("//button[contains(@class,'edit')]");
    By remove = By.xpath("//button[contains(@class,'remove')]");

    public WebElement addItem() {
        return driver.findElement(addItem);
    }
    public WebElement purchaseDateField() {
        return driver.findElement(purchaseDate);
    }
    public WebElement selectDay() {
        return driver.findElement(day);
    }
    public WebElement submitNewItem() {
        return driver.findElement(newItem);
    }
    public WebElement getItemsList() {
        return driver.findElement(history);
    }
    public WebElement editItem() {
        return driver.findElement(edit);
    }
    public WebElement removeItem() {
        return driver.findElement(remove);
    }
}
