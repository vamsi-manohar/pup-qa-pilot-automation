package com.productsup.platform.pages;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.util.concurrent.Uninterruptibles;
import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.enums.WaitStrategy;
import com.productsup.platform.factories.ExplicitWaitFactory;

public class BasePage {

	protected WebDriverWait wait;

	protected void click(By by, WaitStrategy waitStrategy) {

		ExplicitWaitFactory.performExplicitWait(waitStrategy, by).click();
	}

	protected void sendKeys(By by, String keys, WaitStrategy waitStrategy) {
		ExplicitWaitFactory.performExplicitWait(waitStrategy, by).sendKeys(keys);
	}

	protected void click(WebElement element, WaitStrategy waitStrategy) {

		ExplicitWaitFactory.performExplicitWait(waitStrategy, element).click();
	}

	protected void mouseHoverOnWebElement(WebElement element) {
		try {

			Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
			String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
			((JavascriptExecutor) DriverManager.getDriver()).executeScript(mouseOverScript, element);
			Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);

		} catch (StaleElementReferenceException e) {
		} catch (NoSuchElementException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void mouseHover(By by) {
		Actions action = new Actions(DriverManager.getDriver());
		action.moveToElement(DriverManager.getDriver().findElement(by)).build().perform();
	}

	protected void selectValueFromDropdown(By by, String value) {
		Select select = new Select(DriverManager.getDriver().findElement(by));
		select.selectByValue(value);
	}

	protected void dragAndDrop(By sourceLocation, By destinationLocation) {
		Actions action = new Actions(DriverManager.getDriver());
		action.dragAndDrop(DriverManager.getDriver().findElement(sourceLocation),
				DriverManager.getDriver().findElement(destinationLocation)).build().perform();
	}

}
