package core;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
	
	//private static WebDriver driver;
	private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>() {
		@Override
		protected synchronized WebDriver initialValue() {
			try {
				return initDriver();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			return null;
		}
	};
	
	private DriverFactory() {}

	public static WebDriver getDriver() {
		return threadDriver.get();
	}
	
	public static WebDriver initDriver() throws MalformedURLException {
		System.setProperty("webdriver.chrome.driver",
			System.getProperty("user.dir") + "/src/main/resources/chromedriver");

		WebDriver driver = null;

		if (Propriedades.TIPO_EXECUCAO == Propriedades.TipoExecucao.LOCAL) {
			switch (Propriedades.BROWSER) {
				case FIREFOX: driver = new FirefoxDriver(); break;
				case CHROME: driver = new ChromeDriver(); break;
			}
		}

		if (Propriedades.TIPO_EXECUCAO == Propriedades.TipoExecucao.GRID) {
			DesiredCapabilities cap = null;
			switch (Propriedades.BROWSER) {
				case FIREFOX: cap = DesiredCapabilities.firefox(); break;
				case CHROME: cap = DesiredCapabilities.chrome(); break;
			}

			driver = new RemoteWebDriver(new URL("http://192.168.15.6:4444/wd/hub"), cap);
		}

		driver.manage().window().setSize(new Dimension(1200, 765));
		return driver;
	}

	public static void killDriver() {
		WebDriver driver = getDriver();
		if (driver != null) {
			driver.quit();
			driver = null;
		}

		if (threadDriver != null) threadDriver.remove();
	}
}
