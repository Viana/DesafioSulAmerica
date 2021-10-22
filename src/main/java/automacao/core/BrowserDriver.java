package automacao.core;


import org.apache.commons.configuration.ConfigurationException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BrowserDriver {
    private static WebDriver driver;
    static String arquivoBaixado = System.getProperty("user.dir") + "/target/arq";

    public synchronized static WebDriver getCurrentDriver() throws ConfigurationException, IOException {
        if (null == driver) {
            BrowserName browser;
            if (System.getProperty("browser") == null) {
                browser = BrowserName.C1;
            } else {
                browser = BrowserName.valueOf(System.getProperty("browser").toUpperCase());
            }
            try {
                driver = browser.getBrowser();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            } finally {
                Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                    public void run() {
                        try {
                            fecharBrowser();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }));
            }
        }
        return driver;
    }

    public static void fecharBrowser() throws IOException {
        try {
            if (null != driver) {
                driver.manage().deleteAllCookies();
                driver.quit();
            }
        } catch (UnreachableBrowserException ignore) {
        }
        driver = null;
    }
}
