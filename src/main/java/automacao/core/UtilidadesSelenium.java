package automacao.core;

import cucumber.api.Scenario;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static automacao.core.BrowserDriver.getCurrentDriver;

public class UtilidadesSelenium {

    static boolean highLight = true;

    public static String getTexto(By locator) throws InterruptedException, ConfigurationException, IOException {
        WebElement obj = getCurrentDriver().findElement(locator);
        highLighterMethod(getCurrentDriver(), obj);
        return obj.getText();
    }

    public static String getAttribute(By locator, String atributo) throws InterruptedException, ConfigurationException, IOException {
        WebElement obj = getCurrentDriver().findElement(locator);
        highLighterMethod(getCurrentDriver(), obj);
        return obj.getAttribute(atributo);
    }

    public static void setAttribute(By locator, String atributo, String info) throws ConfigurationException, InterruptedException, IOException {
        WebElement obj = getCurrentDriver().findElement(locator);
        highLighterMethod(getCurrentDriver(), obj);
        ((JavascriptExecutor) getCurrentDriver()).executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", obj, atributo, info);
    }

    public static String getValue(By locator) throws InterruptedException, ConfigurationException, IOException {
        WebElement obj = getCurrentDriver().findElement(locator);
        highLighterMethod(getCurrentDriver(), obj);
        return obj.getAttribute("value");
    }

    public static void cliqueNoElemento(By locator) throws InterruptedException, ConfigurationException, IOException {
        WebElement obj = getCurrentDriver().findElement(locator);
        highLighterMethod(getCurrentDriver(), obj);
        obj.click();
    }

    public static void mouserNoElemento(By locator) throws InterruptedException, ConfigurationException, IOException {
        Actions action = new Actions(getCurrentDriver());
        WebElement obj = getCurrentDriver().findElement(locator);
        highLighterMethod(getCurrentDriver(), obj);
        action.moveToElement(obj).build().perform();
    }

    public static void cliqueNoElementoJavaScripts(By locator) throws InterruptedException, ConfigurationException, IOException {
        WebElement obj = getCurrentDriver().findElement(locator);
        highLighterMethod(getCurrentDriver(), obj);
        ((JavascriptExecutor) getCurrentDriver()).executeScript("arguments[0].click();", obj);
    }

    public static void digiteNoElementoJavaScripts(By locator, String info) throws InterruptedException, ConfigurationException, IOException {
        WebElement obj = getCurrentDriver().findElement(locator);
        highLighterMethod(getCurrentDriver(), obj);
        obj.clear();
        ((JavascriptExecutor) getCurrentDriver()).executeScript("arguments[0].value='" + info + "';", obj);
    }

    public static void digiteNoElemento(By locator, String info) throws InterruptedException, ConfigurationException, IOException {
        WebElement obj = getCurrentDriver().findElement(locator);
        highLighterMethod(getCurrentDriver(), obj);
        obj.clear();
        obj.sendKeys(Keys.CONTROL+"A");
        obj.sendKeys(info);
        obj.sendKeys(Keys.TAB);
    }

    public static void digiteCaracterNoElemento(By locator, String info) throws InterruptedException, ConfigurationException, IOException {
        WebElement obj = getCurrentDriver().findElement(locator);
        highLighterMethod(getCurrentDriver(), obj);
        obj.clear();
        obj.click();
        obj.sendKeys(Keys.HOME);
        for (int i = 0; i < info.length(); i++) {
            char c = info.charAt(i);
            String letra = new StringBuilder().append(c).toString();
            obj.sendKeys(letra);
            Thread.sleep(100);
        }
    }

    public static void scrollElement(By locator) throws ConfigurationException, IOException, InterruptedException {
        WebElement obj = getCurrentDriver().findElement(locator);
        highLighterMethod(getCurrentDriver(), obj);
        ((JavascriptExecutor) getCurrentDriver()).executeScript("arguments[0].scrollIntoView(true);", obj);
        Thread.sleep(200);
    }

    public static void acessarFrame(By locator) throws ConfigurationException, IOException {
        WebElement element = getCurrentDriver().findElement(locator);
        getCurrentDriver().switchTo().defaultContent();
        getCurrentDriver().switchTo().frame(element);
    }

    public static void highLighterMethod(WebDriver driver, WebElement element) throws ConfigurationException, IOException, InterruptedException {
        if (highLight) {
            JavascriptExecutor js = (JavascriptExecutor) getCurrentDriver();
            js.executeScript("arguments[0].setAttribute('style', 'background: ; border: 2px solid red;');", element);
        }
    }

    public static boolean elementIsPresent(By locator) throws ConfigurationException, IOException, InterruptedException {
        boolean resp = false;
        getCurrentDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        if (getCurrentDriver().findElements(locator).size() > 0 && getCurrentDriver().findElement(locator).isDisplayed()) {
//            scrollElement(locator);
            highLighterMethod(getCurrentDriver(), getCurrentDriver().findElement(locator));
            resp = true;
        }
        getCurrentDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return resp;
    }

    public static void openNewTab(String url) throws ConfigurationException, IOException {
        ((JavascriptExecutor) getCurrentDriver()).executeScript("window.open()");
        selecionarUltimaJanelaAberta();
        acessarUrl(url);
    }

    public static void fecharBrowser() throws ConfigurationException, IOException, InterruptedException {
        getCurrentDriver().close();
        Thread.sleep(1000);
        selecionarUltimaJanelaAberta();
    }

    public static void selecionarUltimaJanelaAberta() throws ConfigurationException, IOException {
        List<String> abas = new ArrayList<>(getCurrentDriver().getWindowHandles());
        getCurrentDriver().switchTo().window(abas.get(abas.size() - 1));
    }

    public static void atualizaBrowser() throws ConfigurationException, IOException {
        getCurrentDriver().navigate().refresh();
    }

    public static void acessarUrl(String url) throws ConfigurationException, IOException {
        getCurrentDriver().get(url);
    }

    public static List<WebElement> quantidadeElementos(By locator) throws ConfigurationException, IOException, InterruptedException {
//        aguardeElementoAparecerTela(locator);
        List<WebElement> locators = getCurrentDriver().findElements(locator);
        List<WebElement> elements = new ArrayList<>();
        for (WebElement el : locators) {
            if (el.isDisplayed()) {
                elements.add(el);
            }
        }
        return elements;
    }

    public static void takeScreenShot(Scenario scenario) throws WebDriverException, ConfigurationException, IOException {
        try {
            final byte[] screenshot = ((TakesScreenshot) BrowserDriver.getCurrentDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage(), ioe);
        }
    }

    public static void takeScreenShot(String pathName) throws WebDriverException, ConfigurationException, IOException {
        File screenShot = ((TakesScreenshot) getCurrentDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenShot, new File(pathName));
        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage(), ioe);
        }
    }
}
