package com.productsup.platform.tests;

import java.util.HashMap;
import java.util.Map;

import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.pages.PlatformRouting;
import com.productsup.platform.pages.account.AccountOverview;
import com.productsup.platform.reports.ExtentLogger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import com.productsup.platform.driver.Driver;

public class BaseTest {

	@SuppressWarnings("unchecked")

	protected Map<String,String>details=new HashMap<>();

	@BeforeMethod
	protected void setUp(Object[] data)  {
	
		Map<String,String>map=(Map<String,String>)data[0];
		Driver.initDriver(map.get("Browser"));
		getBrowserAndSystemDetails();
		new PlatformRouting().getLoginPage().loginToPlatform()
		.selectAccount();
	}


	protected Map<String, String> getBrowserAndSystemDetails()
	{

		Capabilities cap = ((RemoteWebDriver) DriverManager.getDriver()).getCapabilities();
		String browserName = cap.getBrowserName().toUpperCase();
		String operatingSystem = cap.getPlatform().toString();
		String version = cap.getVersion().toString();
		details.put("browser",browserName);
		details.put("OS",operatingSystem);
		details.put("version",version);
		return details;

	}

	@AfterMethod(alwaysRun = true)
	protected void tearDown() {

		Driver.quitDriver();

	}


	protected void logEnvironmentInfo()
	{
		ExtentLogger.info("Executed on browser --> " +details.get("browser"));
		ExtentLogger.info("Executed on OS --> " +details.get("OS"));
		ExtentLogger.info("Executed on version --> " +details.get("version"));
	}


	protected void switchToDefaultContent()
	{
		DriverManager.getDriver().switchTo().defaultContent();
	}


}
