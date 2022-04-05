package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

public class Sample7Task {
    WebDriver driver;
    String base_url = "https://kristinek.github.io/site/examples/actions";

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
    public void selectCheckBox() throws Exception {
//         TODO:
//        check that none of the checkboxes are ticked
//        tick  "Option 2"
//        check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
//        tick  "Option 3"
//        click result
//        check that text 'You selected value(s): Option 2, Option 3' is being displayed
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));
        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected()); // checkboxes are NOT selected
        }
        WebElement Option1 = driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='checkbox']"));
        WebElement Option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='checkbox']"));
        WebElement Option3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='checkbox']"));
        //Click Option2
        Option2.click();
        assertFalse(Option1.isSelected());
        assertTrue(Option2.isSelected());
        assertFalse(Option3.isSelected());
        //Click Option3
        Option3.click();
        //Click Result button
        driver.findElement(By.id("result_button_checkbox")).click();
        assertEquals("You selected value(s): Option 2, Option 3", driver.findElement(By.id("result_checkbox")).getText());
        Thread.sleep(2000);
    }


    @Test
    public void selectRadioButton() throws Exception {
//         TODO:
//        check that none of the radio are selected
//        select  "Option 3"
//        check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
//        select  "Option 1"
//        check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
//        click result
//        check that 'You selected option: Option 1' text is being displayed
        List<WebElement> radioBoxes = driver.findElements(By.cssSelector(".w3-check[type='radio']"));
        for (WebElement checkBox : radioBoxes) {
            assertFalse(checkBox.isSelected()); // checkboxes are NOT selected
        }
        WebElement Option1 = driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='radio']"));
        WebElement Option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='radio']"));
        WebElement Option3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='radio']"));
        //Click Option3
        Option3.click();
        assertFalse(Option1.isSelected());
        assertFalse(Option2.isSelected());
        assertTrue(Option3.isSelected());
        //Click Option1
        Option1.click();
        assertTrue(Option1.isSelected());
        assertFalse(Option2.isSelected());
        assertFalse(Option3.isSelected());
        //Click Result button
        driver.findElement(By.id("result_button_ratio")).click();
        assertEquals("You selected option: Option 1", driver.findElement(By.id("result_radio")).getText());
        Thread.sleep(2000);
    }

    @Test
    public void selectOption() throws Exception {
//        select "Option 3" in Select
//        check that selected option is "Option 3"
//        select "Option 2" in Select
//        check that selected option is "Option 2"
//        click result
//        check that 'You selected option: Option 2' text is being displayed
        Select dropdown = new Select(driver.findElement(By.id("vfb-12")));
        dropdown.selectByValue("value3");
        assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());
        dropdown.selectByValue("value2");
        assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());
        //Click Result button
        driver.findElement(By.id("result_button_select")).click();
        assertEquals("You selected option: Option 2", driver.findElement(By.id("result_select")).getText());
        Thread.sleep(2000);
    }

    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
//        enter date '4 of July 2007' using calendar widget
//        check that correct date is added
        String dateToEnter = "08/01/2007";
        String targetDate = "07/04/2007";

        //Enter initial date in Date box
        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        dateBox.click();
        dateBox.sendKeys(dateToEnter);

        //Go to previous month in Calendar widget
        WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));
        dateWidget.findElement(By.className("ui-datepicker-prev")).click();
        //select date 4
        dateWidget.findElement(By.xpath("//a[text()='4']")).click();
        assertEquals(targetDate, dateBox.getAttribute("value"));
        Thread.sleep(3000);
        //Click Result button
        driver.findElement(By.id("result_button_date")).click();
        assertEquals("You entered date: 07/04/2007", driver.findElement(By.id("result_date")).getText());
        Thread.sleep(2000);
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//        enter date '2 of May 1959' using text
//        check that correct date is added
        String dateToEnter = "05/02/1959";

        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        assertEquals("", dateBox.getAttribute("value"));

        Thread.sleep(2000);
        dateBox.clear();
        dateBox.sendKeys(dateToEnter);
        assertEquals(dateToEnter, dateBox.getAttribute("value"));
        Thread.sleep(3000);

        //Click Result button
        dateBox.sendKeys(Keys.ESCAPE);
        driver.findElement(By.id("result_button_date")).click();
        assertEquals("You entered date: 05/02/1959", driver.findElement(By.id("result_date")).getText());
        Thread.sleep(2000);
    }
}
