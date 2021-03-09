package com.libraryAutomation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class DashBoard extends PageBase {

    @FindBy(xpath = "//a[@id='navbarDropdown']/span")
    public WebElement accountUserName;

    @FindBy(xpath = "//section[@id='dashboard']")
    public WebElement sectionDashboard;//used to verify that librarian land to dashboard


    @FindBy(id = "user_count")
    public WebElement userCount;


}
