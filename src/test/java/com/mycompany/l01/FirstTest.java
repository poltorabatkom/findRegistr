package com.mycompany.l01;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class FirstTest {
    WebDriver driver = null;
    String baseUrl = "https://www.google.ru/";
    String expectedTitle = "Intra";
    String actualTitle = "";
    String current_env = "firefox";

    @BeforeTest
    public void initBrowser() {
        if (current_env.equals("firefox")) {
            String pathToGeckoDriver = Paths.get("C:\\webdrivers\\geckodriver\\geckodriver.exe").toAbsolutePath().toString();
            System.setProperty("webdriver.gecko.driver", pathToGeckoDriver);
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }
        else if (current_env.equals("chrome")){
            System.setProperty("webdriver.chrome.driver","C:\\webdrivers\\chromedriver\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        else
        {
            System.exit(0);
        }
    }

    @Test
    public void runBrowser() {
        driver.get(baseUrl);
        actualTitle = driver.getTitle();
        driver.navigate().refresh();
        driver.navigate().back();
        driver.navigate().forward();
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }
}
