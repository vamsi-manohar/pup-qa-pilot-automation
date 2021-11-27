package com.productsup.platform.pages.platform.hierrarchy;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Supplier;
import com.productsup.platform.pages.platformactions.PlatformHierarchy;

public class PlatformHierarchyFactory {

	private static final Supplier<PlatformHierarchy> account = () -> new AccountOverviewPageOptions();
	private static final Supplier<PlatformHierarchy> project = () -> new ProjectOverviewPageOptions();
	private static final Supplier<PlatformHierarchy> site = () -> new SiteOverviewPageOptions();

	private static final Map<String, Supplier<PlatformHierarchy>> MAP = new HashMap<>();

	static {
		MAP.put("Account", account);
		MAP.put("Project", project);
		MAP.put("Site", site);
	}

	public static PlatformHierarchy get(String option) {
		return MAP.get(option).get();
	}
}
