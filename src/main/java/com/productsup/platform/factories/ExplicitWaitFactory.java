package com.productsup.platform.factories;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.productsup.platform.constants.Constants;
import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.enums.WaitStrategy;

/**
 * Explicit wait factory produces different waits before operating on a WebElement
 * 
 * @author Vamsi Manohar
 * 22-Sep-2021
 * 7:15:06 am
 */
public final class ExplicitWaitFactory {

	/**
	 * Private constructor to avoid external instantiation
	 */
	private ExplicitWaitFactory() {

	}

	
	/**
	 * 
	 * @author K Vamsi Manohar
	 * @param waitStrategy  Strategy to be applied on a WebElement and can be chosen from WaitStrategy Enums class
	 * @param by By locator of the WebElement
	 * @return the WebElement
	 */
	public static WebElement performExplicitWait(WaitStrategy waitStrategy, By by) {

		WebElement element = null;
		if (waitStrategy == WaitStrategy.CLICKABLE) {
			element = new WebDriverWait(DriverManager.getDriver(), Constants.getTimeOut())
					.until(ExpectedConditions.elementToBeClickable(by));

		} else if (waitStrategy == WaitStrategy.PRESENCE) {
			element = new WebDriverWait(DriverManager.getDriver(), Constants.getTimeOut())
					.until(ExpectedConditions.presenceOfElementLocated(by));
		}

		else if (waitStrategy == WaitStrategy.NONE) {
			element = DriverManager.getDriver().findElement(by);
		}

		return element;
	}
	
	
	public static WebElement performExplicitWait(WaitStrategy waitStrategy, WebElement ele) {

		WebElement element = null;
		if (waitStrategy == WaitStrategy.CLICKABLE) {
			element = new WebDriverWait(DriverManager.getDriver(), Constants.getTimeOut())
					.until(ExpectedConditions.elementToBeClickable(ele));

		} else if (waitStrategy == WaitStrategy.VISIBLE) {
			element = new WebDriverWait(DriverManager.getDriver(), Constants.getTimeOut())
					.until(ExpectedConditions.visibilityOf(ele));
		}
	
		else if (waitStrategy == WaitStrategy.NONE) {
			element = ele;
		}

		return element;
	}
	
	
	
	


}
