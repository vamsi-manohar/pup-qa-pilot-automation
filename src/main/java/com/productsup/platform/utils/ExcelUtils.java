package com.productsup.platform.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.productsup.platform.constants.Constants;
import com.productsup.platform.exceptions.FrameworkExceptions;
import com.productsup.platform.exceptions.InvalidTestDataPathException;


public final class ExcelUtils {

	private ExcelUtils() {

	}

	public static List<Map<String, String>> getTestData(String sheetName) {

		List<Map<String, String>> list = null;

		try (FileInputStream fs = new FileInputStream(Constants.getExcelPath())) {
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			int rows = sheet.getLastRowNum();
			int cols = sheet.getRow(0).getLastCellNum();
			Map<String, String> map = null;
			list = new ArrayList<>();
			for (int i = 1; i <= rows; i++) {
				map = new HashMap<>(); 
				for (int j = 0; j < cols; j++) {
					String key = sheet.getRow(0).getCell(j).getStringCellValue();
					String value = sheet.getRow(i).getCell(j).getStringCellValue();
					map.put(key, value);
				}
				list.add(map);
			}

		} catch (FileNotFoundException e) {
			
			//Customizing the stack trace
			
			/*
			 * StackTraceElement[] ex = e.getStackTrace(); ex[0]=new
			 * StackTraceElement("org.personal.utils.ExcelUtils",
			 * "getTestData","ExcelUtils.java",25); e.setStackTrace(ex);
			 */
			
			throw new InvalidTestDataPathException("Test Data File is not found");
		} catch (IOException e) {
			throw new FrameworkExceptions("IO Exception while reading Excel File");
		}
		return list;
	}
}
