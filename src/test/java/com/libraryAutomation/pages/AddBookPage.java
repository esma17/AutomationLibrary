package com.libraryAutomation.pages;

import com.libraryAutomation.utilities.BrowserUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddBookPage extends BooksPage {
    @FindBy(id = "add_book_modal")
    private WebElement formAddBook;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement buttonSaveChanges;

    @FindBy(xpath = "//button[@type='cancel']")
    private WebElement buttonClose;

    @FindBy(xpath = "//input[@name='name']")
    private WebElement inputBookName;

    @FindBy(xpath = "//input[@name='isbn']")
    private WebElement inputISBN;
    @FindBy(xpath = "//input[@name='year']")
    private WebElement inputYear;
    @FindBy(xpath = "//input[@name='author']")
    private WebElement inputAuthor;

    @FindBy(id = "book_group_id")
    private WebElement selectBookCategory;
    @FindBy(id = "description")
    private WebElement inputDescription;



    public WebElement getFormAddBook() {
        return BrowserUtils.waitForVisibility(formAddBook,20);
    }

    public WebElement getButtonSaveChanges() {
        return BrowserUtils.waitForClickability(buttonSaveChanges,20);
    }

    public WebElement getButtonClose() {
        return BrowserUtils.waitForClickability(buttonClose,20);
    }

    public WebElement getInputBookName() {
        return BrowserUtils.waitForVisibility(inputBookName,20);
    }

    public WebElement getInputISBN() {
        return BrowserUtils.waitForVisibility(inputISBN,20);
    }

    public WebElement getInputYear() {
        return BrowserUtils.waitForVisibility(inputYear,20);
    }

    public WebElement getInputAuthor() {
        return BrowserUtils.waitForVisibility(inputAuthor,20);
    }

    public WebElement getSelectBookCategory() {
        return BrowserUtils.waitForVisibility(selectBookCategory,20);
    }

    public WebElement getInputDescription() {
        return BrowserUtils.waitForVisibility(inputDescription,20);
    }
}
