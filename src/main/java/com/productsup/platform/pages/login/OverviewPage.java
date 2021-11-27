package com.productsup.platform.pages.login;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.productsup.platform.pages.site.SiteDashboard;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.google.common.util.concurrent.Uninterruptibles;
import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.enums.WaitStrategy;
import com.productsup.platform.pages.BasePage;


public final class OverviewPage extends BasePage {

	@FindBy(css = "#js-projectList td a")
	private List<WebElement> projectList;

	@FindBy(css = "#js-projectList td[class='emphasis'] a")
	private List<WebElement> sitesList;

	public OverviewPage() {
		PageFactory.initElements(DriverManager.getDriver(), this);
	}

	// By availableProjects=By.cssSelector("#js-projectList td a");



	public OverviewPage selectProject(String projectName) {

	   Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
		for (int i = 0; i < projectList.size(); i++) {
			System.out.println(projectList.get(i).getText());
			if (projectList.get(i).getText().contains(projectName)) {
				click(projectList.get(i), WaitStrategy.CLICKABLE);

			}

		}

		return this;
	}

	public SiteDashboard selectSite(String siteName) {
		  Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
		for (int i = 0; i < sitesList.size(); i++) {
			if (sitesList.get(i).getText().contains(siteName)) {

				click(sitesList.get(i), WaitStrategy.CLICKABLE);
				

			}

		}

		return new SiteDashboard();

	}

}
