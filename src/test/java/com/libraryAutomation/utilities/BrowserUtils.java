package com.libraryAutomation.utilities;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.Key;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class BrowserUtils {

    public static void sleep(int seconds) {
        seconds *= 1000;

        try {
            Thread.sleep(seconds);

        } catch (InterruptedException e) {
            System.out.println("something happen in sleep method");
        }
    }

    public static void hoover(WebElement element) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
    }

    public static void clickOn(WebElement element, int timeout) {
        final WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.elementToBeClickable(element)));
        element.click();
    }
    public static void textToBePresent(WebElement element,  String text) {
        final WebDriverWait wait = new WebDriverWait(Driver.getDriver(),20);
        wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.textToBePresentInElement(element, text)));
        element.click();
    }

    //this method accepts List<WebElement> and returns List<String>
    public static List<String> convertWebElementToString_andGetText(List<WebElement> elements) {
        List<String> textsOfWebElement = new LinkedList<>();

        for (WebElement element : elements) {
            textsOfWebElement.add(element.getText());
        }
        return textsOfWebElement;
    }

    // The method takes an element and takes the text you want to send by waiting explicitly and returns text as a String
    public static String sendTheKeys(WebElement element, String text,int timeout){
        final WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.visibilityOf(element)));
        element.sendKeys(text);
        return text;
    }

    // wait method for element until Visible by WebElement
    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeToWaitInSec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    public static List<WebElement> waitForVisibility(List<WebElement> element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeToWaitInSec);
        return wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public static boolean waitForInVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeToWaitInSec);
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }

    // wait method for webElement until Clickable
    public static WebElement waitForClickability(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));

    }

    public static WebElement fluentWait(final WebElement webElement, int timeinsec) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(Driver.getDriver())
                .withTimeout(Duration.ofSeconds(timeinsec))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(StaleElementReferenceException.class);
        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return webElement;
            }
        });
        return element;
    }

    public static String sendTheKeys(WebElement element,String text){
        element.clear();
        element.sendKeys(text);
        return text;
    }

    public void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }

    public static void waitForStaleElement(WebElement element) {
        int y = 0;
        while (y <= 15) {
            if (y == 1)
                try {
                    element.isDisplayed();
                    break;
                } catch (StaleElementReferenceException st) {
                    y++;
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (WebDriverException we) {
                    y++;
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }
    }


    public static WebElement waitUntilCertainTextAppears(WebElement element,String text){
        WebDriverWait wait=new WebDriverWait(Driver.getDriver(),20);
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.textToBePresentInElement(element,text)));
    return element;
    }




}
