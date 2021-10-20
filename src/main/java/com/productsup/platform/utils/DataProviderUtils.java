package com.productsup.platform.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.productsup.platform.constants.Constants;

public final class DataProviderUtils {

	static List<Map<String, String>> data = new ArrayList<>();

	private DataProviderUtils() {

	}

	private static List<Map<String, String>> list = new ArrayList<>();

	@DataProvider(parallel = false)
	public static Object[] getData(Method m) {
		String testName = m.getName();

		if (list.isEmpty()) {
			list = ExcelUtils.getTestData(Constants.getIterationSheet());
		}

		List<Map<String, String>> executionList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get("TestCase").equalsIgnoreCase(testName)
					&& list.get(i).get("Execute").equalsIgnoreCase("yes")) {

				executionList.add(list.get(i));
				data.add(list.get(i));
			}

		}
		return executionList.toArray();
	}

	public static List<Map<String, String>> getAllData() {
		return data;
	}

}
