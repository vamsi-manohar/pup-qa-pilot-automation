package com.productsup.platform.pages.platform.hierrarchy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.Uninterruptibles;
import com.productsup.platform.pages.login.LoginPage;

public class ProjectOverviewPageOptions implements PlatformHierarchy {

	List<String> menuOptions = new ArrayList<>();

	@Override
	public void selectPlatformHierarchy(Map<String, String> details) {
		
		new LoginPage().loginToPlatform().selectProject(details.get("Project_Name"));
		Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
		
	}

	@Override
	public List<String> getAvailableMenuOptions(Map<String, String> details) {

		menuOptions.add("Project");
		menuOptions.add("Dashboard");
		menuOptions.add("Authentication");
		menuOptions.add("Monitor");
		menuOptions.add("Activity");
		menuOptions.add("Order Sync");
		menuOptions.add("Reporting");
		menuOptions.add("Settings");
		return menuOptions;
	}

}
