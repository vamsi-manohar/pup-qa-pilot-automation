package com.productsup.platform.utils;

public final class DynamicLocatorStrategy {

	private DynamicLocatorStrategy() {

	}

	public static String getDynamicLocator(String xpath, String value) {
		return String.format(xpath, value);
	}

}
