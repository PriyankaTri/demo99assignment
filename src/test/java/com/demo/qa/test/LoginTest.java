package com.demo.qa.test;

import com.demo.utilities.TestConfig;
import com.demo.utilities.TestUtils;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

@Listeners(com.demo.listener.TestNGListener.class)
public class LoginTest extends BaseTest {

    LoginPage loginPage;
    HomePage homePage;

    TestConfig testConfig;

    @Test(dataProvider = "loginData")
    public void loginProcess(String uname, String pass) throws IOException {
        extentTest = extentReports.createTest("Login Test");
      loginPage = new LoginPage(driver);
       homePage = new HomePage(driver);
       loginPage.enterUserName(uname);
       log.info("Entered Username: "+ uname);

       loginPage.enterPassword(pass);
       log.info("Entered password: "+ pass);
       homePage = loginPage.clickLoginBtn();
      log.info("Clicked on login button");
        String gtplText = homePage.getGtplText();
        Assert.assertEquals(gtplText,"Gtpl Bank");
       log.info("Test passed");
       homePage.clickLogout();
        Alert al = driver.switchTo().alert();
        al.accept();


    }

    @DataProvider(name = "loginData")
    public String[][] getData() throws IOException {
        String path = System.getProperty("user.dir") + "\\loginTestData.xlsx";
        System.out.println(path);
        TestUtils testUtils = new TestUtils(path);
        int totalRowCount = testUtils.getRowCount("Sheet1");
        int totalColCount = testUtils.getCellCount("Sheet1",1);

        String loginData[][] = new String[totalRowCount-1][totalColCount];
        for (int i=1;i<totalRowCount;i++){
            for (int j=0; j<totalColCount; j++){

                loginData[i-1][j] = testUtils.getCellData("Sheet1",i,j);

            }
        }

        return loginData;

    }
}
