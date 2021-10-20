package com.productsup.platform.pages.overview;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.google.common.util.concurrent.Uninterruptibles;
import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.enums.WaitStrategy;
import com.productsup.platform.pages.BasePage;
import com.productsup.platform.pages.dataview.DataviewPage;
import com.productsup.platform.utils.DynamicLocatorStrategy;

public final class SiteDashboardPage extends BasePage 
{
	

	
	
	@FindBy(id="sidebar-logo")
	private WebElement sideBarLogo;
	
	@FindBy(css="pup-heading span")
	private WebElement title;
	
	By navBar= By.className("sidebar");
	
	
	private String menu = "//*[text()='%s']";
	
	
	public SiteDashboardPage()
	{
	
		PageFactory.initElements(DriverManager.getDriver(), this);
	}
	
	
	public DataviewPage navigateToDataView() 
	{
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        mouseHover(navBar);
		click(By.xpath(DynamicLocatorStrategy.getDynamicLocator(menu, "Data View")), WaitStrategy.CLICKABLE);
		 Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
		return new DataviewPage();
	}

}
