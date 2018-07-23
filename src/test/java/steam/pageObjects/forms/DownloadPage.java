package steam.pageObjects.forms;

import framework.BasePage;
import framework.BrowserFactory;
import framework.elements.Button;
import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;


public class DownloadPage extends BasePage {

    private String locatorDownload = "//a[@class='btn_medium btn_green_white_innerfade']//span";
    private static String nameFileForWindows="SteamSetup.exe";
    private static String nameFileForLinux="steam_latest.deb";
    private static String OS = System.getProperty("os.name").toLowerCase().substring(0, 4);

    public DownloadPage() {
        locatorPage = By.xpath(locatorDownload);
        isOpen(locatorPage);
    }


    public boolean checkDownloadFile() {

        boolean fileIsDownloaded = false;
        String fileName = "";
        switch (OS) {
            case "wind": {
                fileName =nameFileForWindows;
                break;
            }
            case "linu": {
                fileName =nameFileForLinux;
            }
        }
        while (!fileIsDownloaded) {
            File file = null;
            try {
                file = new File(new File("./src/test/resources/".concat(fileName)).getCanonicalPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (file.exists() && file.isFile())
                fileIsDownloaded = true;
        }
        return  fileIsDownloaded;

    }

    public void downloadSteam() {

        Button btnDownload = new Button(By.xpath(locatorDownload));
        btnDownload.click();

    }
}
