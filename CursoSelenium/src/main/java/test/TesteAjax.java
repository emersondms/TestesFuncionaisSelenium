package test;

import core.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static core.DriverFactory.*;

public class TesteAjax {

    private DSL dsl;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver",
            System.getProperty("user.dir") + "/src/main/resources/chromedriver");

        getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
        dsl = new DSL();
    }

    @Test
    public void deveUtilizarEsperaFixa() throws InterruptedException {
        dsl.escrever("j_idt116:name", "Teste");
        dsl.clicarBotao("j_idt116:j_idt119");
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        //wait.until(ExpectedConditions.textToBe(By.id("j_idt116:display"), "Teste"));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt129")));
        Assert.assertEquals("Teste", dsl.obterTexto("j_idt116:display"));
    }

    @After
    public void finish() {
        killDriver();
    }
}
