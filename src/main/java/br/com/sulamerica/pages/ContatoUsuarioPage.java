package br.com.sulamerica.pages;

import automacao.core.UtilidadesSelenium;
import org.apache.commons.configuration.ConfigurationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

import static automacao.core.BrowserDriver.getCurrentDriver;

public class ContatoUsuarioPage extends UtilidadesSelenium {


    public static String getTitle() throws ConfigurationException, IOException, InterruptedException {
        return getTexto(By.xpath(" //div[@class='fade-in']//p[contains(@class,'subtitle1')]"));
    }

    public static ContatoUsuarioPage checkInput(String opcao) throws ConfigurationException, IOException, InterruptedException {
        cliqueNoElemento(By.xpath("//div/label[span/p[text()='" + opcao + "']]//input"));
        return PageFactory.initElements(getCurrentDriver(), ContatoUsuarioPage.class);
    }

    public static ContatoUsuarioPage getCheckInputMessage(String opcao) throws ConfigurationException, IOException, InterruptedException {
        getTexto(By.xpath("//div[label/span/p[text()='" + opcao + "']]/div/p"));
        return PageFactory.initElements(getCurrentDriver(), ContatoUsuarioPage.class);
    }

    public static ContatoUsuarioPage setInput(String label, String info) throws ConfigurationException, IOException, InterruptedException {
        digiteNoElemento(By.xpath("//form//div[label[text()='" + label + "']]//input"), info);
        return PageFactory.initElements(getCurrentDriver(), ContatoUsuarioPage.class);
    }

    public static ContatoUsuarioPage getInputValue(String label, String info) throws ConfigurationException, IOException, InterruptedException {
        getValue(By.xpath("//form//div[label[text()='" + label + "']]//input"));
        return PageFactory.initElements(getCurrentDriver(), ContatoUsuarioPage.class);
    }

    public static ContatoUsuarioPage getInputMensagem(String label, String info) throws ConfigurationException, IOException, InterruptedException {
        getTexto(By.xpath("//form//div[div/label[text()='" + label + "']]//p"));
        return PageFactory.initElements(getCurrentDriver(), ContatoUsuarioPage.class);
    }

    public static DadosPessoaisPage buttonEncontrarSeguro() throws ConfigurationException, IOException, InterruptedException {
        scrollElement(By.xpath("//button/span[text()='ENCONTRAR MEU SEGURO']"));
        cliqueNoElemento(By.xpath("//button/span[text()='ENCONTRAR MEU SEGURO']"));
        WebElement element = getCurrentDriver().findElement(By.xpath("//p[text()='Buscando seu seguro...']"));
        new WebDriverWait(getCurrentDriver(), 10)
                .until(ExpectedConditions.invisibilityOf(element));
        return PageFactory.initElements(getCurrentDriver(), DadosPessoaisPage.class);
    }


}

