package com.productsup.platform.reports;


import com.aventstack.extentreports.MediaEntityBuilder;
import com.productsup.platform.enums.ConfigProperties;
import com.productsup.platform.utils.PropertyUtils;
import com.productsup.platform.utils.ScreenshotUtils;

public final class ExtentLogger {

	private ExtentLogger() {

	}

	public static void pass(String message) {
		ExtentManager.getExtentTest().pass(message);
	}

	public static void info(String message)
	{
		ExtentManager.getExtentTest().info(message);
	}





	public static void fail(String message) {
		ExtentManager.getExtentTest().fail(message);
	}

	public static void skip(String message) {
		ExtentManager.getExtentTest().skip(message);
	}

	public static void pass(String message, boolean isScreenshotRequired)  {
		if (PropertyUtils.getValue(ConfigProperties.PASSED_STEPS_SCREENSHOTS).equalsIgnoreCase("yes")
				&& isScreenshotRequired) {
			ExtentManager.getExtentTest().pass(message,
					MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
		}

		else {
			pass(message);
		}

	}

	public static void fail(String message, boolean isScreenshotRequired)  {
		if (PropertyUtils.getValue(ConfigProperties.FAILED_STEPS_SCREENSHOTS).equalsIgnoreCase("yes")
				&& isScreenshotRequired) {
			ExtentManager.getExtentTest().fail(message,
					MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
		}

		else {
			fail(message);
		}

	}

	public static void skip(String message, boolean isScreenshotRequired)  {
		if (PropertyUtils.getValue(ConfigProperties.SKIPPED_STEPS_SCREENSHOTS).equalsIgnoreCase("yes")
				&& isScreenshotRequired) {
			ExtentManager.getExtentTest().skip(message,
					MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
		}

		else {
			skip(message);
		}

	}

	
	
}
