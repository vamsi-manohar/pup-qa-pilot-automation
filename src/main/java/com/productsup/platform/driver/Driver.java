package com.productsup.platform.driver;

import java.net.MalformedURLException;
import java.util.Objects;

import com.productsup.platform.enums.ConfigProperties;
import com.productsup.platform.exceptions.BrowserInvocationFailedException;
import com.productsup.platform.factories.DriverFactory;
import com.productsup.platform.utils.PropertyUtils;


/**
 * Driver class is responsible for invoking and closing the browsers.
 * 
 * 
 * It is also responsible for 
 * setting the driver variable to DriverManager which handles the thread safety for the 
 * webdriver instance.
 * 
 * @author Vamsi Manohar
 *22-Sep-2021
 *6:18:31 am
 *
 *
 */



public final class Driver {

	/**
	 * Using private constructor to avoid external instantiation
	 */

	private Driver() {
	}


/**
 * Gets the browser value and initialize the browser based on that
 * @param browser  Value will be passed from Base Test class. Values can be chrome and firefox
 */
	public static void initDriver(String browser) {
		if (Objects.isNull(DriverManager.getDriver())) {
			try {
				DriverManager.setDriver(DriverFactory.getDriver(browser));
			} catch (MalformedURLException e) {
				throw new BrowserInvocationFailedException("Browser Invocation Failed");
			}

			//
			//DriverManager.getDriver().get(PropertyUtils.getValue(ConfigProperties.URL));
			DriverManager.getDriver().manage().window().maximize();

		}

	}

	/**
	 * Terminates the browser instance.
	 * Sets the thread local to default value, i.e null.
	 */
	public static void quitDriver() {
		if (Objects.nonNull(DriverManager.getDriver())) {
			DriverManager.getDriver().quit();
			System.out.println("Driver instance is closed");
			DriverManager.unload();
		}
	}

}
