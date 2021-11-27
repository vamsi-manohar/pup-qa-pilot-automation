package com.productsup.platform.factories;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.productsup.platform.enums.ConfigProperties;
import com.productsup.platform.utils.PropertyUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author Vamsi Manohar 22-Sep-2021 6:45:53 am
 */

public final class DriverFactory {

	private DriverFactory() {

	}

	/**
	 * 
	 * @author K Vamsi Manohar
	 * @param browser
	 * @return
	 * @throws MalformedURLException
	 */

	public static WebDriver getDriver(String browser) throws MalformedURLException {
		WebDriver driver = null;
		String runMode = PropertyUtils.getValue(ConfigProperties.RUN_MODE);

		if (browser.equalsIgnoreCase("chrome")) {
			if (runMode.equalsIgnoreCase("remote")) {
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setBrowserName(BrowserType.CHROME);

				driver = new RemoteWebDriver(new URL(""), cap);

			}

			else {
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				
				//options.addArguments("--incognito");
				//options.addArguments("--headless");
	
				driver = new ChromeDriver(options);
			


			}
		} else if (browser.equalsIgnoreCase("firefox")) {
			if (runMode.equalsIgnoreCase("remote")) {
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setBrowserName(BrowserType.FIREFOX);

				driver = new RemoteWebDriver(new URL(PropertyUtils.getValue(ConfigProperties.SELENIUM_GRID_URL)), cap);

			}

			else {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}

		}
		return driver;
	}

}
