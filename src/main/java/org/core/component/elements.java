package org.core.component;

import org.openqa.selenium.By;

public class elements extends CommonMethods{
    public elements(By locator, String name) throws Exception {
        super(locator, name);
    }

    public void print (){
        System.out.println("ddd");
    }
}
