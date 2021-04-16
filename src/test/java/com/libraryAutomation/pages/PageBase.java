package com.libraryAutomation.pages;

import com.libraryAutomation.utilities.BrowserUtils;
import com.libraryAutomation.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public abstract class PageBase extends PageGenerator {


    @FindBy(xpath = "//a[@id='navbarDropdown']/span")
    public WebElement accountUserName;

    @FindBy(xpath = "//a[@class='nav-link']/span[1]")
    public List<WebElement> navigationBar;//this does not include the avatar

    @FindBy(xpath = "//a[@class='dropdown-item']")
    public WebElement logOutButton;


    public void navigatingThroughNavigationBar(String text) {
        BrowserUtils.waitForVisibility(navigationBar,20);
        for (WebElement element : navigationBar) {
            if (element.getText().equalsIgnoreCase(text)) {
                element.click();

            }
        }


    }

    public void logOut() {
        accountUserName.click();
        logOutButton.click();
    }





}
