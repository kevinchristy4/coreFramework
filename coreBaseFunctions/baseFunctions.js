const { browser, protractor, element, by,ExpectedConditions } = require("protractor");


var baseFunctions = function(){
    
    var EC = protractor.ExpectedConditions;
    var commonDate = new Date();
    var buttonXpath = (btnText) => {return `//button[contains(text(),'${btnText}')]`;};
    var absButtonXpath = (btnText) => {return element(by.xpath(`//button[text()='${btnText}']`))};
    var linkXpath = (text) =>{return `//a[contains(.,'${text}')]`};
    var labelLinkXpath = (lblText) => {return `//label[text() = '${lblText}']`;};

    this.waitFor = async function (waitElement) {
        try {
            await browser.wait(ExpectedConditions.visibilityOf(waitElement), 20000).then((result) => {
                browser.driver.executeScript("arguments[0].setAttribute('style', arguments[1]);", waitElement.getWebElement(), "color: Red; border: 2px solid red;")
            })
        } catch (result_2) {
            throw new Error('Element not found = ' + waitElement.locator().toString());
        }
    }

    this.scrollIntoView = function(scrollEle){

        this.waitFor(scrollEle);
        browser.executeScript("arguments[0].scrollIntoView({block: 'center'});", scrollEle);
    };

    this.clickWithWait = function (passElement) {

        this.waitFor(passElement);
        passElement.click();
    }

    this.clickWithScroll = function (passElement) {

        this.waitFor(passElement);
        this.scrollIntoView(passElement);
        passElement.click();
    }

    this.waitForPageToLoadCompletely = ()=>{
        
        browser.waitForAngular();

    }

    this.clickButtonUsingText = function(buttonText){

        var button = element(by.xpath(buttonXpath(buttonText)));
        this.clickWithScroll(button);

    }

    this.clickButtonUsingAbsoluteText = (btnTxt)=>{

        this.clickWithScroll(absButtonXpath(btnTxt));
    }

    this.clickLinkUsingLabel = function(labelText){

        var button = element(by.xpath(labelLinkXpath(labelText)));
        this.clickWithWait(button);

    }


    this.clickLink = function(linkText){

        var link = element(by.xpath(linkXpath(linkText)));
        this.clickWithWait(link);

    }

    this.clickLinkWithScroll = function(linkText){

        var link = element(by.xpath(linkXpath(linkText)));
        this.clickWithScroll(link);

    }

    this.get = (url)=>{
        
        browser.navigate().to(url);
        this.waitForPageToLoadCompletely();
    }

    this.clearBrowserData = function(){
        browser.executeScript('window.localStorage.clear();');
        browser.executeScript('window.sessionStorage.clear();');
        browser.driver.manage().deleteAllCookies(); 
    }

    this.getTime = function(){
        return commonDate.getTime();
    }


    this.assertIfElementPresent = function(elemenT1){

        this.waitFor(elemenT1);
        expect(elemenT1.isPresent()).toBe(true);
        return elemenT1.isPresent()
    };

    this.assertIfElementNotPresent = function(elementT2){

        browser.wait(EC.invisibilityOf(elementT2)).then((result)=>{
            expect(result).toBe(true);
        });
        return elementT2.isPresent();
    };

    

    this.zoom = (level)=>{

        browser.executeScript(`document.body.style.zoom='${level}%'`);
    }

    this.sendKeys = (element,data)=>{

        this.waitFor(element);
        element.clear();
        element.sendKeys(data);
    };



    this.getText = (element)=>{

        this.waitFor(element);
        return element.getText();
    }

    this.getTitle=()=>{

        browser.getTitle().then((title)=>{
            return title;
        })
    }

    this.focusToField =(element) => {

        browser.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        // commonFunc.waitFor(element);
    }

    this.sleep=(sec)=>{

        return browser.sleep(sec*1000);

    }


    this.getFormattedDate = ()=>{

        var date = new Date();
        var day = date.getDate();
        var month = date.getMonth();
        if (date.getDate() < 10) {
            day = '0' + date.getDate().toString();
        }
        if (date.getMonth() < 10) {
            month = '0' + (date.getMonth()+1).toString();
        }
        return (month+'/'+day+'/'+date.getFullYear().toString());
    }

   
   
}
module.exports = new baseFunctions();