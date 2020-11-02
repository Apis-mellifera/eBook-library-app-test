package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {
    WebDriver driver;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    By addTitle = By.cssSelector("#add-title-button");
    By title = By.xpath("//*[@name='title']");
    By author = By.xpath("//*[@name='author']");
    By year = By.xpath("//*[@name='year']");
    By newTitle = By.xpath("//*[@name='submit-button']");
    By items = By.xpath("//button[contains(@class,'copies')]");
    By edit = By.xpath("//button[contains(@class,'edit')]");
    By remove = By.xpath("//button[contains(@class,'remove')]");

    public WebElement addTitle() {
        return driver.findElement(addTitle);
    }

    public WebElement enterTitle() {
        return driver.findElement(title);
    }

    public WebElement enterAuthor() {
        return driver.findElement(author);
    }

    public WebElement enterYear() {
        return driver.findElement(year);
    }

    public WebElement submitNewTitle() {
        return driver.findElement(newTitle);
    }

    public WebElement getItems() {
        return driver.findElement(items);
    }

    public WebElement editTitle() {
        return driver.findElement(edit);
    }

    public WebElement deleteTitle() {
        return driver.findElement(remove);
    }






}
