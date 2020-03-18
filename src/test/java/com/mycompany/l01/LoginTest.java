package com.mycompany.l01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends FirstTest {
    @Test
    public void  loginNegative(){
        //user find
        WebElement username = driver.findElement(By.id("UserName"));
        username.sendKeys("mpoltora");
        //pass find
        WebElement userPassword = driver.findElement(By.xpath("//input[@id='Password']"));
        userPassword.sendKeys("invalidPass");
        //click to login button
        WebElement userClick = driver.findElement(By.xpath("//input[@id='logonButton']"));
        userPassword.sendKeys("click");

        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='validation-summary-errors']")).getText().contains("Ошибка входа. Пожалуйста, исправьте ошибки и повторите попытку."));
    }
}
