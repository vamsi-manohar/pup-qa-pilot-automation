package com.productsup.platform.tests;

import java.util.HashMap;
import java.util.Map;

import com.productsup.platform.enums.Navigations;
import com.productsup.platform.pages.PlatformRouting;
import com.productsup.platform.pages.login.LoginPage;
import com.productsup.platform.pages.site.dataview.DataViewFactory;
import com.productsup.platform.pages.site.dataview.DataviewActions;
import com.productsup.platform.reports.ExtentLogger;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.productsup.platform.annotations.FrameworkAnnotation;
import com.productsup.platform.enums.CategoryType;

public final class DataviewTest extends BaseTest {


	private Map<String,String>dataSet=new HashMap<>();
	private boolean success=false;

	private DataviewTest() {

	}

	@FrameworkAnnotation(author = {"Vamsi"}, category = {CategoryType.SMOKE})
	@Test
	public void addRuleBoxes(Map<String, String> data) {

		this.dataSet=data;
		try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {

			logEnvironmentInfo();
			ExtentLogger.info("Rule box added => " + data.get("Rule_Box"));
			DataviewActions dataviewActions = new DataviewActions();
			dataviewActions.setDataView(DataViewFactory.get(data.get("Rule_Box")));
			softly.assertThat(dataviewActions.setupDataView(data)).isEqualTo(true);
			success=true;
		}


	}

	@AfterMethod(alwaysRun = true)
	public void tearDownRuleBox()
	{
		try {
			if (success) {
				System.out.println("Entered into tear down method");
				DataviewActions dataviewActions = new DataviewActions();
				dataviewActions.destroyRuleBox(dataSet);
				success = false;
			}
		}catch(Exception e)
		{
			e.getMessage();
		}
	}
}




	
	


