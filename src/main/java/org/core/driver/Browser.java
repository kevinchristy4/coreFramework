package org.core.driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.rmi.Remote;
import java.util.function.Function;

public class Browser {

    private static ThreadLocal<RemoteWebDriver> driverHolder;
    private static Browsers browserName;

    public enum Browsers{
        CHROME("chrome"),
        FIREFOX("firefox");
       //Add other required browsers
        public String value;
        Browsers(String values){
            value = values;
        }
        public String toString() {
            return value;
        }
    }

    //Modify this method into switch or any other according to your need
    public Browsers selectBrowser(String browser){
        if(browser.toLowerCase() == "chrome"){
            browserName = Browsers.CHROME;
        }else {
            browserName = Browsers.FIREFOX;
        }
        return browserName;
    }

    public RemoteWebDriver getDriver() throws Exception {

            return Browserfactory.getDriver(browserName);

    }

    public void waitForPage() throws Exception {

        new WebDriverWait(getDriver(), 60).until((Function<WebDriver, Boolean>) driver -> {
            Boolean isPageLoaded = false;
            try {
                Object isDocumentReady = ((JavascriptExecutor)
                        driver).executeScript("return document.readyState");

                //Modify below script to wait for angular application - This below script is from protractor native wait

//                Object isAngularReady = ((JavascriptExecutor)
//                        driver).executeAsyncScript( "var callback = arguments[arguments.length - 1];\n" +
//                        "var el = document.querySelector('html');\n" +
//                        "if (!window.angular) {\n" +
//                        "    callback('false')\n" +
//                        "}\n" +
//                        "if (angular.getTestability) {\n" +
//                        "    angular.getTestability(el).whenStable(function(){callback('true')});\n" +
//                        "} else {\n" +
//                        "    if (!angular.element(el).injector()) {\n" +
//                        "        callback('false')\n" +
//                        "    }\n" +
//                        "    var browser = angular.element(el).injector().get('$browser');\n" +
//                        "    browser.notifyWhenNoOutstandingRequests(function(){callback('true')});\n" +
//                        "}");

                if (isDocumentReady.toString().equals("complete")){
                    isPageLoaded =  true;
                }
            } catch (Exception e) {
               System.out.println(e);
            }
            return isPageLoaded;
        });
    }
}
