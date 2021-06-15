package org.core.driver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.core.driver.Browser.Browsers;

public abstract class Browserfactory {

    public static RemoteWebDriver driver = null;

    public static RemoteWebDriver setDriver(Browsers browserName) throws Exception{


        switch (browserName){
            case CHROME:
                //set required options
                System.setProperty("webdriver.chrome.driver", "/home/kevin/automation/blog/seleniumFW/drivers/chromedriver");
                ChromeOptions chcoptions = new ChromeOptions();
                chcoptions.addArguments("start-maximized");
                chcoptions.addArguments("incognito");
                driver = new ChromeDriver(chcoptions);
                break;
            case FIREFOX:
                FirefoxOptions ffoptions = new FirefoxOptions();
                //set required options
                driver = new FirefoxDriver(ffoptions);
                driver.manage().window().maximize();
        }

        //Add other browsers and configurations as required

        return driver;
    }

    public static RemoteWebDriver getDriver(Browsers browser) throws Exception {

        if(driver == null){
            driver = Browserfactory.setDriver(browser);
            return driver;
        }
        return driver;
    }


}
