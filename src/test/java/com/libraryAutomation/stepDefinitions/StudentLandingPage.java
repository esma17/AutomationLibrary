package com.libraryAutomation.stepDefinitions;

import com.libraryAutomation.pages.LoginPage;
import com.libraryAutomation.pages.PageBase;
import com.libraryAutomation.utilities.BrowserUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;


import java.util.List;

public class StudentLandingPage extends PageBase {
LoginPage loginPage=new LoginPage();
    @Given("the student on the home page")
    public void the_student_on_the_home_page() {
      loginPage.loginAsStudent();
    }



    @Then("the user should see following modules in dashboard page")
    public void theUserShouldSeeFollowingModulesInDashboardPage(List<String> headers) {
        BrowserUtils.waitForVisibility(navigationBar,15);
        Assert.assertEquals(headers, BrowserUtils.convertWebElementToString_andGetText(navigationBar));

    }
}
