package com.libraryAutomation.stepDefinitions;

import com.libraryAutomation.pages.AddBookPage;
import com.libraryAutomation.pages.BooksPage;
import com.libraryAutomation.pages.LoginPage;
import com.libraryAutomation.utilities.BrowserUtils;
import com.libraryAutomation.utilities.Driver;
import com.libraryAutomation.utilities.Memory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalTime;
import java.util.List;

public class BooksTable_StepDefinitions {
    WebDriver driver = Driver.getDriver();
    LoginPage loginPage = new LoginPage();
    BooksPage books = new BooksPage();
    AddBookPage addBook = new AddBookPage();


    @Given("the user on the homepage")
    public void the_user_on_the_homepage() {
        loginPage.loginAsStudent();
    }


    @Then("the user should see the following column names:")
    public void the_user_should_see_the_following_column_names(List<String> expectedheaderText) {
        BrowserUtils.waitForVisibility(books.header, 15);
        List<String> actualHeaderText = BrowserUtils.convertWebElementToString_andGetText(books.header);
        Assert.assertEquals(expectedheaderText, actualHeaderText);
    }

    @Then("the show records dropdown default value should be {int}")
    public void theShowRecordsDropdownDefaultValueShouldBe(int expectedDefaultValue) {
        BrowserUtils.waitForVisibility(books.selectRecords, 15);
        Select select = new Select(books.selectRecords);
        Assert.assertEquals(expectedDefaultValue, Integer.parseInt(select.getFirstSelectedOption().getText()));
    }


    @And("the dropdown should have following options:")
    public void theDropdownShouldHaveFollowingOptions(List<String> expectedValues) {
        Select select = new Select(books.selectRecords);
        List<WebElement> actualList = select.getOptions();
        Assert.assertEquals("Didnt get the expected valuest in record dropdown", expectedValues, BrowserUtils.convertWebElementToString_andGetText(actualList));

    }

    @Given("librarian on books page and clicks to addBook Button")
    public void librarianOnBooksPageAndClicksToAddBookButton() {
        loginPage.loginAsLibrary();
        books.navigatingThroughNavigationBar("Books");
        books.getButtonAddButton().click();
    }

    @When("librarian enter valid info about the book {string},{string},{string},{string},{string},{string}")
    public void librarianEnterValidInfoAboutTheBook(String bookName, String iSBN, String year, String author, String bookCategory, String description) {
        addBook.getInputBookName().sendKeys(bookName);
        Memory.saveValue("bookName", bookName);
        addBook.getInputISBN().sendKeys(iSBN);
        addBook.getInputYear().sendKeys(year);
        addBook.getInputAuthor().sendKeys(author);
        Select select = new Select(addBook.getSelectBookCategory());
        select.selectByVisibleText(bookCategory);
        LocalTime time = LocalTime.now();
        Memory.saveValue("time", time.toString());
        addBook.getInputDescription().sendKeys(description + " " + time);
        addBook.getButtonSaveChanges().click();

    }

    @Then("the book is displayed on the table")
    public void theBookIsDisplayedOnTheTable() {
        BrowserUtils.waitForInVisibility(addBook.getFormAddBook(), 20);
        addBook.getButtonSearch().sendKeys(Memory.retrieveValue("bookName"));
        books.getFirstEditButton().click();
        Assert.assertTrue(addBook.getInputDescription().getText().contains(Memory.retrieveValue("time")));
        Memory.refresh();

    }

}
