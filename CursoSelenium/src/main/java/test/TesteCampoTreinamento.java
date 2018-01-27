package test;

import core.DSL;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static core.DriverFactory.*;

public class TesteCampoTreinamento {

    private DSL dsl;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver",
            System.getProperty("user.dir") + "/src/main/resources/chromedriver");

        getDriver().get("file:///" + System.getProperty("user.dir") +
            "/src/main/resources/campo-treinamento/componentes.html");

        dsl = new DSL();
    }

    @Test
    public void testTextField() {
        dsl.escrever("elementosForm:nome", "Teste");
        Assert.assertEquals("Teste", dsl.obterValorCampo("elementosForm:nome"));
    }

    @Test
    public void testTextFieldDuplo() {
        dsl.escrever("elementosForm:nome", "Emerson");
        Assert.assertEquals("Emerson", dsl.obterValorCampo("elementosForm:nome"));
        dsl.escrever("elementosForm:nome", "Delmondes");
        Assert.assertEquals("Delmondes", dsl.obterValorCampo("elementosForm:nome"));
    }

    @Test
    public void deveInteragirComTextArea() {
        dsl.escrever("elementosForm:sugestoes", "Teste\nTeste");
        Assert.assertEquals("Teste\nTeste", dsl.obterValorCampo("elementosForm:sugestoes"));
    }

    @Test
    public void deveInteragirComRadioButton() {
        dsl.clicarRadio("elementosForm:sexo:0");
        Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
    }

    @Test
    public void deveInteragirComCheckbox() {
        dsl.clicarCheck("elementosForm:comidaFavorita:2");
        Assert.assertTrue(dsl.isCheckMarcado("elementosForm:comidaFavorita:2"));
    }

    @Test
    public void deveInteragirComCombo() {
        dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
        Assert.assertEquals("2o grau completo", dsl.obterValorCombo("elementosForm:escolaridade"));
    }

    @Test
    public void deveVerificarValoresCombo() {
        Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
        Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
    }

    @Test
    public void deveVerificarValoresComboMultiplo() {
        dsl.selecionarCombo("elementosForm:esportes", "Natacao");
        dsl.selecionarCombo("elementosForm:esportes", "Corrida");
        dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");

        List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
        Assert.assertEquals(3, opcoesMarcadas.size());

        dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
        opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
        Assert.assertEquals(2, opcoesMarcadas.size());
        Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));
    }

    @Test
    public void deveInteragirComBotoes() {
        dsl.clicarBotao("buttonSimple");
        Assert.assertEquals("Obrigado!", dsl.obterValueElemento("buttonSimple"));
    }

    @Test
    @Ignore
    public void deveInteragirComLinks() {
        dsl.clicarLink("Voltar");
        Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
    }

    @Test
    public void deveBuscarTextosNaPagina() {
        Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",
            dsl.obterTexto(By.className("facilAchar"))
        );
    }

    @Test
    public void testJavascript() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("alert('Teste')");
        js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via js'");
        js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");
        WebElement element = getDriver().findElement(By.id("elementosForm:nome"));
        js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
    }

    @Test
    public void deveClicarBotaoTabela() {
        dsl.clicarBotaoTabela(
            "Nome", "Maria", "Botao", "elementosForm:tableUsuarios"
        );
    }

    @After
    public void finish() {
        killDriver();
    }
}
