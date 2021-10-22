package br.com.sulamerica.pages;

import automacao.core.UtilidadesSelenium;
import org.apache.commons.configuration.ConfigurationException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

import static automacao.core.BrowserDriver.getCurrentDriver;

public class UsuarioSeguroConfiguracaoPage extends UtilidadesSelenium {

    public static String getTitle() throws ConfigurationException, IOException, InterruptedException {
        return getTexto(By.xpath("//p[@data-testid='firstName-content']"));
    }
}
