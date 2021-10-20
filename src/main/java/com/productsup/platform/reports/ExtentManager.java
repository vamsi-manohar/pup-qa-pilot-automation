package com.productsup.platform.reports;

import java.util.Objects;

import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {

	private ExtentManager() {

	}

	private static ThreadLocal<ExtentTest> exTest = new ThreadLocal<>();

	static ExtentTest getExtentTest() {
		return exTest.get();
	}

	static void setExtentTest(ExtentTest test) {
		if (Objects.nonNull(test)) {
			exTest.set(test);
		}
	}

	static void unload() {
		exTest.remove();
	}
}
