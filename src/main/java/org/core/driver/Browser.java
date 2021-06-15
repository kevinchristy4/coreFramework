package org.core.driver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.rmi.Remote;

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
}
