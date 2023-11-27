import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class Unsuccessful {

    @Test
    public void Test(){
        WebDriver driver = driver_setup();

        //Login
        username(driver);
        password(driver);
        login(driver);

        driver.quit();
    }

    private WebDriver driver_setup() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.saucedemo.com/");

        return driver;
    }

    private void username(WebDriver driver) {
        WebElement usernameFieldXPath =
                driver.findElement(By.xpath("//input[@type='text']"));

        usernameFieldXPath.sendKeys("username");
    }

    private void password(WebDriver driver) {
        WebElement usernameFieldCSS =
                driver.findElement(By.cssSelector("input[type='password']"));

        usernameFieldCSS.sendKeys("password");
    }

    private void login(WebDriver driver) {
        WebElement logInButtonCSS =
                driver.findElement(By.cssSelector("input[value='Login']"));

        logInButtonCSS.click();
    }
}