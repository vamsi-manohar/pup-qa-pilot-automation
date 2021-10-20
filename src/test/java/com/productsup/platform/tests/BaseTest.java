package com.productsup.platform.tests;

import java.util.Map;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.productsup.platform.driver.Driver;

public class BaseTest {





	@SuppressWarnings("unchecked")
	@BeforeMethod
	protected void setUp(Object[] data)  {
	
		Map<String,String>map=(Map<String,String>)data[0];
		System.out.println(map);
		Driver.initDriver(map.get("Browser"));
	}

	@AfterMethod
	protected void tearDown() {
		Driver.quitDriver();
	}
}
