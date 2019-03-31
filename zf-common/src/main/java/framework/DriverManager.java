package framework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class DriverManager {

	private static WebDriver driver = null;
	private static String url = null;
	private static final Long DELAYTIME = (EnvironmentManager.getDelayTime());


	/**
	 * This static method and will return the driver instance
	 */
	public static WebDriver getDriverInstance() {

		return driver;
	}


	/**
	 * This static method and will check the browser and open the url present in the property file
	 * @param browserName
	 *            Name of the browser
	 */
	public static void getDriver(String browserName) {
		url = EnvironmentManager.getUrl();
		switch (browserName) {
		case "FIREFOX":
			System.setProperty("webdriver.gecko.driver", "src/main/resources/bin/geckodriver.exe");
			FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("network.proxy.type", ProxyType.AUTODETECT.ordinal());
            FirefoxOptions option = new FirefoxOptions();
            option.setProfile(profile);
            driver = new FirefoxDriver(option);
            ExtentReport.info("FIREFOX browser launched successfully" +"\n" + "URL = " + url);
			break;
		case "CHROME":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/bin/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars");
			driver = new EventFiringWebDriver(new ChromeDriver(options));
			ExtentReport.info("CHROME browser launched successfully" +"\n" + "URL = " + url);
			break;
		case "IE":
			/**
			 * This DesiredCapabilities will automatically enable the
			 * "Enable Protected Mode Check-Box" for
			 * "Internet, Local Internet and Trusted sites" options in security
			 * tab for IE browser
			 */
			InternetExplorerOptions explorerOptions = new InternetExplorerOptions();
			explorerOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			System.setProperty("webdriver.ie.driver", "src/main/resources/bin/IEDriverServer.exe");
			
			driver = new EventFiringWebDriver(new InternetExplorerDriver(explorerOptions));
			ExtentReport.info("IE browser launched successfully" +"\n" + "URL = " + url);
			break;
		default:
			break;
		}
		openUrl();
	}

	/**
	 * This method will highlighted the fields on browser and delete the browser
	 * cache
	 */
	private static void openUrl() {
		driver.manage().timeouts().pageLoadTimeout(DELAYTIME, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get(url);
		driver.manage().window().maximize();
	}	

	public static void closeBrowser() {
		driver.close();
	}
	public static void closeAllBrowser() {
		driver.quit();
	}
}
