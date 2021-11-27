package com.productsup.platform.pages.platform.hierrarchy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.productsup.platform.pages.PlatformRouting;
import com.productsup.platform.pages.platformactions.PlatformHierarchy;

public class AccountOverviewPageOptions implements PlatformHierarchy {

	private List<String> menuOptions = new ArrayList<>();

	@Override
	public void selectPlatformHierarchy(Map<String, String> details) {

		// This implementation for account level is not needed

	}

	@Override
	public List<String> getAvailableMenuOptions(Map<String, String> details) {
		menuOptions.add("Overview");
		menuOptions.add("Dashboard");
		menuOptions.add("Authentication");
		menuOptions.add("User Management");
		menuOptions.add("Monitor");
		menuOptions.add("FTP Accounts");
		menuOptions.add("Activity");
		menuOptions.add("Settings");
		
		

		return menuOptions;
	}

}
