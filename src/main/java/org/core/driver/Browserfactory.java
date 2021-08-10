package org.core.driver;

//import org.apache.log4j.helpers.ThreadLocalMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.core.driver.Browser.Browsers;

import java.util.logging.Level;

public abstract class Browserfactory {
    static ThreadLocal<RemoteWebDriver> driverHolder = new ThreadLocal<RemoteWebDriver>();
    public static RemoteWebDriver driver = null;
    private static Browsers browserName = null;
    private static RemoteWebDriver setDriver(Browsers browserName) throws Exception{
        switch (browserName){
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "/home/kevin/automation/blog/seleniumFW/drivers/chromedriver");
                ChromeOptions chcoptions = new ChromeOptions();
                chcoptions.addArguments("start-maximized");
                chcoptions.addArguments("incognito");
                System.setProperty("webdriver.chrome.silentOutput", "true");
                java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
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
            if(driverHolder.get() == null){
                browserName = browser;
                driverHolder.set(Browserfactory.setDriver(browser));
                return driverHolder.get();
            }
            return driverHolder.get();
    }
    public static void closeBrowser() throws Exception {
        try {
            getDriver(browserName).quit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driverHolder.remove();
        }
    }

}
