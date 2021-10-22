package br.com.sulamerica.pages;

import automacao.core.UtilidadesSelenium;
import org.apache.commons.configuration.ConfigurationException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

import static automacao.core.BrowserDriver.getCurrentDriver;

public class DadosPessoaisPage extends UtilidadesSelenium {

    public DadosPessoaisPage() throws ConfigurationException, IOException, InterruptedException {
        getCurrentDriver().switchTo().defaultContent();
    }

    public static DadosPessoaisPage setInput(String label, String info) throws ConfigurationException, IOException, InterruptedException {
        digiteNoElemento(By.xpath("//form/div/div[contains(.,'"+label+"')]//input"), info);
        return PageFactory.initElements(getCurrentDriver(), DadosPessoaisPage.class);
    }

    public static DadosPessoaisPage setInputCombo(String label, String info) throws ConfigurationException, IOException, InterruptedException {
        cliqueNoElemento(By.xpath("//form/div/div[contains(.,'"+label+"')]//button[2]"));
        digiteCaracterNoElemento(By.xpath("//form/div/div[contains(.,'"+label+"')]//input[@aria-controls]"), info);
        cliqueNoElementoJavaScripts(By.xpath("//ul/li[@role and text()='"+info.toUpperCase()+"']"));
        return PageFactory.initElements(getCurrentDriver(), DadosPessoaisPage.class);
    }


    public static String getInputValue(String label, String info) throws ConfigurationException, IOException, InterruptedException {
        return getValue(By.xpath("//form//div[label[text()='" + label + "']]//input"));
    }

    public static String getInputMensagem(String label) throws ConfigurationException, IOException, InterruptedException {
        return getTexto(By.xpath("//form/div/div[contains(.,'"+label+"')]//p"));
    }

    public static DadosPessoaisPage buttonProximo() throws ConfigurationException, IOException, InterruptedException {
        cliqueNoElemento(By.xpath("//button/span[text()='PRÓXIMO']"));
        return PageFactory.initElements(getCurrentDriver(), DadosPessoaisPage.class);
    }


}
