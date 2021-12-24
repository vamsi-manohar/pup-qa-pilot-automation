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
	public List<String> availableMenuOptions(Map<String, String> details) {
		this.menuOptions.add("Overview");
		this.menuOptions.add("Dashboard");
		this.menuOptions.add("Authentication");
		this.menuOptions.add("User Management");
		this.menuOptions.add("Monitor");
		this.menuOptions.add("FTP Accounts");
		this.menuOptions.add("Activity");
		this.menuOptions.add("Settings");
		
		

		return this.menuOptions;
	}

}
