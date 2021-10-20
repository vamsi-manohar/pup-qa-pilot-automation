package com.productsup.platform.pages.dataview;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.util.concurrent.Uninterruptibles;
import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.enums.WaitStrategy;
import com.productsup.platform.pages.BasePage;
import com.productsup.platform.utils.DynamicLocatorStrategy;

public final class AddConditionalBoxes extends BasePage {

	private String attribute;

	@FindBy(id = "box-text-filter")
	private WebElement filterBox;

	@FindBy(name = "editFrame")
	private WebElement editFrame;

	By dataFlowInput = By.id("dataflow-ul-input");
	By dataFlowOutput = By.id("dataflow-ul-output");
	By saveProcess = By.cssSelector("form[class*='hidden'] button[data-stages]");

	private String buttonType = "//*[text()='%s']";
	//private String ruleBox = "li[data-title='%s']";
	private String ruleBox1 = "li[class*='ui-draggable'][data-title='%s'] div[class*='title']";
	private String attributeFieldInfo = "td[class*='product-column-%s'] div span";

	public AddConditionalBoxes(String attribute) {
		PageFactory.initElements(DriverManager.getDriver(), this);
		wait = new WebDriverWait(DriverManager.getDriver(), 10);
		this.attribute = attribute;
	}

	// Navigating to Large View
	public void navigateToLargeView() {
		DriverManager.getDriver().switchTo().frame(editFrame);
		click(By.xpath(DynamicLocatorStrategy.getDynamicLocator(buttonType, "Large View")), WaitStrategy.CLICKABLE);
		this.wait.until((d) -> filterBox.isDisplayed());
	}

	public void addRuleBox(String ruleBoxType, String ruleBoxAt) {
		navigateToLargeView();
		filterBox.sendKeys(ruleBoxType);
		if (ruleBoxAt.equalsIgnoreCase("Intermediate")) {
			dragAndDrop(By.cssSelector(DynamicLocatorStrategy.getDynamicLocator(ruleBox1, ruleBoxType)), dataFlowInput);
		} else if (ruleBoxAt.equalsIgnoreCase("Export")) {
			dragAndDrop(By.cssSelector(DynamicLocatorStrategy.getDynamicLocator(ruleBox1, ruleBoxType)),
					dataFlowOutput);
		}
		click(saveProcess, WaitStrategy.CLICKABLE);
		Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
		DriverManager.getDriver().navigate().refresh();
		Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
		//DriverManager.getDriver().navigate().refresh();

	}

	public void validateUppercaseToHumanRuleBox() {

		List<WebElement> values = DriverManager.getDriver()
				.findElements(By.cssSelector(DynamicLocatorStrategy.getDynamicLocator(attributeFieldInfo, attribute)));
		for (int i = 0; i < values.size(); i++) {
			System.out.println(values.get(i).getText());
		}

	}
	
	
	public boolean removeHTMLTags(String value) {
		String patternToBeMatched = "<(\"[^\"]*\"|'[^']*'|[^'\">])*>";
		Pattern pattern = Pattern.compile(patternToBeMatched);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();

	}

}
