package com.libraryAutomation.stepDefinitions;

import com.libraryAutomation.pages.BooksPage;
import com.libraryAutomation.pages.LoginPage;
import com.libraryAutomation.utilities.BrowserUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.eo.Se;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

public class BookCategories_StepDefinitions {
    BooksPage booksPage = new BooksPage();

    @Given("User logs in as Student")
    public void user_logs_in_as_student() {
        LoginPage loginPage = new LoginPage();
        loginPage.loginAsStudent();
        booksPage.navigatingThroughNavigationBar("books");
    }

    @Then("Student should see below info in book categories dropdown")
    public void student_should_see_below_info_in_book_categories_dropdown(List<String> expectedCategories) {
        BrowserUtils.sleep(3);
        booksPage.booksPageLink.click();
        BrowserUtils.sleep(3);
        List<String> actualCategories = booksPage.getAllBookCategories();
        Assert.assertEquals(expectedCategories, actualCategories);
    }

    @Then("Student selects {string} from category dropdown")
    public void student_selects_from_category_dropdown(String category) {
        BrowserUtils.waitForVisibility(booksPage.selectBookCategories,15);
        Select select = new Select(booksPage.selectBookCategories);
        BrowserUtils.textToBePresent(booksPage.selectBookCategories,category);
        select.selectByVisibleText(category);

    }


    @Then("Student should see all the books from that {string}")
    public void studentShouldSeeAllTheBooksFromThat(String expectedCategory) {

        List<WebElement> actual = booksPage.listOfSelectedCategories;
        Set<String> actualText = new LinkedHashSet<>();
        for (WebElement element : actual) {
            BrowserUtils.waitForStaleElement(element);
            actualText.add(element.getText());
        }
        for (String eachLine : actualText) Assert.assertEquals(eachLine, expectedCategory);
    }


}
