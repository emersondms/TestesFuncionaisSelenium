package tests;

import core.BaseTest;
import core.DriverFactory;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.MenuPage;
import pages.ResumoPage;

import java.util.List;

import static core.DriverFactory.getDriver;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ResumoTest extends BaseTest {

	private MenuPage menuPage = new MenuPage();
	private ResumoPage resumoPage = new ResumoPage();

	//@Test
	public void test1ExcluirMovimentacao() {
		menuPage.acessarTelaResumo();
		resumoPage.excluirMovimentacao();
		Assert.assertEquals("Movimentação removida com sucesso!", resumoPage.obterMensagemSucesso());
	}
	
	//@Test(expected=NoSuchElementException.class)
	@Test
	public void test2ResumoMensal() {
		menuPage.acessarTelaResumo();
		Assert.assertEquals("Seu Barriga - Extrato", getDriver().getTitle());
		/*DriverFactory.getDriver().findElement(By.xpath("//*[@id='tabelaExtrato']/tbody/tr"));
		
		try{
			DriverFactory.getDriver().findElement(By.xpath("//*[@id='tabelaExtrato']/tbody/tr"));
			Assert.fail();
		} catch (NoSuchElementException e) {
		}*/

		resumoPage.selecionarAno("2016");
		resumoPage.buscar();
		
		List<WebElement> elementosEncontrados = 
			DriverFactory.getDriver().findElements(By.xpath("//*[@id='tabelaExtrato']/tbody/tr"));

		Assert.assertEquals(0, elementosEncontrados.size());
	}
}
