package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Task1 {
    WebDriver driver;

    @Before
    public void openPage() {

        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/enter_a_number");
    }

    @After
    public void closeBrowser() throws Exception {
        //Sleep for 3 seconds
        Thread.sleep(3000);
        driver.close();
    }

    @Test
    public void errorOnText() {
//        TODO
//        enter a text instead of a number, check that correct error is seen
        String txtString = "Text instead of number";
        String expErrMessage = "Please enter a number";

        // checking that Entry Field is empty
        WebElement numArea = driver.findElement(By.id("numb"));
        assertEquals(numArea.getText(), "");
        assertEquals(numArea.getAttribute("value"), "");

        // Insert text value
        numArea.sendKeys(txtString);

        // checking that getText has "Text instead of number"
        assertEquals(numArea.getAttribute("value"), txtString);

        // Find "Submit" button
        WebElement submitButton = driver.findElement(By.tagName("BUTTON"));
        System.out.println("Submit Button GetText: " + submitButton.getText());
        System.out.println("Submit Button TagName: " + submitButton.getTagName());
        submitButton.click();

        // Find Error message, check that Error message is as expected
        WebElement errMessage = driver.findElement(By.id("ch1_error"));
        System.out.println("Error Text GetText: " + errMessage.getText());
        assertEquals(errMessage.getText(), expErrMessage);
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//        enter number which is too small (below 50), check that correct error is seen
        String txtString = "10";
        String expErrMessage = "Number is too small";

        // checking that Entry Field is empty
        WebElement numArea = driver.findElement(By.id("numb"));
        assertEquals(numArea.getText(), "");
        assertEquals(numArea.getAttribute("value"), "");

        // Insert text value
        numArea.sendKeys(txtString);

        // checking that getText has "Text instead of number"
        assertEquals(numArea.getAttribute("value"), txtString);

        // Find "Submit" button
        WebElement submitButton = driver.findElement(By.tagName("BUTTON"));
        submitButton.click();

        // Find Error message, check that Error message is as expected
        WebElement errMessage = driver.findElement(By.id("ch1_error"));
        System.out.println("Error Text GetText: " + errMessage.getText());
        assertEquals(errMessage.getText(), expErrMessage);
    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        String txtString = "101";
        String expErrMessage = "Number is too big";

        // checking that Entry Field is empty
        WebElement numArea = driver.findElement(By.id("numb"));
        assertEquals(numArea.getText(), "");
        assertEquals(numArea.getAttribute("value"), "");

        // Insert Too Big value
        numArea.sendKeys(txtString);

        // checking that getText has "Number is too big"
        assertEquals(numArea.getAttribute("value"), txtString);

        // Find "Submit" button
        WebElement submitButton = driver.findElement(By.tagName("BUTTON"));
        submitButton.click();

        // Find Error message, check that Error message is as expected
        WebElement errMessage = driver.findElement(By.id("ch1_error"));
        System.out.println("Error Text GetText: " + errMessage.getText());
        assertEquals(errMessage.getText(), expErrMessage);
    }

    @Test
    public void errorOnNumberTooBigBug666() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        String txtString = "666";
        String expErrMessage = "Number is too big";

        // checking that Entry Field is empty
        WebElement numArea = driver.findElement(By.id("numb"));
        assertEquals(numArea.getText(), "");
        assertEquals(numArea.getAttribute("value"), "");

        // Insert Too Big value
        numArea.sendKeys(txtString);

        // checking that getText has "666" value
        assertEquals(txtString, numArea.getAttribute("value"));

        // Find "Submit" button
        WebElement submitButton = driver.findElement(By.tagName("BUTTON"));
        submitButton.click();

        // Find Error message, check that Error message is as expected
        WebElement errMessage = driver.findElement(By.id("ch1_error"));
        System.out.println("Error Text GetText: " + errMessage.getText());
        assertEquals(expErrMessage, errMessage.getText());
    }

    @Test
    public void correctSquareRootWithoutRemainder() throws InterruptedException {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
        String txtString = "64";
        String expErrMessage = "";
        String expResult = "Square root of 64 is 8.00";

        // checking that Entry Field is empty
        WebElement numArea = driver.findElement(By.id("numb"));
        assertEquals(numArea.getText(), "");
        assertEquals(numArea.getAttribute("value"), "");

        // Insert Numeric value
        numArea.sendKeys(txtString);

        // checking that getText has "64" value
        assertEquals(numArea.getAttribute("value"), txtString);

        // Find "Submit" button
        WebElement submitButton = driver.findElement(By.tagName("BUTTON"));
        submitButton.click();

        Alert alert = driver.switchTo().alert();
        assertEquals(expResult, alert.getText());
        Thread.sleep(3000);
        alert.accept();

        // Find Error message, check that Error message is as expected
        WebElement errMessage = driver.findElement(By.id("ch1_error"));
        System.out.println("Error Text GetText: " + errMessage.getText());
        assertEquals(errMessage.getText(), expErrMessage);
    }

    @Test
    public void correctSquareRootWithRemainder() throws InterruptedException {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly
        String txtString = "90";
        String expErrMessage = "";
        String expResult = "Square root of 90 is 9.49";

        // checking that Entry Field is empty
        WebElement numArea = driver.findElement(By.id("numb"));
        assertEquals(numArea.getText(), "");
        assertEquals(numArea.getAttribute("value"), "");

        // Insert Numeric value
        numArea.sendKeys(txtString);

        // checking that getText has "90" value
        assertEquals(numArea.getAttribute("value"), txtString);

        // Find "Submit" button
        WebElement submitButton = driver.findElement(By.tagName("BUTTON"));
        submitButton.click();

        Alert alert = driver.switchTo().alert();
        assertEquals(expResult, alert.getText());
        Thread.sleep(3000);
        alert.accept();

        // Find Error message, check that Error message is as expected
        WebElement errMessage = driver.findElement(By.id("ch1_error"));
        System.out.println("Error Text GetText: " + errMessage.getText());
        assertEquals(errMessage.getText(), expErrMessage);
    }
}
