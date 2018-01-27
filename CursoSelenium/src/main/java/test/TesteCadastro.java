package test;

import core.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import page.CampoTreinamentoPage;

import static core.DriverFactory.*;

public class TesteCadastro extends BaseTest {

    private CampoTreinamentoPage page;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver",
            System.getProperty("user.dir") + "/src/main/resources/chromedriver");

        getDriver().get("file:///" + System.getProperty("user.dir") +
            "/src/main/resources/campo-treinamento/componentes.html");

        page = new CampoTreinamentoPage();
    }

    @Test
    public void deveRealizarCadastroComSucesso() {
        page.setNome("Emerson");
        page.setSobrenome("Delmondes");
        page.setSexoMasculino();
        page.setComidaPizza();
        page.setEscolaridade("Mestrado");
        page.setEsporte("Natacao");
        page.cadastrar();
        Assert.assertEquals("Cadastrado!", page.obterResultadoCadastro());
        Assert.assertEquals("Emerson", page.obterNomeCadastro());
        Assert.assertEquals("Delmondes", page.obterSobrenomeCadastro());
        Assert.assertEquals("Masculino", page.obterSexoCadastro());
        Assert.assertEquals("Pizza", page.obterComidaCadastro());
        Assert.assertEquals("mestrado", page.obterEscolaridadeCadastro());
        Assert.assertEquals("Natacao", page.obterEsportesCadastro());
    }
}
