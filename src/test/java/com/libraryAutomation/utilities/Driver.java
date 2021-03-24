package com.libraryAutomation.utilities;

import com.libraryAutomation.utilities.enumOptions.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.URL;

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
                            URL url = new URL("http://34.228.73.245:4444/wd/hub");
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
                    case headless_chrome:
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions options = new ChromeOptions();
                        options.addArguments("--headless");
                        driverPoll.set((new ChromeDriver(options)));
                        driverPoll.get().manage().window().setSize(new Dimension(1200, 900));
                        break;
                    case firefox_headless:
                        WebDriverManager.firefoxdriver().setup();
                        driverPoll.set(new FirefoxDriver(new FirefoxOptions().setHeadless(true)));
                        driverPoll.get().manage().window().setSize(new Dimension(1200, 900));
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
