package application.page.base;

import base.BrowserDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import reporting.TestLogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public  class ApplicationPageBase extends BrowserDriver {

    //This class has all the helper methods

        public static void sendKeys(WebElement webElement, String webElementName, String keys){
            TestLogger.log("Sending " + keys + " to " + webElementName);
            webElement.sendKeys(keys, Keys.ENTER);
            TestLogger.log("Keys Sent to " + webElementName);
        }

    public static void sendKeysII(WebElement webElement, String webElementName, String keys){
        TestLogger.log("Sending " + keys + " to " + webElementName);
        webElement.sendKeys(keys);
        TestLogger.log("Keys Sent to " + webElementName);
    }

        public static String getText(WebElement webElement, String webElementName){
            TestLogger.log("Getting Text from " + webElementName);
            String actualText =  webElement.getText();
            TestLogger.log("Actual text: " + actualText);
            return actualText;
        }

        public static void click(WebElement webElement, String webElementName){
            TestLogger.log("Click " + webElementName );
            webElement.click();
            TestLogger.log("Clicked " + webElementName);
        }









}


