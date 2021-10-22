package br.com.sulamerica.steps;

import automacao.core.UtilidadesSelenium;
import br.com.sulamerica.model.Usuario;
import br.com.sulamerica.pages.*;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.configuration.ConfigurationException;
import org.junit.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static automacao.core.BrowserDriver.getCurrentDriver;
import static org.junit.Assert.assertEquals;

public class FrontEndStep {

    Scenario scenario;
    Usuario usuario = new Usuario();

    @Before
    public void getScenarios(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("^acesso o site SulAmerica Contrata Fácil$")
    public void acessoOSiteSulAmericaContrataFácil() throws ConfigurationException, IOException {
        getCurrentDriver().get(ConfiguracaoProperties.getProp().getProperty("config.url"));
    }

    @Given("^clico na opção EU QUERO SIMULAR$")
    public void clicoNaOpçãoEUQUEROSIMULAR() throws ConfigurationException, IOException, InterruptedException {
        HomePage.buttonSimula();
    }


    @When("^no botão PRÓXIMO$")
    public void noBotãoPRÓXIMO() throws ConfigurationException, IOException, InterruptedException {
        DadosPessoaisPage.buttonProximo();
    }


    @When("^preencher os campos:$")
    public void preencherOsCampos(List<Map<Object, String>> dados) throws ConfigurationException, IOException, InterruptedException {
        usuario.setNome(dados.get(0).get("Qual o seu nome completo?"));
        usuario.setDataNascimento(dados.get(0).get("Qual a sua data de nascimento?"));
        usuario.setOcupacao(dados.get(0).get("Qual sua ocupação atual?"));
        DadosPessoaisPage.setInput(dados.get(0).keySet().toArray()[0].toString(), usuario.getNome());
        DadosPessoaisPage.setInput(dados.get(0).keySet().toArray()[1].toString(), usuario.getDataNascimento());
        DadosPessoaisPage.setInputCombo(dados.get(0).keySet().toArray()[2].toString(), usuario.getOcupacao());
    }

    @Then("^a tela de informações de contato deve informar o nome do usuário$")
    public void aTelaDeInformaçõesDeContatoDeveInformarONomeDoUsuário() throws ConfigurationException, IOException, InterruptedException {
        System.out.println(ContatoUsuarioPage.getTitle());
    }

    @Then("^a tela de informações de contato deve informar primeiro nome do usuário seguido da frase \"([^\"]*)\"$")
    public void aTelaDeInformaçõesDeContatoDeveInformarPrimeiroNomeDoUsuárioSeguidoDaFrase(String frase) throws Throwable {
        try {
            assertEquals(usuario.getNome().split(" ")[0] + ", " + frase, ContatoUsuarioPage.getTitle());
        } finally {
            UtilidadesSelenium.takeScreenShot(scenario);
        }
    }

    @When("^preencher os campos da tela de contato:$")
    public void preencherOsCamposDaTelaDeContato(List<Map<Object, String>> dados) throws ConfigurationException, IOException, InterruptedException {
        usuario.setTelefone(dados.get(0).get("Telefone (Celular)"));
        usuario.setEmail(dados.get(0).get("E-mail"));
        try {
            ContatoUsuarioPage.checkInput(dados.get(0).keySet().toArray()[0].toString().split(" ")[1]);
            ContatoUsuarioPage.checkInput(dados.get(0).keySet().toArray()[1].toString().split(" ")[1]);
            ContatoUsuarioPage.checkInput(dados.get(0).keySet().toArray()[2].toString().split(" ")[1]);
            ContatoUsuarioPage.setInput(dados.get(0).keySet().toArray()[3].toString(), usuario.getTelefone());
            ContatoUsuarioPage.setInput(dados.get(0).keySet().toArray()[4].toString(), usuario.getEmail());
            ContatoUsuarioPage.checkInput(dados.get(0).keySet().toArray()[5].toString());
        } finally {
            UtilidadesSelenium.takeScreenShot(scenario);
        }
    }

    @And("^no botão ENCONTRAR MEU SEGURO$")
    public void noBotãoENCONTRARMEUSEGURO() throws ConfigurationException, IOException, InterruptedException {
        ContatoUsuarioPage.buttonEncontrarSeguro();
    }

    @Then("^a tela de configuração de seguro deve ser apresentada com o nome do usuário seguido da frase \"([^\"]*)\"$")
    public void aTelaDeConfiguraçãoDeSeguroDeveSerApresentadaComONomeDoUsuárioSeguidoDaFrase(String frase) throws Throwable {
        try {
            assertEquals(usuario.getNome().split(" ")[0] + ", " + frase, UsuarioSeguroConfiguracaoPage.getTitle());
        } finally {
            UtilidadesSelenium.takeScreenShot(scenario);
        }
    }
}
