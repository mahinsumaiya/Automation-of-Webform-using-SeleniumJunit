import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utils.Utils;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class RegistrationForm {
    WebDriver driver;
    @BeforeAll
    public void setup(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofDays(30));
    }

    @DisplayName("Registration Successful!")
    @Test
    public void GuestFormAutomation() throws InterruptedException {
    driver.get("https://demo.wpeverest.com/user-registration/guest-registration-form/");
    Utils.scroll(driver, 500);
    List<WebElement> guestInfo = driver.findElements(By.className("ur-frontend-field"));
    guestInfo.get(0).sendKeys("Sumaiya");
    guestInfo.get(1).sendKeys("mahin32@gmail.com");
    guestInfo.get(2).sendKeys("GuEsT@$45568");
    guestInfo.get(3).sendKeys("Mahin");
    Thread.sleep(2000);

    //Gender
    List<WebElement> genderButton = driver.findElements(By.cssSelector("[type=radio]"));
    Actions actions = new Actions(driver);
    actions.click(genderButton.get(1)).perform();
    Thread.sleep(2000);

    //Date of Birth
    driver.findElements(By.className("flatpickr-input")).get(0).click();

    //month
    Select month = new Select(driver.findElement(By.className("flatpickr-monthDropdown-months")));
    month.selectByValue("0");
    Thread.sleep(1000);
    //year
    WebElement year = driver.findElement(By.className("cur-year"));
    year.click();
    year.sendKeys("1999");
    Thread.sleep(1000);
    //date
    WebElement date = driver.findElement(By.xpath("//span[contains(@class, 'flatpickr-day') and text()='24']"));
    date.click();

    //Nationality
    driver.findElement(By.id("input_box_1665629217")).sendKeys("Bangladeshi");

    //country
    Select select1 = new Select(driver.findElement(By.id("country_1665629257")));
    select1.selectByVisibleText("Bangladesh");
    Utils.scroll(driver,500);
    //PhoneNumber
    List<WebElement> phoneNumber = driver.findElements(By.id("phone_1665627880"));
    phoneNumber.get(1).sendKeys("01821111111");
    Thread.sleep(2000);
    Utils.scroll(driver, 1200);

    //Terms and Conditions
    driver.findElement(By.id("privacy_policy_1665633140")).click();

    //submitbutton
    List<WebElement> button = driver.findElements(By.cssSelector("[type=submit]"));
    button.get(2).click();

    //Assertion
    WebElement successMessage = driver.findElement(By.id("ur-submit-message-node"));
    String actualMessage = successMessage.getText();
    String expectedMessage = "User successfully registered.";

    System.out.println("Success message: " + actualMessage);

    assertEquals(expectedMessage, actualMessage);
    Thread.sleep(3000);
    }

@AfterAll
   public void finishTest() {
       driver.quit();
}


}





