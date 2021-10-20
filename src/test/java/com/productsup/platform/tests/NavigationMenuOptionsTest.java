package com.productsup.platform.tests;

import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import com.productsup.platform.annotations.FrameworkAnnotation;
import com.productsup.platform.enums.CategoryType;
import com.productsup.platform.pages.platform.hierrarchy.PlatformHierarchyFactory;
import com.productsup.platform.pages.platform.hierrarchy.PlatformOverview;

public final class NavigationMenuOptionsTest extends BaseTest {

	@FrameworkAnnotation(author = { "Vamsi" }, category = { CategoryType.SMOKE })
	@Test
	public void validatePlatformOptions(Map<String, String> details) {
		PlatformOverview platformOverview = new PlatformOverview();
		platformOverview.setPlatformInterface(PlatformHierarchyFactory.get(details.get("Platform_Hierrarchy")));
		List<String> actual = platformOverview.platformSetup(details);
		List<String> options = platformOverview.getSideBarNavigation().getAvailableOptions();
		Assertions.assertThat(options).isNotEmpty().containsAll(actual);

	}

}
