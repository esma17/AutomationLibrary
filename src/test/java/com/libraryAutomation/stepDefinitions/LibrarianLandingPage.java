package com.libraryAutomation.stepDefinitions;

import com.libraryAutomation.pages.LoginPage;
import com.libraryAutomation.pages.PageBase;
import com.libraryAutomation.utilities.BrowserUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;

public class LibrarianLandingPage extends PageBase {
    LoginPage loginPage = new LoginPage();

    @Given("the librarian on the homepage")
    public void the_librarian_on_the_homepage() {
        loginPage.loginAsLibrary();
    }


    @Then("the user should see following modules")
    public void the_user_should_see_following_modules(List<String> headers) {
        Assert.assertEquals(headers, BrowserUtils.convertWebElementToString_andGetText(navigationBar));

    }


    @Then("the user should see following modules in books Page")
    public void theUserShouldSeeFollowingModulesInBooksPage(List<String>headers) {
        BrowserUtils.waitForVisibility(navigationBar,15);
        Assert.assertEquals(headers, BrowserUtils.convertWebElementToString_andGetText(navigationBar));

    }
}
