import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class Successful {

    @Test
    public void Test() {
        WebDriver driver = driver_setup();

        get_current_url(driver);

        //Login
        username(driver);
        password(driver);
        login(driver);

        wait(driver);
        get_current_url(driver);

        menu(driver);

        //Logout
        logout(driver);
        get_current_url(driver);
        driver.close();
    }

    private WebDriver driver_setup() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.saucedemo.com/");

        return driver;
    }

    private void get_current_url(WebDriver driver) {
        String currentURL = driver.getCurrentUrl();
        System.out.println("CURRENT URL: " + currentURL);
    }

    private void username(WebDriver driver) {
        WebElement usernameFieldXPath =
                driver.findElement(By.xpath("//input[@type='text']"));

        usernameFieldXPath.sendKeys("standard_user");
    }

    private void password(WebDriver driver) {
        WebElement usernameFieldCSS =
                driver.findElement(By.cssSelector("input[type='password']"));

        usernameFieldCSS.sendKeys("secret_sauce");
    }

    private void login(WebDriver driver) {
        WebElement logInButtonCSS =
                driver.findElement(By.cssSelector("input[value='Login']"));

        logInButtonCSS.click();
    }

    private void wait(WebDriver driver) {
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs("Swag Labs"));
    }

    private void menu(WebDriver driver) {
        WebElement menu = driver.findElement((By.id("react-burger-menu-btn")));
        menu.click();
    }

    private void logout(WebDriver driver) {
        WebElement logOutButton =
                driver.findElement(By.id("logout_sidebar_link"));

        logOutButton.click();
    }
}