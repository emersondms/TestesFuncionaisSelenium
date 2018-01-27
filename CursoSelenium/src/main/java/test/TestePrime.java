package test;

import core.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static core.DriverFactory.*;

public class TestePrime {

    private DSL dsl;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver",
            System.getProperty("user.dir") + "/src/main/resources/chromedriver");

        getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
        dsl = new DSL();
    }

    @Test
    public void deveInteragirComRadioPrime() {
        dsl.clicarRadio(By.xpath("//input[@id='j_idt117:console:0']/../..//span"));
        Assert.assertTrue(dsl.isRadioMarcado("j_idt117:console:0"));
        dsl.clicarRadio(By.xpath("//label[.='PS4']/..//span"));
        Assert.assertTrue(dsl.isRadioMarcado("j_idt117:console:1"));
    }

    @Test
    public void deveInteragirComSelectPrime() {
        getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
        dsl.selecionarComboPrime("j_idt117:console", "PS4");
        Assert.assertEquals("PS4", dsl.obterTexto("j_idt117:console_label"));
    }

    @After
    public void finish() {
        killDriver();
    }
}
