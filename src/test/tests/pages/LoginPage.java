import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver){
        super(driver);
    }

    // Many ways to access the field

    private By usernameFieldName =  By.name("username");

    private By usernameFieldID  = By.id("username");

    private By usernameFieldXpath = By.xpath("//input[@type='text']");

    private By passwordFieldCSS = By.cssSelector("input[type='password");

    private By loginButtonCSS = By.cssSelector("input[value='Login']");

    public void typeOnUsernameFieldName(String username){
        WebElement usernameField = driver.findElement(usernameFieldName);
        usernameField.sendKeys(username);
    }

    public void typeOnUsernameFieldID(String username){
        WebElement usernameField = driver.findElement(usernameFieldID);
        usernameField.sendKeys(username);
    }

    public void typeOnUsernameFieldXPath(String username){
        WebElement usernameField = driver.findElement(usernameFieldXpath);
        usernameField.sendKeys(username);
    }

    public void typeOnPasswordFieldCSS(String username){
        WebElement usernameField = driver.findElement(passwordFieldCSS);
        usernameField.sendKeys(username);
    }

    public void clickOnLoginButton(){
        WebElement logInButtonCSS = driver.findElement(loginButtonCSS);
        logInButtonCSS.click();
    }

    public boolean isLoginButtonVisible(){
        return driver.findElement(loginButtonCSS).isDisplayed();
    }
}