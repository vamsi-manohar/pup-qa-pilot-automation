package com.productsup.platform.driver;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.Uninterruptibles;
import com.productsup.platform.enums.BrowserType;
import com.productsup.platform.factories.DriverFactorySupplier;


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
 * @param browser  Value will be passed from Base Test class. Values can be chrome, firefox,safari and edge
 */
	public static void initDriver(String browser) {
		if (Objects.isNull(DriverManager.getDriver())) {
			//DriverManager.setDriver(DriverFactory.getDriver(browser));
			BrowserType browserType=null;
			switch(browser)
			{
				case "chrome":
					browserType = BrowserType.CHROME;
					break;

				case "firefox":
					browserType=BrowserType.FIREFOX;
					break;

				case "safari":
					browserType=BrowserType.SAFARI;
					break;

				case "edge":
					browserType = BrowserType.EDGE;
					break;
			}

			DriverManager.setDriver(DriverFactorySupplier.getDriver(browserType));

			//
			//DriverManager.getDriver().get(PropertyUtils.getValue(ConfigProperties.URL));
			Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
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
