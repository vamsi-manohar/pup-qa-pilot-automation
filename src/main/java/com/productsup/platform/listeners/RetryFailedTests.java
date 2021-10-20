package com.productsup.platform.listeners;


import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.productsup.platform.enums.ConfigProperties;
import com.productsup.platform.utils.PropertyUtils;

public class RetryFailedTests implements IRetryAnalyzer {

	private int count = 0;
	private int retries = 1;

	@Override
	public boolean retry(ITestResult result) {

		boolean value = false;

		if (PropertyUtils.getValue(ConfigProperties.RETRY_FAILED_TESTS).equalsIgnoreCase("yes")) {
			value = count < retries;
			count++;

		}

		return value;
	}

}
