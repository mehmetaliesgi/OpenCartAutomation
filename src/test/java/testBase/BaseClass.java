package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass {
    public static WebDriver driver;
    public Logger logger;
    public Properties properties;

    @BeforeClass(groups = {"Regression", "Master", "Sanity"})
    @Parameters({"os", "browser"})
    public void setup(String os, String browser) throws IOException {
        FileReader file = new FileReader("C:\\Users\\mehme\\Desktop\\Selenium_Projects\\OpenCartAutomation\\src\\test\\resources\\config.properties");
        properties = new Properties();
        properties.load(file);

        logger = LogManager.getLogger(this.getClass());
        if(properties.getProperty("executionEnv").equalsIgnoreCase("remote")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            if (os.equalsIgnoreCase("windows")) {
                capabilities.setPlatform(Platform.WIN11);
            } else if (os.equalsIgnoreCase("mac")) {
                capabilities.setPlatform(Platform.MAC);
            } else if (os.equalsIgnoreCase("linux")){
                capabilities.setPlatform(Platform.LINUX);
            } else {
                System.out.println("No matching OS!");
                return;
            }

            switch (browser.toLowerCase()) {
                case "chrome": capabilities.setBrowserName("chrome"); break;
                case "firefox": capabilities.setBrowserName("firefox"); break;
                case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
                default:
                    System.out.println("No matching browser!"); return;
            }

            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        }

        if (properties.getProperty("executionEnv").equalsIgnoreCase("local")){
            switch (browser.toLowerCase()) {
                case "chrome": driver = new ChromeDriver(); break;
                case "edge": driver = new EdgeDriver(); break;
                case "firefox": driver = new FirefoxDriver(); break;
                default:
                    System.out.println("Invalid browser name..."); return;
            }
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(properties.getProperty("appURL"));
        driver.manage().window().maximize();
    }

    @AfterClass(groups = {"Regression", "Master", "Sanity"})
    public void tearDown() {
        driver.quit();
    }

    public String randomString() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    public String randomNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    public String randomAlphaNumeric() {
        return RandomStringUtils.randomAlphanumeric(12);
    }

    public String captureScreen(String testName) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilepath = STR."\{System.getProperty("user.dir")}\\screenshots\\\{testName}_\{timeStamp}.png";
        File targetFile = new File(targetFilepath);

        sourceFile.renameTo(targetFile);

        return STR."\\screenshots\\\{testName}_\{timeStamp}.png";
    }
}
