package org.core.component;

import org.core.driver.Browser;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.function.Function;

public class CommonMethods implements WebElement {
    private static RemoteWebDriver driver;
    private By locator;
    private WebElement ele;

    public CommonMethods(By locator,String name) throws Exception {
        driver = new Browser().getDriver();
        this.locator = locator;
        ele = driver.findElement(this.locator);
    }

    // A simple custom wait
    public void waitForElement(){

        WebDriverWait wait =  new WebDriverWait(driver, 60);
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait.until((Function<WebDriver, Boolean>) driver -> {
            WebElement waitedEle =  wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(locator)));
            if(waitedEle != null && driver.findElement(locator).isDisplayed()){
                System.out.println(waitedEle+"\n"+driver.findElement(locator).isDisplayed());
                return true;
            }
            return false;
        });
    }


    /* Have your owm custom implementation of selenium methods - or just use the original methods */

    @Override
    public void click() {
        ele.click();
    }

    @Override
    public void submit() {
        ele.submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        ele.sendKeys(keysToSend);
    }

    @Override
    public void clear() {
        ele.clear();
    }

    @Override
    public String getTagName() {
        return ele.getTagName();
    }

    @Override
    public String getAttribute(String name) {
        return ele.getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        return ele.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return ele.isEnabled();
    }

    @Override
    public String getText() {
        return ele.getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return ele.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return ele.findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return ele.isDisplayed();
    }

    @Override
    public Point getLocation() {
        return ele.getLocation();
    }

    @Override
    public Dimension getSize() {
        return ele.getSize();
    }

    @Override
    public Rectangle getRect() {
        return ele.getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return ele.getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return ele.getScreenshotAs(target);
    }
}
