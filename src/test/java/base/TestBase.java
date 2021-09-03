package base;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    /*
    WebDriver - done
    Properties - done
    Logs
    ExtentReports
    DB
    Excel
    Mail
    ReportNG, ExtentReport
    Jenkins
     */

    public static WebDriver driver;
    public static Properties config = new Properties();
    public static Properties OR = new Properties();
    public static FileInputStream fis;
    public Logger log = Logger.getLogger("devpinoyLogger");



    @BeforeSuite
    public void setUp() {
        //       String log4jConfPath = "/src/test/resources/logs/log4j.properties";
        //       PropertyConfigurator.configure(log4jConfPath);
//        log = Logger.getLogger("devpinoyLogger");
//        log.debug ("- information--");
        if (driver == null) {
            FileInputStream fis = null;

            try {

                fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {

                config.load(fis);
                log.debug("Config file loaded!!!");
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {

                fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                OR.load(fis);
                log.debug("OR Config file loaded!!!");
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (config.getProperty("browser").equals("firefox")) {

                // System.getProperty("webriver.gecko.driver", "gecko.exe");
                driver = new FirefoxDriver();
            } else if (config.getProperty("browser").equals("chrome")) {

                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
                driver = new ChromeDriver();
                log.debug("Chrome Launched !!!");
            }
        } else if (config.getProperty("browser").equals("ie")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();

        }

      //  String log4jConfPath = "/src/test/resources/logs/log4j.properties";
     //   PropertyConfigurator.configure(log4jConfPath);

      // String log4jConfPath = System.getProperty("user.dir")+"/src/test/resources/logs/log4j.properties";
   //     Logger log = Logger.getLogger("devpinoyLogger");

        driver.get(config.getProperty("testsiteurl"));
        log.debug("Navigate to : " + config.getProperty("testsiteurl"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
    }


    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        log.debug("Test execution completed");
    }
}