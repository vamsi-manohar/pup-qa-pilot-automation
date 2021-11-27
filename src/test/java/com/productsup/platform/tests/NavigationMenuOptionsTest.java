package com.productsup.platform.tests;

import java.util.List;
import java.util.Map;

import com.productsup.platform.pages.PlatformRouting;
import com.productsup.platform.reports.ExtentLogger;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.productsup.platform.annotations.FrameworkAnnotation;
import com.productsup.platform.enums.CategoryType;
import com.productsup.platform.pages.platform.hierrarchy.PlatformHierarchyFactory;
import com.productsup.platform.pages.platform.hierrarchy.PlatformOverview;

public final class NavigationMenuOptionsTest extends BaseTest {


	@FrameworkAnnotation(author = { "Vamsi Manohar" }, category = { CategoryType.SMOKE })
	@Test
	public void validatePlatformOptions(Map<String, String> details) {

		try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions())
		{

			ExtentLogger.info("Navigation options on platform at :: " + details.get("Platform_Hierrarchy") + " Level ");
			PlatformOverview platformOverview = new PlatformOverview();
			platformOverview.setPlatformInterface(PlatformHierarchyFactory.get(details.get("Platform_Hierrarchy")));
			List<String> actual = platformOverview.platformSetup(details);
			List<String> options = platformOverview.getSideBarNavigation().getAvailableOptions();
			softly.assertThat(options).isNotEmpty().containsAll(actual);
		}

	}

}
