package br.com.sulamerica.steps;

import automacao.core.BrowserDriver;
import automacao.core.UtilidadesSelenium;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.apache.commons.configuration.ConfigurationException;

import java.io.IOException;

public class HookAfter {
    @After
    public void after(Scenario scenario) throws ConfigurationException, IOException, InterruptedException {
        if (scenario.isFailed()) {
            UtilidadesSelenium.takeScreenShot(scenario);
        }
        BrowserDriver.fecharBrowser();
    }
}
