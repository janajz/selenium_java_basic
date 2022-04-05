package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class Task2 {
    WebDriver driver;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:
//         check that all field are empty and no tick are clicked
//         "Don't know" is selected in "Genre"
//         "Choose your option" in "How do you like us?"
//         check that the button send is blue with white letters

        //Input fields
        assertEquals("", driver.findElement(By.id("fb_name")).getAttribute("value"));
        assertEquals("", driver.findElement(By.id("fb_age")).getAttribute("value"));
        //Check boxes
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));
        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected()); // checkboxes are NOT selected
            System.out.println(checkBox.getAttribute("value"));
        }
        //Radio buttons
        assertFalse("", driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[1]")).isSelected());
        assertFalse("", driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[2]")).isSelected());
        assertTrue("", driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[3]")).isSelected());
        //Option list
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());
        //Comment
        assertEquals("", driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[6]/textarea")).getAttribute("value"));
        // Send Button
        assertEquals("rgba(33, 150, 243, 1)", driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).getCssValue("color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
//         check fields are empty or null
//         check button colors
//         (green with white letter and red with white letters)
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).click();
        // Check all fields
        assertEquals("", driver.findElement(By.id("name")).getText());
        assertEquals("", driver.findElement(By.id("age")).getText());
        assertEquals("", driver.findElement(By.id("language")).getText());
        assertEquals("null", driver.findElement(By.id("gender")).getText());
        assertEquals("null", driver.findElement(By.id("option")).getText());
        assertEquals("", driver.findElement(By.id("comment")).getText());
        // Check buttons
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]")).getCssValue("color"));
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)
        //Input fields
        String name = "Jana JZ";
        String age = "39";
        String comment = "Everything is nice and good";
        driver.findElement(By.id("fb_name")).sendKeys(name);
        driver.findElement(By.id("fb_age")).sendKeys(age);
        //Check boxes
        driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[3]")).click();
        //Radio buttons
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[2]")).click();
        //Option list
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByIndex(1);
        //Comment
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[6]/textarea")).sendKeys(comment);
        // Send Button
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).click();
        Thread.sleep(3000);
        // Check all fields
        assertEquals(name, driver.findElement(By.id("name")).getText());
        assertEquals(age, driver.findElement(By.id("age")).getText());
        assertEquals("English,Spanish", driver.findElement(By.id("language")).getText());
        assertEquals("female", driver.findElement(By.id("gender")).getText());
        assertEquals("Good", driver.findElement(By.id("option")).getText());
        assertEquals(comment, driver.findElement(By.id("comment")).getText());
        // Check buttons
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]")).getCssValue("color"));
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background
        String name = "Jana JZ";
        driver.findElement(By.id("fb_name")).sendKeys(name);
        // Send Button
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).click();
        Thread.sleep(3000);
        // Yes Button
        driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).click();
        Thread.sleep(3000);
        // Thank You message
        String expectedMessage = "Thank you, " + name + ", for your feedback!";
        assertEquals(expectedMessage, driver.findElement(By.id("message")).getText());
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div")).getCssValue("color"));
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
//         click "Yes"
//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background
        // Send Button
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).click();
        Thread.sleep(3000);
        // Yes Button
        driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).click();
        Thread.sleep(3000);
        // Thank You message
        String expectedMessage = "Thank you for your feedback!";
        assertEquals(expectedMessage, driver.findElement(By.id("message")).getText());
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div")).getCssValue("color"));
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly
        //Input fields
        String name = "Jana JZ";
        String age = "39";
        String comment = "Everything is nice and good";
        driver.findElement(By.id("fb_name")).sendKeys(name);
        driver.findElement(By.id("fb_age")).sendKeys(age);
        //Check boxes
        driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[3]")).click();
        //Radio buttons
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[2]")).click();
        //Option list
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByIndex(1);
        //Comment
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[6]/textarea")).sendKeys(comment);
        // Send Button
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).click();
        Thread.sleep(3000);
        // No Button
        driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]")).click();
        Thread.sleep(3000);
        // Check all fields
        //Input fields
        assertEquals(name,driver.findElement(By.id("fb_name")).getAttribute("value"));
        assertEquals(age,driver.findElement(By.id("fb_age")).getAttribute("value"));
        //Check boxes
        assertTrue(driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[1]")).isSelected());
        assertTrue(driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[3]")).isSelected());
        //Radio buttons
        assertTrue(driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[2]")).isSelected());
        //Option list
        assertEquals("1", driver.findElement(By.id("like_us")).getAttribute("selectedIndex"));
        //Comment
        assertEquals(comment, driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[6]/textarea")).getAttribute("value"));
    }
}
