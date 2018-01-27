package test;

import core.DSL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;

public class TesteSincronismo {

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
    public void deveUtilizarEsperaFixa() throws InterruptedException {
        dsl.clicarBotao("buttonDelay");
        Thread.sleep(5000);
        dsl.escrever("novoCampo", "Deu certo?");
    }

    @Test
    public void deveUtilizarEsperaImplicita() {
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        dsl.clicarBotao("buttonDelay");
        dsl.escrever("novoCampo", "Deu certo?");
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    @Test
    public void deveUtilizarEsperaExplicita() {
        dsl.clicarBotao("buttonDelay");
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
        dsl.escrever("novoCampo", "Deu certo?");
    }

    @After
    public void finish() {
        killDriver();
    }
}
