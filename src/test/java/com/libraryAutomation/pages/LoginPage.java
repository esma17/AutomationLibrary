package com.libraryAutomation.pages;

import com.libraryAutomation.utilities.BrowserUtils;
import com.libraryAutomation.utilities.ConfigurationReader;
import com.libraryAutomation.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageGenerator  {

    @FindBy(id = "login-form")
    public WebElement loginPage;
    @FindBy(xpath = "//input[@id='inputEmail']")
    public WebElement usernameInput;
    @FindBy(xpath = "//input[@id='inputPassword']")
    public WebElement passwordInput;
    @FindBy(xpath = "//button[.='Sign in']")
    public WebElement signInButton;

    public WebElement getSignInButton(){
        return BrowserUtils.waitForVisibility(signInButton,20);
    }

    public void login(String username, String password) {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        signInButton.click();

    }
    public void loginAsLibrary() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        String username = ConfigurationReader.getProperty("libUserName");
        String password = ConfigurationReader.getProperty("libPassword");
        usernameInput.sendKeys(username);
       passwordInput.sendKeys(password);
       signInButton.click();

    }
    public void loginAsStudent() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        String username = ConfigurationReader.getProperty("stuUserName11");
        String password = ConfigurationReader.getProperty("stuPassword11");
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        signInButton.click();

    }
    public void loginLibrary1(){
        Driver.getDriver().get(ConfigurationReader.getProperty("library1_url"));
        String username = ConfigurationReader.getProperty("library1_user");
        String password = ConfigurationReader.getProperty("library1_password");
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        signInButton.click();
    }



}
