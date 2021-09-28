package utilities;

import base.TestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class TestUtil extends TestBase {
    public static String screenshootPath;
    public static String screenshotName;
    public static void captureScreenshot() throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        System.out.println("--------------------");
        System.out.println(scrFile.getName());

        screenshotName = "error.png";
        System.out.println("dddddddd");
  //      String screenshotBase64 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
       FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") +"\\target\\surefire-reports\\html"+screenshotName));
    }
}
