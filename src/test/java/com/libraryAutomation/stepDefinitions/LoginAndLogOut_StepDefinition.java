package com.libraryAutomation.stepDefinitions;

import com.libraryAutomation.pages.BooksPage;
import com.libraryAutomation.pages.DashBoard;
import com.libraryAutomation.pages.LoginPage;
import com.libraryAutomation.utilities.BrowserUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginAndLogOut_StepDefinition extends WebDriverManager{

    LoginPage loginPage = new LoginPage();
    BooksPage booksPage = new BooksPage();
    DashBoard landing=new DashBoard();

    @Given("the user login as a {string}")
    public void theUserLoginAsA(String role) {
        switch (role) {
            case "student":
                loginPage.loginAsStudent();
                break;
            case "librarian":
                loginPage.loginAsLibrary();
                break;
        }
    }

    @Then("the user on  {string}")
    public void theUserOn(String landingPage) {
        switch (landingPage) {
            case "books":
                BrowserUtils.waitForVisibility(booksPage.sectionBooks,15);
                Assert.assertEquals(booksPage.sectionBooks.getAttribute("id"), landingPage);
                break;
            case "dashboard":
                BrowserUtils.waitForVisibility(booksPage.sectionDashboard,15);
                Assert.assertEquals(booksPage.sectionDashboard.getAttribute("id"), landingPage);
                break;
        }
    }

    @Given("the user is on the {string}")
    public void theUserIsOnThe(String page) {
        switch (page) {
            case "dashboard":
                loginPage.loginAsLibrary();

                break;
            case "books":
                loginPage.loginAsStudent();

                break;
        }
    }

    @When("user clicks to logout")
    public void userClicksToLogout() {
        BrowserUtils.waitForClickability(landing.accountUserName,10).click();
        landing.logOutButton.click();
    }




    @Then("user will land on login page and title will be {string}")
    public void userWillLandOnLoginPageAndTitleWillBe(String expectedTitle) {
        String actualTitle= driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);
    }
}
