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
	public List<String> getAvailableMenuOptions(Map<String, String> details) {
		menuOptions.add("Dashboard");
		menuOptions.add("Data Map");
		menuOptions.add("Authentication");
		menuOptions.add("Data Sources");
		menuOptions.add("Data Services");
		menuOptions.add("Exports A/B");
		menuOptions.add("Designer");
		menuOptions.add("Dataflow");
		menuOptions.add("Data View");
		menuOptions.add("Lists");
		menuOptions.add("ROI Strategy");
		menuOptions.add("Error Log");
		menuOptions.add("Monitor");
		menuOptions.add("Activity");
		menuOptions.add("Tracking");
		menuOptions.add("Settings");
		return menuOptions;
	}

}
