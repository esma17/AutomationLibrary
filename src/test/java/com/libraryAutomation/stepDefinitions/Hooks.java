package com.libraryAutomation.stepDefinitions;

import com.libraryAutomation.utilities.Driver;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    @After
    public void tearDown(Scenario scenario){

        if(scenario.isFailed()){
            byte[]screenshot=((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png",scenario.getName());
        }
      Driver.closeDriver();
    }



}
