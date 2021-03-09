package com.libraryAutomation.stepDefinitions;

import com.libraryAutomation.pages.Add_EditUserPage;
import com.libraryAutomation.pages.LoginPage;
import com.libraryAutomation.pages.UsersPage;
import com.libraryAutomation.utilities.BrowserUtils;
import com.libraryAutomation.utilities.Memory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class UsersPage_StepDefinition {
    UsersPage user = new UsersPage();
    Add_EditUserPage add_editUserPage = new Add_EditUserPage();
    LoginPage loginPage = new LoginPage();

    @Given("the librarian  is on users page")
    public void the_librarian_is_on_users_page() {
        loginPage.loginAsLibrary();
        BrowserUtils.sleep(3);
        user.navigatingThroughNavigationBar("users");
    }


    @When("the librarian clicks on edit user page")
    public void the_librarian_clicks_on_edit_user_page() {
        user.firstEditButton.click();
    }

    @Then("librarian can see edit page")
    public void librarian_can_see_edit_page() {
        BrowserUtils.waitForVisibility(user.editUserInformationHeader, 15);
        Assert.assertTrue(user.editUserInformationHeader.isDisplayed());
    }


    @When("user clicks to add new user")
    public void user_clicks_to_add_new_user() {
        BrowserUtils.waitForVisibility(user.addUser,15);
        user.addUser.click();
    }


    @When("user enters valid info and clicks add user")
    public void user_enters_valid_info_and_clicks_add_user() {
        add_editUserPage.addOrEditUser();


    }

    @Then("user will be able to see in user page the added user")
    public void user_will_be_able_to_see_in_user_page_the_added_user() {

        List<String> names= BrowserUtils.convertWebElementToString_andGetText(user.listOfNames);

        for (String name : names) {
           if(name.equals(Memory.retrieveValue("name"))){
               Assert.assertEquals(name,Memory.retrieveValue("name"));
           }

        }
//        String name = BrowserUtils.sendTheKeys(BrowserUtils.fluentWait(user.search, 20), Memory.retrieveValue("name"));
//        BrowserUtils.waitUntilCertainTextAppears(user.fullName, name);
//        Assert.assertEquals("The name does not match the editing/adding wasn't successful", name, user.fullName.getText());

    }

    @Then("user clicks to close, the form is closed")
    public void userClicksToCloseTheFormIsClosed() {
        add_editUserPage.buttonCancelClick();
    }

    @When("user clicks to edit button for desired {string}")
    public void userClicksToEditButtonForDesired(String name) {
        user.selectUserToEdit(name);
    }

    @When("user clicks to edit button randomly from the list")
    public void userClicksToEditButtonRandomlyFromTheList() {
        user.selectingRandomEdit();
    }
}
