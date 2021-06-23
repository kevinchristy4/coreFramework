package org.core;

import static org.junit.Assert.assertTrue;

import org.core.component.elements;
import org.core.driver.Browser;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    WebDriver drv = null;
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws Exception {

        Browser browser = new Browser();
        browser.selectBrowser("chrome");
        drv = browser.getDriver();
        browser.getDriver().get("https://www.ideas2it.com");
        browser.waitForPage();
        elements element = new elements(By.xpath("//a[text()='CONTACT']"),"Contact");
        element.waitForElement();
        element.click();
//        element.print();

    }
}
