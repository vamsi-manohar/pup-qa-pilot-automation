package com.productsup.platform.pages.dataview;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.google.common.util.concurrent.Uninterruptibles;
import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.enums.WaitStrategy;
import com.productsup.platform.factories.ExplicitWaitFactory;
import com.productsup.platform.pages.BasePage;

public final class DataviewPage extends BasePage {

	@FindBy(css = "tbody[ng-show] tr")
	private List<WebElement> productsInfo;

	@FindBy(css = "a span[data-title]")
	private List<WebElement> list;

	@FindBy(css = ".viewFrame")
	private WebElement frame1;

	@FindBy(css = "span[column]")
	private List<WebElement> productAttributes;

	@FindBy(css = "button[class='btn btn-default btn-sm dropdown-toggle']")
	private List<WebElement> btn;

	@FindBy(css = "button[ui-sref*='edit']")
	private List<WebElement> editBtn;

	@FindBy(css = "div[class='nav-tabs-container']")
	private WebElement sideBar;

	By button = By.cssSelector("div[class='btn-group stage-menu-container'] button");

	By frame = By.className("viewFrame");

	public DataviewPage() {
		PageFactory.initElements(DriverManager.getDriver(), this);
	}

	public DataviewPage selectOptimizationLevelFor(String template) {
		DriverManager.getDriver().switchTo().frame(frame1);
		click(button, WaitStrategy.PRESENCE);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getAttribute("data-title").equalsIgnoreCase(template)) {
				list.get(i).click();
				Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
				break;
			}
		}

		return this;
	}

	public AddConditionalBoxes selectAttribute(String productAttribute) {

		for (int i = 0; i < productAttributes.size(); i++) {
			if (productAttributes.get(i).getText().equalsIgnoreCase(productAttribute)) {
				Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
				editBtn.get(i).click();
				ExplicitWaitFactory.performExplicitWait(WaitStrategy.VISIBLE, sideBar);
				break;
			}
		}

		return new AddConditionalBoxes(productAttribute);
	}

}
