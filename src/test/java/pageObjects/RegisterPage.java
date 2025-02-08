package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage{
    private final By txtFirstNameLocator = By.id("input-firstname");
    private final By txtLastNameLocator = By.id("input-lastname");
    private final By txtEmailLocator = By.id("input-email");
    private final By txtPhoneLocator = By.id("input-telephone");
    private final By txtPasswordLocator = By.id("input-password");
    private final By txtPasswordConfirmLocator = By.id("input-confirm");
    private final By cbPrivacyPolicyLocator = By.xpath("//input[@name='agree']");
    private final By btnContinueLocator = By.xpath("//input[@value='Continue']");
    private final By msgConfirmationLocator = By.xpath("//h1[normalize-space()='Your Account Has Been Created!']");
    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void setFirstName(String firstName) {
        driver.findElement(txtFirstNameLocator).sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        driver.findElement(txtLastNameLocator).sendKeys(lastName);
    }

    public void setEmail(String email){
        driver.findElement(txtEmailLocator).sendKeys(email);
    }

    public void setPhone(String phone) {
        driver.findElement(txtPhoneLocator).sendKeys(phone);
    }

    public void setPassword(String password) {
        driver.findElement(txtPasswordLocator).sendKeys(password);
    }

    public void setPasswordConfirm(String password) {
        driver.findElement(txtPasswordConfirmLocator).sendKeys(password);
    }

    public void setPrivacyPolicy() {
        driver.findElement(cbPrivacyPolicyLocator).click();
    }

    public void clickContinue() {
        driver.findElement(btnContinueLocator).click();
    }

    public String getConfirmationMessage() {
        try {
            return (driver.findElement(msgConfirmationLocator).getText());
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
