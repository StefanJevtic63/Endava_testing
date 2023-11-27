import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    public WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }


    public void getCurrentUrl(){
        String currentURL = driver.getCurrentUrl();
        System.out.println("CURRENT URL IS -> " + currentURL);
    }

    public void closeCurrentTab(){
        if (driver != null)
            driver.close();
    }

    public void quitBrowser(){
        if (driver != null)
            driver.quit();
    }

    public void waitUntilPageTitleIsCorrect(long seconds, String title){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.titleIs(title));
    }

    public void waitUntilElementIsVisible(By element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
}
