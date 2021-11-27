package com.productsup.platform.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import com.productsup.platform.utils.DataProviderUtils;

public class AnnotationTransformer implements IAnnotationTransformer{
	
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod)
	{
		annotation.setDataProvider("getData");
		annotation.setDataProviderClass(DataProviderUtils.class);
		annotation.setRetryAnalyzer(RetryFailedTests.class);

	}

}
