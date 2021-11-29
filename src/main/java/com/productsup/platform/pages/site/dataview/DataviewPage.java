package com.productsup.platform.pages.site.dataview;

import java.util.*;
import java.util.concurrent.TimeUnit;

import com.productsup.platform.enums.RuleBoxes;
import com.productsup.platform.pages.site.SiteNavigations;
import com.productsup.platform.utils.DynamicLocatorStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.google.common.util.concurrent.Uninterruptibles;
import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.enums.WaitStrategy;
import com.productsup.platform.factories.ExplicitWaitFactory;
import com.productsup.platform.pages.BasePage;
import org.openqa.selenium.support.ui.WebDriverWait;

public  class DataviewPage extends  BasePage {

	private WebDriverWait wait;

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

	private String attribute;

	@FindBy(id = "box-text-filter")
	private WebElement filterBox;

	@FindBy(name = "editFrame")
	private WebElement editFrame;

	@FindBy(css="iframe[class='viewFrame']")
	private WebElement viewFrame;

	By dataFlowInput = By.id("dataflow-ul-input");
	By dataFlowOutput = By.id("dataflow-ul-output");
	By saveProcess = By.cssSelector("form[class*='hidden'] button[data-stages]");

	private String buttonType = "//*[text()='%s']";
	//private String ruleBox = "li[data-title='%s']";
	private String ruleBox1 = "li[class*='ui-draggable'][data-title='%s'] div[class*='title']";

	@FindBy(css="div[class*='stage-menu-container'] button")
	private WebElement dropdownArrow;

	@FindBy(css="span[column]")
	private List<WebElement> getColumnLabels;

	By button = By.cssSelector("div[class='btn-group stage-menu-container'] button");

	By frame = By.className("viewFrame");

	private String attributeFieldInfo = "td[class*='product-column-%s'] div span";
	public List<String>dataBeforeRuleBox = new ArrayList<>();
	public List<String>dataAfterRuleBox = new ArrayList<>();



	public DataviewPage() {
		PageFactory.initElements(DriverManager.getDriver(), this);
		this.wait = new WebDriverWait(DriverManager.getDriver(), 10);


	}

	private static EnumMap<RuleBoxes, String> ruleBoxMapping = new EnumMap<RuleBoxes, String>(RuleBoxes.class);

	static {
		for (RuleBoxes ruleBox : RuleBoxes.values()) {
			ruleBoxMapping.put(ruleBox, ruleBox.getData());
		}
	}

	public static String getRuleBoxes(RuleBoxes ruleBoxes) {
		return ruleBoxes.getData();
	}

