package org.core;

import org.core.component.elements;
import org.core.driver.Browser;
import org.core.util.Logger;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Unit test for simple App.
 */
public class test1
{
    WebDriver drv = null;
    private Logger log = Logger.getLogger();

    @Test
    public void shouldAnswerWithTrue() throws Exception {

//        Thread.sleep(10000);
        log.startTest("sampleTest1111");
        Browser browser = new Browser();
        log.step(1,"Select chrome");
        browser.selectBrowser("chrome");
        log.step(2,"opening chrome");
        drv = browser.getDriver();
        log.step(3,"Go to ideas2it");
        browser.getDriver().get("https://www.ideas2it.com");
        browser.waitForPage();
        log.step(4,"Click contact");
        elements element = new elements(By.xpath("//a[text()='CONTACT']"),"Contact");
        element.waitForElement();
        element.click();
        log.endTest("sample test1111");
//        element.print();

    }
}
