package com.productsup.platform.driver;

import java.util.Objects;

import org.openqa.selenium.WebDriver;

/**
 * DriverManager class helps to achieve thread safety for the WebDriver instance
 * @author Vamsi Manohar
 * 22-Sep-2021
 * 6:35:46 am
 */


public final class DriverManager {

	/**
	 * Private constructor to avoid external instantiation
	 */
	
	private DriverManager() {

	}

	private static ThreadLocal<WebDriver> dr = new ThreadLocal<>();

	
	/**
	 * Returns the thread safe WebDriver instance fetched from ThreadLocal variable.
	 */
	public static WebDriver getDriver() {
		return dr.get();
	}

	/**
	 * Set the WebDriver instance to thread local variable
	 * 
	 * @author K Vamsi Manohar
	 * @param driver instance that needs to saved from Thread Safety issues.
	 */
	static void setDriver(WebDriver driver) {
		if (Objects.nonNull(driver)) {
			dr.set(driver);
		}
	}

	/**
	 * Calling remove method on ThreadLocal variable ensures to set the default value to ThreadLocal variable.
	 * 
	 * @author K Vamsi Manohar
	 * 
	 */
	static void unload() {
		System.out.println("Driver Removed");
		dr.remove();
	}

}
