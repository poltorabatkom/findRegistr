package com.mycompany.l01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.nio.file.Paths;


public class MyTest {
    private WebDriver driver;
    String current_env = "chrome";

    @BeforeTest
    public void initBrowser() {

            System.setProperty("webdriver.chrome.driver","C:\\webdrivers\\chromedriver\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();

//            driver.get(baseUrl);
    }

    @Test
    public void InitialElement() {
        driver = new ChromeDriver();
        driver.get("http://cw07529-wordpress.tw1.ru/my-account/");
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("Test1");

        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Studen123456!");

        driver.findElement(By.xpath("//button[@name='login']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='validation-summary-errors']")).getText().contains("Ошибка входа. Пожалуйста, исправьте ошибки и повторите попытку."));
    }

    @AfterTest
    public void stop() {
        driver.quit();
        driver = null;
    }
}
