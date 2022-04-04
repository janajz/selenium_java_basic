package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertEquals;

import java.io.File;

public class Sample6Task {
    WebDriver driver;

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        // declaration above:
        driver = new ChromeDriver();
        //open page:
        driver.get("https://kristinek.github.io/site/examples/locators");
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.close();
    }

    @Test
    public void findElementByXPath() throws Exception {
//         TODO:
//        2 ways to find text: "Heading 2 text":
//        1-2 ways to find text: "Test Text 1"
//        1-2 ways to find text: "Test Text 2"
//        1-2 ways to find text: "Test Text 3"
//        1-2 ways to find text: "Test Text 4"
//        1-2 ways to find text: "Test Text 5"
//        1-2 ways to find text: "This is also a button"
        System.out.println("Find element Heading 2 text by id using xPath :");
        System.out.println("\t text of element with id 'heading_2' is '" +
                        driver.findElement(By.xpath("//*[@id='heading_2']")).getText() + "'");
        System.out.println("\t text of element with id 'standartText' is '" +
                driver.findElement(By.xpath("//*[@id='standartText']")).getText() + "'");
        System.out.println("\t text of element with id 'nonStandartText' is '" +
                driver.findElement(By.xpath("//*[@id='nonStandartText']")).getText() + "'");

//        find element by id using CSS
        System.out.println("Find element by id using CSS:");
        System.out.println("\t text of element with id 'heading_2' is '" +
                driver.findElement(By.cssSelector("#heading_2")).getText() + "'");
        System.out.println("\t text of element with id 'standartText' is '" +
                driver.findElement(By.cssSelector("#standartText")).getText() + "'");
        System.out.println("\t text of element with id 'nonStandartText' is '" +
                driver.findElement(By.cssSelector("#nonStandartText")).getText() + "'");


    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//        1-2 ways to find text: "Heading 2 text"
//        1-2 ways to find text: "Test Text 1"
//        1-2 ways to find text: "Test Text 2"
//        1-2 ways to find text: "Test Text 3"
//        1-2 ways to find text: "This is also a button"

        String heading2 = "Heading 2 text";
        assertEquals(heading2, driver.findElement(By.cssSelector("#heading_2")).getText());

        String testText1 = "Test Text 1";
        assertEquals(testText1, driver.findElement(By.cssSelector(".test")).getText());

        String testText2 = "Test Text 2";
        assertEquals(testText2, driver.findElement(By.cssSelector(".twoTest")).getText());

        String testText3 = "Test Text 3";
        assertEquals(testText3, driver.findElements(By.cssSelector("#test3 > .test")).get(0).getText());

        String button2 = "This is also a button";
        assertEquals(button2, driver.findElement(By.cssSelector("#buttonId")).getAttribute("value"));
        assertEquals(button2, driver.findElement(By.cssSelector("[name=\"randomButton2\"]")).getAttribute("value"));





    }
}
