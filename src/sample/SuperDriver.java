package sample;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Created by tkittirudeekul on 5/13/16.
 */
public class SuperDriver {

    private static SuperDriver instance = null;
    private WebDriver driver;
    private WebDriver Chrome = null;

    private SuperDriver() {

    }

    public WebDriver openBrowser(Boolean max) {
        ChromeOptions options = new ChromeOptions();
        if (max) {
            options.addArguments("window-size=1980,1080");
        }
        System.setProperty("webdriver.chrome.driver", "/Users/tkittirudeekul/Downloads/chromedriver");
        if (Chrome == null) {
            driver = new ChromeDriver(options);
            Chrome = driver;
        } else if (Chrome != null) {
            driver = Chrome;
        }
        driver.manage().window().maximize();
        return driver;
    }

    public static SuperDriver getInstance() {
        if (instance == null) {
            instance = new SuperDriver();
        }
        return instance;
    }
}

