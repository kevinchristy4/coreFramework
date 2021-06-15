package org.core.baseElements;

import org.core.driver.Browser;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseElements {
    private static RemoteWebDriver driver;

    public BaseElements() throws Exception {
        driver = new Browser().getDriver();
    }


}
