package com.libraryAutomation.pages;

import com.libraryAutomation.utilities.BrowserUtils;
import com.libraryAutomation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class UsersPage extends PageBase {


    @FindBy(xpath = "//select[@name='tbl_users_length']")
    public WebElement showRecordsDropdown;

    @FindBy(xpath = "//section[@id='users']/div/div/span/a")
    public WebElement addUser;

    @FindBy(id = "user_groups")
    public WebElement userGroup;

    @FindBy(xpath = "//td[3]")
    public List<WebElement> listOfNames;


    @FindBy(xpath = "//input[@type='search']")
    public WebElement search;


    @FindBy(xpath = "//th[@class='sorting_disabled']")
    public WebElement action;

    @FindBy(xpath = "//th[@class='sorting_desc']")
    public WebElement userID;

    @FindBy(xpath = "//tr/td[3]")
    public WebElement fullName;

    @FindBy(xpath = "/thead/tr/th[4]")
    public WebElement emailInTable;

    @FindBy(xpath = "/thead/tr/th[5]")
    public WebElement group;
    @FindBy(xpath = "thead/tr/th[6]")
    public WebElement status;


    @FindBy(xpath = "//thead/tr/th")
    public List<WebElement> tableHeaders;

    @FindBy(xpath = "//tbody/tr/td")
    public WebElement firstEditButton;

    @FindBy(xpath = "//td[1]")
    public List<WebElement> editButtons;

    @FindBy(xpath = "//h5[.='Edit User Information']")
    public WebElement editUserInformationHeader;

    @FindBy(xpath = "//td[3]")
    public List<WebElement> allNames;

    String locator = "(//td[3])[%s]/../td[1]";

    public void selectUserToEdit(String name) {
        search.sendKeys(name);
        BrowserUtils.clickOn(firstEditButton,15);

    }
    public void selectingRandomEdit(){
        BrowserUtils.waitForVisibility(editButtons,15);
        Random rnd=new Random();
        editButtons.get(rnd.nextInt(editButtons.size()-1)).click();
    }


}
