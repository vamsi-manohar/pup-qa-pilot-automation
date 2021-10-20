package com.productsup.platform.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import com.productsup.platform.constants.Constants;
import com.productsup.platform.utils.ExcelUtils;

public class MethodInterceptor implements IMethodInterceptor {

	@Override
	public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {

		List<Map<String, String>> list = ExcelUtils.getTestData(Constants.getRunManagerSheet());
		List<IMethodInstance> results = new ArrayList<>();
		for (int i = 0; i <methods.size(); i++) 
		{
			for (int j = 0; j<list.size(); j++) {
				if (methods.get(i).getMethod().getMethodName().equalsIgnoreCase(list.get(j).get("TestCase"))

						&& list.get(j).get("Execute").equalsIgnoreCase("yes")) {

					methods.get(i).getMethod().setDescription(list.get(j).get("Testcase_Description"));
					methods.get(i).getMethod().setInvocationCount(Integer.parseInt(list.get(j).get("Invocation_Count")));
					methods.get(i).getMethod().setPriority(Integer.parseInt(list.get(j).get("Priority")));
					results.add(methods.get(i));
				}

			}
		}

		return results;
	}

}
