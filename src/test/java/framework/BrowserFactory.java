package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class BrowserFactory extends BaseEntity {

    private static String OS = System.getProperty("os.name").toLowerCase().substring(0, 4);
    private static final String strBrowser = "browser";
    private static String pathChrome = "./src/test/resources/drivers/chromedriver";
    private static String pathFirefox = "./src/test/resources/drivers/geckodriver";
    private static String downloadFilepath;


    private BrowserFactory() {
    }


    private static DesiredCapabilities getOptionChrome() {
        try {
            downloadFilepath = new File(configReader.getProperties("pathDownload")).getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", downloadFilepath);
        chromePrefs.put("safebrowsing.enabled", "true");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        cap.setCapability(ChromeOptions.CAPABILITY, options);
        return cap;
    }

    private static DesiredCapabilities getOptionFirefox() {

        try {
            downloadFilepath = new File(configReader.getProperties("pathDownload")).getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FirefoxProfile options = new FirefoxProfile();
        options.setPreference("browser.download.folderList", 2);
        options.setPreference("browser.download.manager.showWhenStarting", false);
        options.setPreference("browser.download.dir", downloadFilepath);
        options.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/x-debian-package;application/octet-stream");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(FirefoxDriver.PROFILE, options);
        return capabilities;
    }


    public static WebDriver getSingletonBrowser() {
        if (driver == null) {
            switch (OS) {
                case "wind": {
                    pathChrome += ".exe";
                    pathFirefox += ".exe";
                }
            }
            switch (configReader.getProperties(strBrowser)) {
                case "Chrome": {
                    try {
                        System.setProperty("webdriver.chrome.driver", new File(pathChrome).getCanonicalPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return driver = new ChromeDriver(getOptionChrome());
                }
                case "Firefox": {
                    try {
                        System.setProperty("webdriver.gecko.driver", new File(pathFirefox).getCanonicalPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        return driver = new FirefoxDriver(getOptionFirefox());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return driver;
    }
}