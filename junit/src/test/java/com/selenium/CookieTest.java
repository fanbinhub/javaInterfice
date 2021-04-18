package com.selenium;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/*
* 用于cookie操作：
* 1.保存cookie
* 2.获取保存的cookie信息实现登录，不需要使用密码，扫码等操作
* */
public class CookieTest {
    public static WebDriver driver;
    @BeforeAll
    public static void intData (){
        driver = new ChromeDriver();//获取chrome驱动
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);//设置隐式等待时长
    }

    @AfterAll
    public static void end(){
//        driver.close();
    }

    /*
    * 登录并保存cookie信息
    * */
    @Test
    public void saveCookiesTest(){
        try {
        driver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx?from=myhome");
        Thread.sleep(1000);
        driver.navigate().refresh();//刷新缓存信息
            Set<Cookie> cookies = driver.manage().getCookies();//获取cookie信息
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            //将cookie信息写入到指定文件
            objectMapper.writeValue(new File("cookie.yaml"),cookies);

            cookies.forEach(cookie -> System.out.println(
                    cookie.getName()+":"+cookie.getValue()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * 利用cookie信息实现扫码登录，不需要再进行扫码。
    * */
    @Test
    public void loginCookieTest(){
        try {
            driver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx?from=myhome");
            ObjectMapper objectMapper=new ObjectMapper();
            objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//            objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

            //yaml文件格式存在List中（泛型结构需要进行理解）
            TypeReference<List<HashMap<String,Object>>> typeReference = new
                    TypeReference<List<HashMap<String, Object>>>(){} ;

            List<HashMap<String,Object>> cookies = objectMapper.
                    readValue(new File("cookie.yaml"),typeReference);

            cookies.forEach(cookie ->{
                System.out.println("打印获取到的cookie信息："+cookie.get("name").toString()
                + cookie.get("value").toString());
                driver.manage().addCookie(new Cookie(cookie.get("name").toString(),
                        cookie.get("value").toString()));
            });

            driver.navigate().refresh();

        Thread.sleep(1000);
        driver.findElement(By.id("menu_contacts")).click();
        driver.findElement(By.id("memberSearchInput")).sendKeys("核心");
        driver.findElement(By.xpath("//*[@id='search_party_list']/li")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@class='member_colRight_cnt_operation']/a[1]")).click();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
