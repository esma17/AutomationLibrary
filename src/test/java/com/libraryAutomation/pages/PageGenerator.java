package com.libraryAutomation.pages;

import com.libraryAutomation.utilities.Driver;
import org.openqa.selenium.support.PageFactory;

public class PageGenerator {
    public PageGenerator() {
        PageFactory.initElements(Driver.getDriver(),this);
    }
}
