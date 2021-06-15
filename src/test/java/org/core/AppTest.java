package org.core;

import static org.junit.Assert.assertTrue;

import org.core.baseElements.BaseElements;
import org.core.driver.Browser;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws Exception {
        Browser browser = new Browser();
        browser.selectBrowser("chrome");
        browser.getDriver();
        BaseElements baseElements = new BaseElements();
        browser.getDriver().close();



        assertTrue( true );
    }
}
