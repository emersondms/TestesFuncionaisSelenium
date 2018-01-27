package suites;

import core.DriverFactory;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import pages.LoginPage;
import tests.*;

@RunWith(Suite.class)
@SuiteClasses({
	ContaTest.class, MovimentacaoTest.class, RemoverMovimentacaoContaTest.class,
	SaldoTest.class, ResumoTest.class
})
public class SuiteGeral {

	private static LoginPage page = new LoginPage();

	@BeforeClass
	public static void reset() {
		page.acessarTelaInicial();
		page.setEmail("emerson@delmondes");
		page.setSenha("emerson@delmondes");
		page.entrar();
		page.resetar();
		DriverFactory.killDriver();
	}
}
