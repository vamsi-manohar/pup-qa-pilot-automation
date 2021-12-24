package com.productsup.platform.pages.platform.hierrarchy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.Uninterruptibles;
import com.productsup.platform.pages.PlatformRouting;
import com.productsup.platform.pages.account.AccountOverview;
import com.productsup.platform.pages.platformactions.PlatformHierarchy;

public class SiteOverviewPageOptions implements PlatformHierarchy {

	private List<String> menuOptions = new ArrayList<>();

	/**
	 *  Platform is set to - Site level 
	 */
	@Override
	public void selectPlatformHierarchy(Map<String, String> details) {

		new AccountOverview().selectProject(details.get("Project_Name"))
				.selectSite(details.get("Site_Name"));
		Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);

	}

	
	/**
	 *  List of navigation options available at site level
	 */
	@Override
	public List<String> availableMenuOptions(Map<String, String> details) {
		this.menuOptions.add("Dashboard");
		this.menuOptions.add("Data Map");
		this.menuOptions.add("Authentication");
		this.menuOptions.add("Data Sources");
		this.menuOptions.add("Data Services");
		this.menuOptions.add("Exports A/B");
		this.menuOptions.add("Designer");
		this.menuOptions.add("Dataflow");
		this.menuOptions.add("Data View");
		this.menuOptions.add("Lists");
		this.menuOptions.add("ROI Strategy");
		this.menuOptions.add("Error Log");
		this.menuOptions.add("Monitor");
		this.menuOptions.add("Activity");
		this.menuOptions.add("Tracking");
		this.menuOptions.add("Settings");
		return this.menuOptions;
	}

}
