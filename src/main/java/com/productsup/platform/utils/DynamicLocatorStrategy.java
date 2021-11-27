package com.productsup.platform.utils;

public final class DynamicLocatorStrategy {

	private DynamicLocatorStrategy() {

	}

	public static String getDynamicLocator(String xpath, String value) {
		return String.format(xpath, value);
	}

	public static String getDynamicLocator(String xpath, String value,String value1) {
		return xpath.replace("%replace1%",value).replace("%replace2%",value1);
	}

}
