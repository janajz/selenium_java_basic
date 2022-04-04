package selenium.sample.extra;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

import javax.swing.*;
import java.io.File;

public class extra1Task {
    WebDriver driver;
    String base_url = "https://kristinek.github.io/site/examples/act";
    String new_url = "https://kristinek.github.io/site/examples/link1";
    String po_url = "https://kristinek.github.io/site/examples/po";
    String po1_url = "https://kristinek.github.io/site/examples/po1";

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        // declaration above:
        driver = new ChromeDriver();

        //open page:
        driver.get(base_url);
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.close();
    }

    @Test
    public void navigateBack() throws Exception {
//        TODO
//        open page with url "https://kristinek.github.io/site/examples/po"
//        click "More > " for the top left element
//        check that the url now "https://kristinek.github.io/site/examples/po1"
//        using driver navigation go back to "https://kristinek.github.io/site/examples/po"
//        check that the page now is "https://kristinek.github.io/site/examples/po"
        driver.get(po_url);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[text()='More >> ']")).click();
        Thread.sleep(2000);
        assertEquals(po1_url, driver.getCurrentUrl());
        driver.navigate().back();
        Thread.sleep(2000);
        assertEquals(po_url, driver.getCurrentUrl());
    }

    @Test
    public void navigateForward() throws Exception {
//        TODO
//        open page with url "https://kristinek.github.io/site/examples/po"
//        click "More > " for the top left element
//        using driver navigation go back to "https://kristinek.github.io/site/examples/po"
//        using driver navigation go forward to "https://kristinek.github.io/site/examples/po1"
//        check that the page now is "https://kristinek.github.io/site/examples/po1"
        driver.get(po_url);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[text()='More >> ']")).click();
        Thread.sleep(2000);
        driver.navigate().back();
        Thread.sleep(2000);
        driver.navigate().forward();
        Thread.sleep(2000);
        assertEquals(po1_url, driver.getCurrentUrl());


    }

    @Test
    public void refresh() throws Exception {
//        TODO
//        open page "https://kristinek.github.io/site/examples/act"
//        click on "Show" button in 'Button' section
//        check that text "I am here!" is seen
//        refresh page
//        check that text "I am here!" is not seen
        driver.get(base_url);
        Thread.sleep(2000);
        driver.findElement(By.id("show_text")).click();
        Thread.sleep(2000);
        assertTrue(driver.findElement(By.id("show_me")).isDisplayed());
        driver.navigate().refresh();
        Thread.sleep(2000);
        assertFalse(driver.findElement(By.id("show_me")).isDisplayed());
    }
}
