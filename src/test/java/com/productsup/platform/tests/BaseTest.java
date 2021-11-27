package com.productsup.platform.tests;

import java.io.IOException;
import java.util.Map;

import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.pages.PlatformRouting;
import org.testng.annotations.*;

import com.productsup.platform.driver.Driver;

public class BaseTest {

	@SuppressWarnings("unchecked")


	@BeforeMethod
	protected void setUp(Object[] data)  {
	
		Map<String,String>map=(Map<String,String>)data[0];
		System.out.println(map);
		Driver.initDriver(map.get("Browser"));
		DriverManager.getDriver().manage().deleteAllCookies();
		new PlatformRouting().getLoginPage().loginToPlatform();
	}


	@AfterMethod(alwaysRun = true)
	protected void tearDown() {

		Driver.quitDriver();

	}
}
