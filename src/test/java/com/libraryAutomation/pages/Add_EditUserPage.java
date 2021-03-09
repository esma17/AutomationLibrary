package com.libraryAutomation.pages;

import com.github.javafaker.Faker;
import com.libraryAutomation.utilities.BrowserUtils;
import com.libraryAutomation.utilities.Memory;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Add_EditUserPage extends UsersPage {
    @FindBy(name = "full_name")
    public WebElement inputFullName;

    @FindBy(name = "password")
    public WebElement inputPassword;

    @FindBy(name = "email")
    public WebElement inputEmail;

    @FindBy(id = "user_group_id")
    public WebElement selectUserGroup;

    @FindBy(id = "status")
    public WebElement selectStatus;

    @FindBy(name = "start_date")
    public WebElement inputStartDate;

    @FindBy(name = "end_date")
    public WebElement inputEndDate;

    @FindBy(id = "address")
    public WebElement inputAdress;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement buttonSaveChanges;

    @FindBy(xpath = "//button[@type='cancel']")
    public WebElement buttonCancel;

    @FindBy(xpath = "//form[contains(@id,'user_form')]")
    public WebElement addOrEditForm;


    Faker faker = new Faker();
    String name = faker.name().fullName();
    String password = faker.internet().password();
    String email = faker.internet().emailAddress();
    String startDate = faker.date().past(10, TimeUnit.DAYS).toString();
    String endDate = faker.date().future(40, TimeUnit.DAYS).toString();
    String address = faker.address().fullAddress();


    public void addOrEditUser() {
        BrowserUtils.waitForVisibility(selectUserGroup,15);
        Select select = new Select(selectUserGroup);
        Random rnd = new Random();
        inputFullName.clear();

//        if(name.contains(".")){
//            name=name.substring(name.indexOf(".")+1);
//        }
        inputFullName.sendKeys(name);

        Memory.saveValue("name", name);
        inputPassword.clear();
        inputPassword.sendKeys(password);
        inputEmail.clear();
        inputEmail.sendKeys( email);
        //BrowserUtils.waitForClickability(selectUserGroup,15);

        select.selectByVisibleText("Librarian");
        select = new Select(selectStatus);
       // BrowserUtils.waitForClickability(selectStatus,15);
        select.selectByIndex(rnd.nextInt(1)+1);
        inputStartDate.clear();
        inputStartDate.sendKeys(startDate);
        inputEndDate.clear();
        inputEndDate.sendKeys(endDate);
        inputAdress.clear();
        inputAdress.sendKeys(address);

       buttonSaveChanges.click();

        BrowserUtils.waitForInVisibility(addOrEditForm, 15);


    }


    public void buttonCancelClick() {
        BrowserUtils.waitForClickability(buttonCancel,15).click();
        Assert.assertTrue("Couldnt cancel the add or edit user", BrowserUtils.waitForInVisibility(addOrEditForm, 15));
    }

    public void searchAddedUser() {
        if (!search.getText().isEmpty()){
            search.clear();
        }
        search.sendKeys(Memory.retrieveValue("name") + Keys.ENTER);
        Assert.assertEquals("couldnt find the added user", Memory.retrieveValue("name"), fullName.getText());
        Memory.refresh();
    }



}
