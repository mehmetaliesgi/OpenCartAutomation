package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{
    private final By txtEmailLocator = By.id("input-email");
    private final By txtPasswordLocator = By.id("input-password");
    private final By btnLoginLocator = By.xpath("//input[@value='Login']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void setEmail(String email) {
        driver.findElement(txtEmailLocator).sendKeys(email);
    }

    public void setPassword(String pass) {
        driver.findElement(txtPasswordLocator).sendKeys(pass);
    }

    public void clickLogin() {
        driver.findElement(btnLoginLocator).click();
    }

}
