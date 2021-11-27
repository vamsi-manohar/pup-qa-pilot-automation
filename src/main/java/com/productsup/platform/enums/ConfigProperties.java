package com.productsup.platform.enums;



/**
 * To restrict the values used from property file. Without using enums there can be a possibility of null pointer exception if the property 
 * is not correctly provided.
 * 
 * Whenever a new value is added to the property file, corresponding enum should be created here.
 * 
 * @author Vamsi Manohar
 * 22-Sep-2021
 * 7:19:26 am
 */
public enum ConfigProperties {
	
	URL,
	USERNAME,
	PASSWORD,
	MAIN_FEED_URL,
	FEED_USERNAME,
	FEED_PASSWORD,
	PASSED_STEPS_SCREENSHOTS,
	FAILED_STEPS_SCREENSHOTS,
	SKIPPED_STEPS_SCREENSHOTS,
	RUN_MODE,
	SELENIUM_GRID_URL,
	RETRY_FAILED_TESTS,
	OVERRIDE_REPORTS;
	
	
	

}
