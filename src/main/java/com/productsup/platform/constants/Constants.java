package com.productsup.platform.constants;

import com.productsup.platform.enums.ConfigProperties;
import com.productsup.platform.utils.PropertyUtils;

public class Constants {

	private Constants() {

	}

	private static final String CONFIG_FILE_PATH = System.getProperty("user.dir")
			+ "/src/test/resources/config/config.properties";
	private static final String JSON_FILE_PATH = System.getProperty("user.dir")
			+ "/src/test/resources/config/config.json";
	
	private static final String RESOURCES_PATH=System.getProperty("user.dir")+"/src/test/resources";
	
	private static final String EXCEL_PATH = RESOURCES_PATH+"/testdata/TestData.xlsx";
	


	private static final String RUN_MANAGER_SHEET="RUN_MANAGER";
	private static final String ITERATION_SHEET="TEST_DATA";
	
	
	
	


	private static final int TIME_OUT = 10;
	private static final String EXTENT_REPORT_FOLDER_PATH = System.getProperty("user.dir") + "/extent-test-output";
	private static String extentReportFilePath = "";

	public static String getReportPath()  {
		if (extentReportFilePath.isEmpty()) {
			extentReportFilePath = createReportPath();
		}
		return extentReportFilePath;

	}

	private static String createReportPath() {
		if (PropertyUtils.getValue(ConfigProperties.OVERRIDE_REPORTS).equalsIgnoreCase("no")) {
			return EXTENT_REPORT_FOLDER_PATH +"/" + System.currentTimeMillis() + "/index.html";
		} else {
			return EXTENT_REPORT_FOLDER_PATH + "/index.html";
		}
	}

	public static String getConfigFilePath() {
		return CONFIG_FILE_PATH;
	}

	public static int getTimeOut() {
		return TIME_OUT;
	}

	public static String getConfigJSONFilePath() {
		return JSON_FILE_PATH;
	}

	public static String getExcelPath() {
		return EXCEL_PATH;
	}
	
	public static String getRunManagerSheet() {
		return RUN_MANAGER_SHEET;
	}

	public static String getIterationSheet() {
		return ITERATION_SHEET;
	}

}
