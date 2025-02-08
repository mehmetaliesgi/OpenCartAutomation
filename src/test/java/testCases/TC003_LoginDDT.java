package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = {"DataDriven"})
    public void verifyLoginDDT(String email, String pw, String expectedResult) {
        logger.info("***** Starting TC002_LoginTest *****");
        try {
            HomePage homePage = new HomePage(driver);
            LoginPage loginPage = new LoginPage(driver);
            MyAccountPage myAccountPage = new MyAccountPage(driver);

            homePage.clickMyAccount();
            homePage.clickLogin();

            loginPage.setEmail(email);
            loginPage.setPassword(pw);
            loginPage.clickLogin();

            boolean targetPage = myAccountPage.isMyAccountPageExist();

            if (expectedResult.equalsIgnoreCase("Valid")) {
                if (targetPage) {
                    myAccountPage.clickLogout();
                    Assert.assertTrue(true);
                } else {
                    Assert.fail();
                }
            }
            if (expectedResult.equalsIgnoreCase("Invalid")) {
                if (targetPage) {
                    myAccountPage.clickLogout();
                    Assert.fail();
                } else {
                    Assert.assertTrue(true);
                }
            }
        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("***** Finished TC002_LoginTest *****");
    }
}
