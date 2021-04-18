package com.selenium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class seleniumTest {
    public static WebDriver driver;
    @BeforeAll
    public static void intData (){
        driver = new ChromeDriver();//获取chrome驱动
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);//设置隐式等待时长
    }

    @AfterAll
    public static void end(){
        driver.close();
    }

    /*
    * 登录收索演练
    * */
    @Test
    public void selenuim(){
        driver.get("https://home.testing-studio.com"); //通过驱动方法加载网页
        driver.findElement(By.id("details-button")).click();
        driver.findElement(By.id("proceed-link")).click();
        driver.findElement(By.xpath("//span[contains(text(),'登录')]")).click();
        driver.findElement(By.id("login-account-name")).clear();//清空元素内容
        driver.findElement(By.id("login-account-name")).sendKeys("fanbin");
        driver.findElement(By.id("login-account-password")).sendKeys("91625bfbf");
        driver.findElement(By.id("login-button")).click();//

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //搜索框获取
        driver.findElements(By.id("search-button")).get(0).click();
        driver.findElement(By.id("search-term")).sendKeys("企业微信");
        driver.findElement(By.id("search-term")).sendKeys(Keys.ENTER);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //里面内容是前端生成的，定位时根据层级关系进行处理找到内容是“企业微信这个地方”
        driver.findElement(By.xpath("//*[@class='search-results']/div/div/div[5]//span[1]")).click();

    }


}
