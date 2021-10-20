package com.productsup.platform.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.productsup.platform.driver.DriverManager;


public final class ScreenshotUtils {
	
	
	private ScreenshotUtils()
	{
		
	}
	
	
	
	public static String getBase64Image() {
		return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
	}


}
