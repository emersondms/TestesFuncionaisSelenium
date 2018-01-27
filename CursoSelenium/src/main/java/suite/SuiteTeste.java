package suite;

import core.DriverFactory;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.TesteCadastro;
import test.TesteRegrasCadastro;

@RunWith(Suite.class)
@Suite.SuiteClasses({TesteCadastro.class, TesteRegrasCadastro.class})
public class SuiteTeste {

    @AfterClass
    public static void finalizaTudo() {
        DriverFactory.killDriver();
    }
}