	public DataviewPage selectOptimizationLevelFor(String template) {
		DriverManager.getDriver().switchTo().frame(viewFrame);
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

	public DataviewPage selectAttribute(String productAttribute) {
		for (int i = 0; i < productAttributes.size(); i++) {
			scrollIntoView(productAttributes.get(i));
			if (productAttributes.get(i).getText().equalsIgnoreCase(productAttribute)) {
				//Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
				clickUsingJSExecutor(editBtn.get(i));
				ExplicitWaitFactory.performExplicitWait(WaitStrategy.VISIBLE, sideBar);
				break;
			}
		}

		return this;
	}



	// Get Data for the selected Attributes

	public List<String> getAttributeData(String attributeName)
	{
		 if(attributeName.contains(" "))
		 {
			 attributeName = attributeName.replace(" ", "-");
		 }

		 List<String>data=new ArrayList<>();
		List<WebElement> values = DriverManager.getDriver()
				.findElements(By.cssSelector(DynamicLocatorStrategy.getDynamicLocator(attributeFieldInfo, attributeName)));
		values.stream().map(e->e.getText())
				.distinct()
				.forEach(e->data.add(e));

	/*	for (int i = 0; i < values.size(); i++) {
             allData.add(values.get(i).getText());
		}
*/
		//return Lists.newArrayList(allData);
		return data;
	}


	public void navigateToLargeView() {
		DriverManager.getDriver().switchTo().frame(editFrame);
		click(By.xpath(DynamicLocatorStrategy.getDynamicLocator(buttonType, "Large View")), WaitStrategy.CLICKABLE);
		this.wait.until((d) -> filterBox.isDisplayed());
	}


	public void searchRuleBox(RuleBoxes ruleBoxes)
	{
		navigateToLargeView();
		sendKeys(filterBox,WaitStrategy.VISIBLE,getRuleBoxes(ruleBoxes));
		Uninterruptibles.sleepUninterruptibly(5,TimeUnit.SECONDS);

	}



	public String getRuleBoxName(String name)
	{
		String ruleBoxType=null;
		switch(RuleBoxes.valueOf(name))
		{
			case UPPERCASE_TO_HUMAN:
				ruleBoxType= getRuleBoxes(RuleBoxes.UPPERCASE_TO_HUMAN);
				break;

			case REMOVE_HTML_TAGS:
				ruleBoxType = getRuleBoxes(RuleBoxes.REMOVE_HTML_TAGS);
				break;

			case SET_VALUE_IF_EMPTY:
				ruleBoxType = getRuleBoxes(RuleBoxes.SET_VALUE_IF_EMPTY);
				break;

			case CAPITALIZE_WORDS:
				ruleBoxType = getRuleBoxes(RuleBoxes.CAPITALIZE_WORDS);
				break;

			case MAP_REPLACE:
				ruleBoxType = getRuleBoxes(RuleBoxes.MAP_REPLACE);
				break;

			case REMOVE_WHITESPACES:
				ruleBoxType = getRuleBoxes(RuleBoxes.REMOVE_WHITESPACES);
				break;


		}
        return ruleBoxType;
	}

	public String getRuleBoxLevel(String level)
	{
		String ruleBoxLevel=null;
		switch (RuleBoxes.valueOf(level))
		{
			case INTERMEDIATE:
				ruleBoxLevel = "input";
				break;

			case EXPORT:
				ruleBoxLevel="output";
				break;
		}
		return ruleBoxLevel;
	}

	public void applyRuleBoxTo(RuleBoxes ruleBoxes,String ruleBoxType)
	{
		WebElement inputElement = DriverManager.getDriver().
				findElement(By.cssSelector(DynamicLocatorStrategy.getDynamicLocator(ruleBox1, getRuleBoxName(ruleBoxType))));
		this.wait.until(d->inputElement.isDisplayed());
		if (getRuleBoxes(ruleBoxes).equalsIgnoreCase("Intermediate")) {
			dragAndDrop(By.cssSelector(DynamicLocatorStrategy.getDynamicLocator(ruleBox1, getRuleBoxName(ruleBoxType))), dataFlowInput);
		} else if (getRuleBoxes(ruleBoxes).equalsIgnoreCase("Export")) {
			dragAndDrop(By.cssSelector(DynamicLocatorStrategy.getDynamicLocator(ruleBox1, getRuleBoxName(ruleBoxType))),
					dataFlowOutput);
		}
	}
	public void saveRuleBox() {
		click(saveProcess, WaitStrategy.CLICKABLE);
		Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
		DriverManager.getDriver().navigate().refresh();
		Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
		DriverManager.getDriver().navigate().refresh();
	}


	public void addRuleBox(String ruleBox)
	{
		switch (RuleBoxes.valueOf(ruleBox))
		{
			case UPPERCASE_TO_HUMAN:
				searchRuleBox(RuleBoxes.UPPERCASE_TO_HUMAN);
				break;

			case REMOVE_HTML_TAGS:
				searchRuleBox(RuleBoxes.REMOVE_HTML_TAGS);
				break;

			case SET_VALUE_IF_EMPTY:
				searchRuleBox(RuleBoxes.SET_VALUE_IF_EMPTY);
				break;

			case CAPITALIZE_WORDS:
				searchRuleBox(RuleBoxes.CAPITALIZE_WORDS);
				break;

			case MAP_REPLACE:
				searchRuleBox(RuleBoxes.MAP_REPLACE);
				break;

			case REMOVE_WHITESPACES:
				searchRuleBox(RuleBoxes.REMOVE_WHITESPACES);
				break;



		}
	}

	public void applyRuleBox(String level,String type)
	{
		switch (RuleBoxes.valueOf(level))
		{
			case INTERMEDIATE:
				System.out.println(getRuleBoxName(type));
				applyRuleBoxTo(RuleBoxes.INTERMEDIATE,type);
				break;

			case EXPORT:
				applyRuleBoxTo(RuleBoxes.EXPORT,type);
				break;
		}

	}


	public boolean validateRuleBoxesAddedToDataflow(String attributeName, String level, String ruleBoxType)
	{
		DriverManager.getDriver().switchTo().defaultContent();
		return new SiteNavigations().navigateToDataFlowPage().
				validateRuleBoxesAdded(attributeName,getRuleBoxLevel(level),getRuleBoxName(ruleBoxType));
	}


	public List<String> getExportChannelList()
	{

           DriverManager.getDriver().switchTo().frame(viewFrame);
           List<String>channelsList = new ArrayList<>();
           click(dropdownArrow,WaitStrategy.CLICKABLE);
           this.wait.until(d->!list.isEmpty());
           for(int i=0;i<list.size();i++)
		   {
		   	   channelsList.add(list.get(i).getText());
		   }

		  System.out.println("Channels List :: " +channelsList);
           DriverManager.getDriver().switchTo().defaultContent();

           return channelsList;
	}


	public List<String> getColumnLables()
	{
		List<String> allLabels= new ArrayList<>();
		for(int i=0;i<getColumnLabels.size();i++)
		{
			allLabels.add(getColumnLabels.get(i).getText());
		}
		System.out.println("All Labels :: " +allLabels);

		return allLabels;
	}

}
