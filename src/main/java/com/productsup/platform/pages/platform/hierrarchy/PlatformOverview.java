package com.productsup.platform.pages.platform.hierrarchy;

import com.productsup.platform.pages.platformactions.PlatformHierarchy;

import java.util.List;
import java.util.Map;

public class PlatformOverview {

	private SidebarNavigationMenu sidebarNavigationMenu;
	private PlatformHierarchy platformInterface;

	public PlatformOverview() {
		this.sidebarNavigationMenu = new SidebarNavigationMenu();
	}

	public SidebarNavigationMenu getSideBarNavigation() {
		return sidebarNavigationMenu;
	}

	public void setPlatformInterface(PlatformHierarchy platformInterface) {
		this.platformInterface = platformInterface;
	}

	public List<String> platformSetup(Map<String, String> details) {
		platformInterface.selectPlatformHierarchy(details);
		return platformInterface.availableMenuOptions(details);
	}

}
