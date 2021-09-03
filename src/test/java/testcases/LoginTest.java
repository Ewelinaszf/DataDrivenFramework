package testcases;

import base.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test
    public void loginAsBankManager() throws InterruptedException {
        //log.debug("Inside Login Test");
        log.info("Inside Login Test");
        driver.findElement(By.cssSelector((OR.getProperty("bankManagerLoginButton")))).click();
        Thread.sleep(3000);

       // log.debug("Login successfully executed");
        log.info("Login successfully executed");
    }
}
