package testbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import utilities.TestUtil;

/*Base Test for Setting up driver and intiating Browser*/
public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static String resourceFolder = System.getProperty("user.dir") + "/src/test/resources";

	public TestBase() {

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(TestUtil.resourceFolder + "/config/Configuration.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void initialization() {

		
		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", resourceFolder + "/drivers/chromedriver 2");
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("FF")) {
			System.setProperty("webdriver.gecko.driver", resourceFolder + "/drivers/geckodriver.exe");
			driver = new FirefoxDriver();

		}

		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));

	}

	public void executeJavaScript(String perform, WebElement e) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(perform, e);
	}

	public String getWebElementText(WebElement e) {
		return e.getText();

	}

	public void exitDriver() {
		driver.quit();
	}

	public String getCurrenturl() {
		return driver.getCurrentUrl();
	}

}
