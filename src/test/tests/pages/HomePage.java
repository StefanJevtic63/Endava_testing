import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver){
        super(driver);
    }

    public static final String PAGE_TITLE = "Swag Labs";

    By logOutButton = By.id("logout_sidebar_link");


    public void clickLogoutButton(){
        driver.findElement(logOutButton).click();
    }
}