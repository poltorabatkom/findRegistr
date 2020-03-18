package com.mycompany.l01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class FindEement {
    private WebDriver driver;
    String current_env = "chrome";

    @BeforeTest
    public void initBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void InitialElement() {
        String userName = GenerateText(true).substring(0, 6);
        String password = GenerateText(true).substring(0, 15);
        //String password = "03894529ad4b4e7QWERTYaa";
        String email = GenerateText(false).substring(0, 7) + "@mail.ru";
        String firstname = GenerateText(true).substring(0, 6);
        String lastname = GenerateText(true).substring(0, 6);
        String url = "www." + GenerateText(true).substring(0, 6) + ".com";
//записываем все учетные данные
        System.out.println("userName: " + userName);
        System.out.println("password: " + password);
        System.out.println("email: " + email);
        System.out.println("firstname: " + firstname);
        System.out.println("lastname: " + lastname);
        System.out.println("url: " + url);
//входим под админом
        driver.get("http://cw07529-wordpress.tw1.ru/wp-admin/user-new.php");
        driver.findElement(By.xpath("//input[@id='user_login']")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@id='user_pass']")).sendKeys("kCb1hRpr");
        driver.findElement(By.xpath("//input[@id='wp-submit']")).click();
//заполняем форму для регистрации
        driver.findElement(By.xpath("//input[@id='user_login']")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='first_name']")).sendKeys(firstname);
        driver.findElement(By.xpath("//input[@id='last_name']")).sendKeys(lastname);
        driver.findElement(By.xpath("//input[@id='url']")).sendKeys(url);
        driver.findElement(By.xpath("//button[@class='button wp-generate-pw hide-if-no-js']")).click();
        driver.findElement(By.xpath("//button[@class='button wp-hide-pw hide-if-no-js']")).click();
        driver.findElement(By.xpath("//input[@id='pass1']")).clear();
        driver.findElement(By.xpath("//input[@id='pass1']")).sendKeys(password);
//ждем пока примется пароль
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='pass-strength-result' and contains(text(),'Надёжный')]")));
//клик по кнопке Создать
        driver.findElement(By.xpath("//input[@id='createusersub']")).click();
//выходим из учетки админ
        driver.get("http://cw07529-wordpress.tw1.ru/wp-login.php?action=logout&_wpnonce=f22c441145");
        driver.findElement(By.xpath("//p[text()='?']//a")).click();
//заходим с новым юзером
        driver.findElement(By.xpath("//input[@id='user_login']")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@id='user_pass']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='wp-submit']")).click();
    }

    public String GenerateText(boolean deffice) {
        return UUID.randomUUID().toString().replace("-", "");
    }


    @AfterTest
    public void stop() {
        driver.quit();
        driver = null;
    }
}
