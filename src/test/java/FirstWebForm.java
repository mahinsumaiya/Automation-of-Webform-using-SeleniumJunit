
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;


import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class FirstWebForm {

    WebDriver driver;

    @BeforeAll
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
    }

    @DisplayName("Visit Url and check if title is showing properly")
    @Test

    public void visitUrl() {
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        String currentUrl = driver.getCurrentUrl();
        String actualResult = driver.getTitle();
        String expectedResult = "Practice webform for learners | Digital Unite";
        assertEquals(actualResult, expectedResult);
        Assertions.assertTrue(currentUrl.contains("practice-webform-learners"));

    }

    @DisplayName("Check if success message is asserted or not")
    @Test
    public void formAutomation() throws InterruptedException {

        driver.get("https://www.digitalunite.com/practice-webform-learners");
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        Utils.scroll(driver, 500);
        List<WebElement> formInfo = driver.findElements(By.className("form-control"));
        formInfo.get(0).sendKeys("Test User");
        formInfo.get(1).sendKeys("01820000000");
        Thread.sleep(2000);
        WebElement txtCalendar = driver.findElement(By.id("edit-date"));
        txtCalendar.sendKeys(Keys.CONTROL, "a");
        txtCalendar.sendKeys(Keys.BACK_SPACE);
        txtCalendar.sendKeys("10/14/2024");

        driver.findElement(By.id("edit-email")).sendKeys("testuser@gmail.com");
        driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-")).sendKeys("Hi,I am Mahin");

        String relativePath = "\\src\\test\\resources\\image.png";
        String absolutePath = System.getProperty("user.dir")+relativePath;
        driver.findElement(By.id("edit-uploadocument-upload")).sendKeys(absolutePath);
        Thread.sleep(5000);

        Utils.scroll(driver, 500);
        driver.findElement(By.id("edit-age")).click();
        driver.findElement(By.id("edit-submit")).click();

        WebElement successMessage = driver.findElement(By.id("block-pagetitle-2"));

        String actualMessage = successMessage.getText();
        String expectedMessage = "Thank you for your submission!";
        System.out.println("Success message displayed!" +actualMessage);
        assertEquals(expectedMessage,actualMessage);



    }


@AfterAll
    public void finishTest(){
        driver.quit();

}
}




