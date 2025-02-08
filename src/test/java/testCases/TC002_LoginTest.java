package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
    @Test(groups = {"Sanity", "Master"})
    public void verifyLogin() {
        logger.info("***** Starting TC002_LoginTest *****");

        try {
            HomePage homePage = new HomePage(driver);
            LoginPage loginPage = new LoginPage(driver);
            MyAccountPage myAccountPage = new MyAccountPage(driver);

            homePage.clickMyAccount();
            homePage.clickLogin();

            loginPage.setEmail(properties.getProperty("email"));
            loginPage.setPassword(properties.getProperty("password"));
            loginPage.clickLogin();

            boolean targetPage = myAccountPage.isMyAccountPageExist();
            Assert.assertTrue(targetPage);
        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("***** Finished TC002_LoginTest *****");
    }
}
