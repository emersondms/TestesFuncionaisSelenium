package test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteGoogle {

    @Test
    public void test() {
        System.setProperty("webdriver.chrome.driver",
            System.getProperty("user.dir") + "/src/main/resources/chromedriver");
        
        WebDriver driver = new ChromeDriver();
        //driver.manage().window().setPosition(new Point(100, 100));
        //driver.manage().window().setSize(new Dimension(1200, 765));
        //driver.manage().window().maximize();
        driver.get("http://www.google.com");
        Assert.assertEquals("Google", driver.getTitle());
        //driver.close();
        driver.quit();
    }
}
