package org.example.demo.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DemoTest {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "c:\\selenium-drivers\\chrome\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://ta-ebookrental-fe.herokuapp.com");
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.findElement(By.id("register-btn")).click();

        Thread.sleep(3000);

        String currentWindowHandle = driver.getWindowHandle();
        System.out.println(currentWindowHandle);
        driver.switchTo().window(currentWindowHandle);


        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

        WebDriverWait wait = new WebDriverWait(driver, 5);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#login")));
        List<WebElement> titles = driver.findElements(By.className("sub-title"));
        int num = titles.size();
        System.out.println(num);
        String title = driver.findElement(By.className("sub-title")).getText();
        System.out.println(title);

        //login a user
        WebElement loginField = driver.findElement(By.cssSelector("#login"));
        loginField.sendKeys("user55");
        driver.findElement(By.cssSelector("#password")).sendKeys("password55");
        driver.findElement(By.cssSelector("#password-repeat")).sendKeys("password55");

        WebElement registerButton = driver.findElement(By.cssSelector("#register-btn"));
        registerButton.click();

        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);

        String errMsg = driver.findElement(By.className("alert__content")).getText();
        System.out.println(errMsg);

        driver.close();
        driver.quit();

        // Add Title
        /*
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#add-title-button")));
        driver.findElement(By.cssSelector("#add-title-button")).click();

        driver.findElement(By.xpath("//*[@name='title']")).sendKeys("Testing book");
        driver.findElement(By.xpath("//*[@name='author']")).sendKeys("JJ.Gordon");
        driver.findElement(By.xpath("//*[@name='year']")).sendKeys("2008");
        driver.findElement(By.xpath("//*[@name='submit-button']")).click();

        //Add item: 1) Get Items
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'copies')]")));
        //driver.findElement(By.xpath("//*[@id='titles']ul/li/div[2]/a/button")).click();
        driver.findElement(By.xpath("//button[contains(@class,'copies')]")).click();

        // 2) Add Item
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#add-item-button")));
        driver.findElement(By.cssSelector("#add-item-button")).click();

        // 3) Input purchase date
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#id")));
        driver.findElement(By.cssSelector("#id")).click();

        List<WebElement>dates = driver.findElements(By.cssSelector("span[class*='day']"));
        int count = driver.findElements(By.cssSelector("span[class*='day']")).size();

        for (int i = 0; i< count ; i++) {
            String text = driver.findElements(By.cssSelector("span[class*='day']")).get(i).getText();
            if(text.equalsIgnoreCase("15")) {
                driver.findElements(By.cssSelector("span[class*='day']")).get(i).click();
                break;
            }
        }

        //submit data
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[name='submit-button']")));
        driver.findElement(By.cssSelector("button[name='submit-button']")).click();

        //get item's list
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'rents')]")));
        driver.findElement(By.xpath("//button[contains(@class,'rents')]")).click();

        // create rental
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#add-rent-button")));
        driver.findElement(By.cssSelector("#add-rent-button")).click();

        //submit name
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@name,'customer')]")));
        WebElement inputName = driver.findElement(By.xpath("//input[contains(@name,'customer')]"));
        inputName.sendKeys("H.Morgan");

        //confirm rental
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[name='submit-button']")));
        driver.findElement(By.cssSelector("button[name='submit-button']")).click();

         */
    }
}
