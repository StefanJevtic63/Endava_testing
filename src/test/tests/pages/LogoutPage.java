import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage extends BasePage{

    public LogoutPage(WebDriver driver){
        super(driver);
    }

    By successfulLogOutMsg = By.xpath("//div[@class='alert alert-success text-center']");

    public void waitForSuccessfulLogoutMsg(){
        waitUntilElementIsVisible(successfulLogOutMsg);
    }
}
