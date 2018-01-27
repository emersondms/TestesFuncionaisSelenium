package core;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;

public class BaseTest {

    @Rule
    public TestName testName = new TestName();

    @After
    public void finish() throws IOException {
        TakesScreenshot ts = (TakesScreenshot) getDriver();
        File screenShot = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenShot, new File(
            "target" + File.separator + " screenshot" + File.separator +
            testName.getMethodName() + ".jpg"));

        if (Propriedades.FECHAR_BROWSER) killDriver();
    }
}
