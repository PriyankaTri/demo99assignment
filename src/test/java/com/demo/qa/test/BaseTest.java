package com.demo.qa.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.demo.utilities.TestConfig;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;


public class BaseTest {

    WebDriver driver;
    public static Logger log;
    TestConfig testConfig = new TestConfig();

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extentReports;
    public ExtentTest extentTest;
    public ITestResult result;


    @BeforeClass
    @Parameters({"browser","url"})
    public void setUp(String browser,String url){
        log =  Logger.getLogger("SeleniumFramework");
        PropertyConfigurator.configure("log4j.properties");
        if(browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver",testConfig.getChromeDriver());
            driver = new ChromeDriver();

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        else if(browser.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver",testConfig.getEdgeDriver());
            driver = new EdgeDriver();

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        driver.get(url);

    }

    @BeforeMethod
    public void setExtent(){
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\ExtentReport\\myreport.html");
        htmlReporter.config().setDocumentTitle("Demo Automation Report");
        htmlReporter.config().setReportName("Demo Automation Report");
        htmlReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("Hostname","Localhost");
        extentReports.setSystemInfo("Browser","chrome");

    }

    @AfterMethod
    public void flush(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE)
            extentTest.log(Status.FAIL,"Test case is failed"+result.getName());
        else if(result.getStatus() == ITestResult.SUCCESS)
            extentTest.log(Status.PASS,"Test case is passed"+result.getName());
        else if(result.getStatus() == ITestResult.SKIP)
            extentTest.log(Status.SKIP,"Test case is skipped"+result.getName());

        extentReports.flush();
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
