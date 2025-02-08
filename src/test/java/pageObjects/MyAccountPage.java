package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends BasePage{
    private final By msgHeading = By.xpath("//h2[text()='My Account']");
    private final By btnLogoutLocator = By.xpath("//div[@class='list-group']/a[text()='Logout']");

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public boolean isMyAccountPageExist() {
        try {
            return (driver.findElement(msgHeading).isDisplayed());
        } catch (Exception e) {
            return false;
        }
    }

    public void clickLogout() {
        driver.findElement(btnLogoutLocator).click();
    }
}
