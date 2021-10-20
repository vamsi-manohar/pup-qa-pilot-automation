package com.productsup.platform.tests;

import java.util.Map;

import org.testng.annotations.Test;

import com.productsup.platform.annotations.FrameworkAnnotation;
import com.productsup.platform.enums.CategoryType;
import com.productsup.platform.pages.login.LoginPage;

public class DataviewTest extends BaseTest {

	private DataviewTest() {

	}

	@FrameworkAnnotation(author = { "Vamsi" }, category = { CategoryType.SMOKE })
	@Test
	public void addUppercaseToHumanRuleBox(Map<String, String> data) {
		new LoginPage().loginToPlatform().selectProject(data.get("Project_Name")).selectSite(data.get("Site_Name"))
				.navigateToDataView().selectOptimizationLevelFor(data.get("Dataview_Template"))
				.selectAttribute(data.get("Product_Attrribute")).addRuleBox(data.get("Rule_Box"),data.get("Apply_Rulebox_At"));
		
	}

	
	@FrameworkAnnotation(author = { "Vamsi Manohar" }, category = { CategoryType.REGRESSION })
	@Test
	public void addRemoveHTMLTagsRuleBox(Map<String, String> data) {
		
		new LoginPage().loginToPlatform().selectProject(data.get("Project_Name")).selectSite(data.get("Site_Name"))
				.navigateToDataView().selectOptimizationLevelFor(data.get("Dataview_Template"))
				.selectAttribute(data.get("Product_Attrribute")).addRuleBox(data.get("Rule_Box"),data.get("Apply_Rulebox_At"));
	}
	
	

}
