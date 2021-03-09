package com.libraryAutomation.utilities;

import com.libraryAutomation.utilities.enumOptions.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {
    private static final ThreadLocal<WebDriver> driverPoll = new ThreadLocal<>();

    public static synchronized WebDriver getDriver() {

        if (driverPoll.get() == null) {
            synchronized (Driver.class) {
                Browser browser = Browser.valueOf(ConfigurationReader.getProperty("browser"));
                switch (browser) {
                    case remote_chrome:

                        try {
                            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                            desiredCapabilities.setBrowserName(BrowserType.CHROME);
                            desiredCapabilities.setCapability("platform", Platform.ANY);
                            URL url=new URL("http://18.212.199.254:4444/wd/hub");
                            driverPoll.set(new RemoteWebDriver(url, desiredCapabilities));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case remote_firefox:
                        try {
                            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                            desiredCapabilities.setBrowserName(BrowserType.FIREFOX);
                            driverPoll.set(new RemoteWebDriver(new URL("http://100.27.21.56:4444/wd/hub"), desiredCapabilities));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case chrome:
                        WebDriverManager.chromedriver().setup();
                        driverPoll.set(new ChromeDriver());
                        driverPoll.get().manage().window().maximize();
                        //driverPoll.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                        break;
                    case fireFox:
                        WebDriverManager.firefoxdriver().setup();
                        driverPoll.set(new FirefoxDriver());
                        driverPoll.get().manage().window().maximize();
                       // driverPoll.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                        break;
                    case safari:
                        driverPoll.set(new SafariDriver());
                        driverPoll.get().manage().window().maximize();
                        //driverPoll.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                        break;
                }
            }
        }
        return driverPoll.get();
    }

    public static void closeDriver() {
        if (driverPoll.get() != null) {
            driverPoll.get().quit();
            driverPoll.remove();
        }
    }

}
