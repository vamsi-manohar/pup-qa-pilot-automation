package com.productsup.platform.pages.login;

import java.util.Base64;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;

import com.google.common.util.concurrent.Uninterruptibles;
import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.enums.ConfigProperties;
import com.productsup.platform.enums.WaitStrategy;
import com.productsup.platform.pages.BasePage;
import com.productsup.platform.utils.DynamicLocatorStrategy;
import com.productsup.platform.utils.PropertyUtils;

public class LoginPage extends BasePage {

	private String inputType = "input[name='%s']";
	private String nextButton = "//*[text()='%s']";

	public OverviewPage loginToPlatform() {
		/*
		 * DriverManager.getDriver().switchTo().frame(DriverManager.getDriver().
		 * findElement( By.cssSelector(
		 * "[src='//login.productsup.com?hl=en&url=https://platform.productsup.com']")))
		 * ; enterUsername(); enterPassword(); clickLogin();
		 * 
		 * 
		 */
		
		DriverManager.getDriver().navigate().to(PropertyUtils.getValue(ConfigProperties.URL));
		Cookie cookie= new Cookie("PHPSESSID","gnlidb9cm070n1990hvl4mbg8q");
		DriverManager.getDriver().manage().addCookie(cookie);
		DriverManager.getDriver().navigate().to(PropertyUtils.getValue(ConfigProperties.URL));
		Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);

		return new OverviewPage();
	}

	private void enterUsername() {
		sendKeys(By.cssSelector(DynamicLocatorStrategy.getDynamicLocator(inputType, "username")),
				PropertyUtils.getValue(ConfigProperties.USERNAME), WaitStrategy.PRESENCE);
		click(By.xpath(DynamicLocatorStrategy.getDynamicLocator(nextButton, "Next")), WaitStrategy.CLICKABLE);
	}

	private void enterPassword() {
		String decodedPassword = decodePassword();
		sendKeys(By.cssSelector(DynamicLocatorStrategy.getDynamicLocator(inputType, "current-password")),
				decodedPassword, WaitStrategy.PRESENCE);
	}

	private void clickLogin() {
		click(By.xpath(DynamicLocatorStrategy.getDynamicLocator(nextButton, "Log In")), WaitStrategy.CLICKABLE);
	}

	private String decodePassword() {
		return new String(Base64.getDecoder().decode(PropertyUtils.getValue(ConfigProperties.PASSWORD).getBytes()));

	}
}
