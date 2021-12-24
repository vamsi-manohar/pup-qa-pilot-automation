package com.productsup.platform.pages.platform.hierrarchy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.Uninterruptibles;
import com.productsup.platform.pages.PlatformRouting;
import com.productsup.platform.pages.account.AccountOverview;
import com.productsup.platform.pages.platformactions.PlatformHierarchy;

public class ProjectOverviewPageOptions implements PlatformHierarchy {

	private List<String> menuOptions = new ArrayList<>();

	@Override
	public void selectPlatformHierarchy(Map<String, String> details) {
		

		new AccountOverview().selectProject(details.get("Project_Name"));
		Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
		
	}

	@Override
	public List<String> availableMenuOptions(Map<String, String> details) {

		this.menuOptions.add("Project");
		this.menuOptions.add("Dashboard");
		this.menuOptions.add("Authentication");
		this.menuOptions.add("Monitor");
		this.menuOptions.add("Activity");
		this.menuOptions.add("Order Sync");
		this.menuOptions.add("Reporting");
		this.menuOptions.add("Settings");
		return this.menuOptions;
	}

}
