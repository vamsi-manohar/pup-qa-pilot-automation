package com.productsup.platform.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Objects;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.productsup.platform.constants.Constants;
import com.productsup.platform.enums.CategoryType;

public final class ExtentReport {

	private static ExtentReports extent;

	private ExtentReport() {

	}

	public static void initReport() {

		if (Objects.isNull(extent)) {
			extent = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter(Constants.getReportPath());
			extent.attachReporter(spark);
			spark.config().setTheme(Theme.DARK);
			spark.config().setDocumentTitle("Automation Reports");
			spark.config().setReportName("Extent Report demo");
		}

	}

	public static void flushReport() {
		if (Objects.nonNull(extent)) {
			extent.flush();
		}
		ExtentManager.unload();
		try {
			Desktop.getDesktop().browse(new File(Constants.getReportPath()).toURI());
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static void createTest(String testCaseName) {
		ExtentManager.setExtentTest(extent.createTest(testCaseName));
	

	}

	public static void addAuthors(String[] authors) {
		for (String author : authors) {
			ExtentManager.getExtentTest().assignAuthor(author);
		}
	}

	public static void addCategories(CategoryType[] categories) {

		for (CategoryType category : categories) {
			ExtentManager.getExtentTest().assignCategory(category.toString());
		}
	}
}
