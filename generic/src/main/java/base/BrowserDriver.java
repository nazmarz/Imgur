package base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import reporting.ExtentManager;
import reporting.ExtentTestManager;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BrowserDriver {

    //ExtentReport
    public static ExtentReports extent;
    @BeforeSuite
    public void extentSetup(ITestContext context) {
        ExtentManager.setOutputDirectory(context);
        extent = ExtentManager.getInstance();
    }
    @BeforeMethod
    public void startExtent(Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName().toLowerCase();
        ExtentTestManager.startTest(method.getName());
        ExtentTestManager.getTest().assignCategory(className);
    }
    protected String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }
    @AfterMethod
    public void afterEachTestMethod(ITestResult result) {
        ExtentTestManager.getTest().getTest().setStartedTime(getTime(result.getStartMillis()));
        ExtentTestManager.getTest().getTest().setEndedTime(getTime(result.getEndMillis()));

        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group);
        }

        if (result.getStatus() == 1) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
        } else if (result.getStatus() == 2) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, getStackTrace(result.getThrowable()));
        } else if (result.getStatus() == 3) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
        }
        ExtentTestManager.endTest();
        extent.flush();
        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreenshot(driver, result.getName());
        }
        driver.quit();
    }
    @AfterSuite
    public void generateReport() {
        extent.close();
    }
    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    public static WebDriver driver = null;


    @Parameters({"os", "os_version", "browserName", "browserVersion", "url"})
    @BeforeMethod
    public void setUp(
            @Optional("windows") String os, @Optional("10") String os_version, @Optional("firefox") String browserName, @Optional("34")
            String browserVersion, @Optional("https://www.google.com") String url) throws IOException {
        getLocalDriver(browserName, os);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);

    }

    public WebDriver getLocalDriver(String browserName, String os) {


        if (browserName.equalsIgnoreCase("chrome")) {

            ChromeOptions options =new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--ignore-certificate-errors");
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);

            if (os.equalsIgnoreCase("windows")) {
                System.setProperty("webdriver.chrome.driver", "../Generic/drivers/windows/chromedriver.exe");
                driver = new ChromeDriver(options);

            } else if (os.equalsIgnoreCase("mac")) {
                System.setProperty("webdriver.chrome.driver", "../Generic/drivers/mac/chromedriver");
                driver = new ChromeDriver(options);
            }
        } else if (browserName.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--private");
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);


            if (os.equalsIgnoreCase("windows")) {
                System.setProperty("webdriver.gecko.driver", "../Generic/drivers/windows/geckodriver.exe");
                driver = new FirefoxDriver(options);
            } else if (os.equalsIgnoreCase("mac")) {
                System.setProperty("webdriver.gecko.driver", "../Generic/drivers/mac/geckodriver");
                driver = new FirefoxDriver(options);
            }
        }

        return driver;
    }

    @AfterMethod
    public void closeOut() {
        driver.quit();
    }

    public static void captureScreenshot(WebDriver driver, String screenshotName){

        DateFormat df = new SimpleDateFormat("(MM.dd.yyyy-HH:mma)");
        Date date = new Date();
        df.format(date);

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(System.getProperty("user.dir")+ "/screenshots/"+screenshotName+" "+df.format(date)+".png"));
            System.out.println("Screenshot captured");
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot "+e.getMessage());;
        }

    }

    /**
     *
     * @return current WebDriver instance
     */

    public static WebDriver  getDriver()
    {
        return driver;
    }
    /**
     * This method returns the url.
     *
     * @return Returns the string.
     */
    public static String getUrl()
    {
        return driver.getCurrentUrl();
    }

}
