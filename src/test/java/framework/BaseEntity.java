package framework;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseEntity {

    private static final String strUrl = "url";
    private static final String strLang = "lang";
    private static final String strTimeOut = "timeout";
    private static String nameFileForWindows="/SteamSetup.exe";
    private static String nameFileForLinux="/steam_latest.deb";

    protected static ConfigReader configReader;
    protected static ConfigReader langReader;
    protected static WebDriver driver;
    private static String OS = System.getProperty("os.name").toLowerCase().substring(0, 4);

    private  void deleteFile() {
        String strNameGame="";
        switch (OS) {
            case "linu": {
               strNameGame=nameFileForLinux;
               break;
            }
            case "wind": {
                strNameGame=nameFileForWindows;
                break;
            }
        }
        File file = null;

        try {
            System.out.println(new File(configReader.getProperties("pathDownload").concat(strNameGame)).getCanonicalPath());
            file = new File(new File(configReader.getProperties("pathDownload").concat(strNameGame)).getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (file.exists())
            file.delete();

    }

    @BeforeTest
    public void before() {
        configReader = new ConfigReader("config");
        langReader = new ConfigReader(configReader.getProperties(strLang));
        deleteFile();
        driver = BrowserFactory.getSingletonBrowser();
        driver.get(configReader.getProperties(strUrl));
        driver.manage().window().setSize(new Dimension(1920,1080));
        driver.manage().timeouts().implicitlyWait(Long.parseLong(configReader.getProperties(strTimeOut)), TimeUnit.SECONDS);
    }

    @AfterTest
    public void after() {
            driver.quit();
    }


}
