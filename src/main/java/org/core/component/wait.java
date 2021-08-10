package org.core.component;

import org.core.driver.Browser;
import org.core.util.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;

public abstract class wait{

    private static RemoteWebDriver driver;
    private By locator;
    private WebElement ele;
    private Logger log = Logger.getLogger();


    public static void waitForPage() throws Exception {
        new WebDriverWait(new Browser().getDriver(), 60).until((Function<WebDriver, Boolean>) driver -> {
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
