package testCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.HomePage;
import pageObjects.RegisterPage;
import testBase.BaseClass;

public class TC001_RegisterTest extends BaseClass {
    SoftAssert softAssert;
    @Test(groups = {"Regression", "Master"})
    public void verifyAccountRegistration() {
        try {
            logger.info("***** Starting TC001_RegisterTest *****");

            HomePage homePage = new HomePage(driver);
            RegisterPage registerPage = new RegisterPage(driver);

            homePage.clickMyAccount();
            logger.info("Clicked My Account button");
            homePage.clickRegister();
            logger.info("Clicked Register button");

            logger.info("Providing customer details....");
            registerPage.setFirstName(randomString().toUpperCase());
            registerPage.setLastName(randomString().toUpperCase());
            registerPage.setEmail(randomString() + "@gmail.com");
            registerPage.setPhone(randomNumber());
            String password = randomAlphaNumeric();
            registerPage.setPassword(password);
            registerPage.setPasswordConfirm(password);
            registerPage.setPrivacyPolicy();
            registerPage.clickContinue();

            logger.info("Validating expected message...");
            String confirmationMessage = registerPage.getConfirmationMessage();
            softAssert = new SoftAssert();
            softAssert.assertEquals(confirmationMessage, "Your Account Has Been Created!");
        } catch (Exception e) {
            logger.error("Test failed because: " + e);
            softAssert = new SoftAssert();
            softAssert.fail();
        }
        logger.info("***** Finished TC001_RegisterTest *****");
    }
}
