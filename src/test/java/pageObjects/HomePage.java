package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{
    private final By btnMyAccountLocator = By.xpath("//span[text()='My Account']");
    private final By btnRegisterLocator = By.xpath("//a[text()='Register']");
    private final By btnLoginLocator = By.xpath("//a[text()='Login']");
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickMyAccount() {
        driver.findElement(btnMyAccountLocator).click();
    }

    public void clickRegister() {
        driver.findElement(btnRegisterLocator).click();
    }

    public void clickLogin() { driver.findElement(btnLoginLocator).click(); }
}
