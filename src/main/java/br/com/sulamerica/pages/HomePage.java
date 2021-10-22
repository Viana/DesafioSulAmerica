package br.com.sulamerica.pages;

import automacao.core.UtilidadesSelenium;
import org.apache.commons.configuration.ConfigurationException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

import static automacao.core.BrowserDriver.getCurrentDriver;

public class HomePage extends UtilidadesSelenium {

    public static DadosPessoaisPage buttonSimula() throws ConfigurationException, IOException, InterruptedException {
        cliqueNoElemento(By.xpath("//button/span[text()='EU QUERO SIMULAR']"));
        return PageFactory.initElements(getCurrentDriver(), DadosPessoaisPage.class);
    }


}
