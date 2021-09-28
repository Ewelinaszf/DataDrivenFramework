package testcases;

import base.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Objects;

public class AddCustomerTest extends TestBase {

    @Test(dataProvider = "getData")
    public void addCustomer(String firstName, String lastName, String postCode, String alertText) throws InterruptedException {
        Thread.sleep(5000);

        driver.findElement(By.cssSelector(OR.getProperty("addCustomerButton"))).click();
        driver.findElement(By.cssSelector(OR.getProperty("firstName"))).sendKeys(firstName);
        driver.findElement(By.cssSelector(OR.getProperty("lastName"))).sendKeys(lastName);
        driver.findElement(By.cssSelector(OR.getProperty("postalCode"))).sendKeys(postCode);
        driver.findElement(By.cssSelector(OR.getProperty("addButton"))).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().contains(alertText));
        Thread.sleep(5000);
        alert.accept();

    }

    @DataProvider(name = "getData")
    public Object[][] getData() {

        String sheetName = "AddCustomerTest";
        int rows = excel.getRowCount(sheetName);
        int cols = excel.getColumnCount(sheetName);
        Object[][] data = new Object[rows - 1][cols - 1];

        for (int rowNum = 2; rowNum <= rows; rowNum++) {
            for (int colNum = 0; colNum < cols - 1; colNum++) {
                data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
            }
        }
        System.out.println(Arrays.deepToString(data));
        return data;
    }
}
