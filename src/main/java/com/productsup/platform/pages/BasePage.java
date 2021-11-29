package com.productsup.platform.pages;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import com.productsup.platform.driver.Driver;
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

	public void click(By by, WaitStrategy waitStrategy) {

		ExplicitWaitFactory.performExplicitWait(waitStrategy, by).click();
	}

	protected void sendKeys(By by, String keys, WaitStrategy waitStrategy) {
		ExplicitWaitFactory.performExplicitWait(waitStrategy, by).sendKeys(keys);
	}

	public static void click(WebElement element, WaitStrategy waitStrategy) {

		ExplicitWaitFactory.performExplicitWait(waitStrategy, element).click();
	}

	public static void sendKeys(WebElement element,WaitStrategy waitStrategy, String keys)
	{
		ExplicitWaitFactory.performExplicitWait(waitStrategy, element).sendKeys(keys);
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

	public static void mouseHover(By by) {
		Actions action = new Actions(DriverManager.getDriver());
		action.moveToElement(DriverManager.getDriver().findElement(by)).build().perform();
	}

	protected void selectValueFromDropdown(By by, String value) {
		Select select = new Select(DriverManager.getDriver().findElement(by));
		select.selectByValue(value);
	}

	public static void selectValueFromDropdown(WebElement element, String value)
	{
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}

	protected void dragAndDrop(By sourceLocation, By destinationLocation) {
		Actions action = new Actions(DriverManager.getDriver());
		action.dragAndDrop(DriverManager.getDriver().findElement(sourceLocation),
				DriverManager.getDriver().findElement(destinationLocation)).build().perform();
	}

	protected void dragAndDrop(By sourceLocation, WebElement destinationLocation) {
		Actions action = new Actions(DriverManager.getDriver());
		action.dragAndDrop(DriverManager.getDriver().findElement(sourceLocation),
				destinationLocation).build().perform();
	}


	protected String getText(By ele)
	{
		return DriverManager.getDriver().findElement(ele).getText();
	}

	protected String getText(WebElement element)
	{
		return element.getText();
	}


	public static void scrollIntoView(By element)
	{
		JavascriptExecutor je = (JavascriptExecutor) DriverManager.getDriver();
		je.executeScript("arguments[0].scrollIntoView(true);",DriverManager.getDriver().findElement(element));

	}

	public static void scrollIntoView(WebElement element)
	{
		JavascriptExecutor je = (JavascriptExecutor) DriverManager.getDriver();
		je.executeScript("arguments[0].scrollIntoView(true);",(element));

	}

	protected void clickUsingJSExecutor(By by)
	{
		try {
			if (DriverManager.getDriver().findElement(by).isDisplayed()) {

				JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getDriver();
				executor.executeScript("arguments[0].click();", DriverManager.getDriver().findElement(by));
			}
		} catch (StaleElementReferenceException e) {
		} catch (NoSuchElementException e) {
		} catch (Exception e) {

		}
	}

	protected void clickUsingJSExecutor(WebElement element)
	{
		try {
			if (element.isDisplayed()) {

				this.wait=new WebDriverWait(DriverManager.getDriver(),10);
				this.wait.until(d->element.isDisplayed());
				JavascriptExecutor executor = (JavascriptExecutor)  DriverManager.getDriver();
				executor.executeScript("arguments[0].click();", element);
			}
		} catch (StaleElementReferenceException e) {
		} catch (NoSuchElementException e) {
		} catch (Exception e) {

		}
	}
}
