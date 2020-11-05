package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TitlesPage {
    WebDriver driver;

    public TitlesPage(WebDriver driver) {
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
    By titlesList = By.xpath("//li[contains(@id,'title')]");
    By titlesNames = By.xpath("//div[contains(@class,'primary')]");
    By subtitle = By.xpath("//*[text()= 'Titles catalog']");
    By editSubtitle = By.xpath("//div[@class='fog__content']/form/h2");

    public WebElement addTitleBtn() {
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

    public List<WebElement> getTitlesList() {
        return driver.findElements(titlesList);
    }

    public List<WebElement> getTitlesNames() {
        return driver.findElements(titlesNames);
    }

    public WebElement getEditSubtitle() {
        return driver.findElement(editSubtitle);
    }

    public WebElement getSubtitle() {
        return driver.findElement(subtitle);
    }
}
