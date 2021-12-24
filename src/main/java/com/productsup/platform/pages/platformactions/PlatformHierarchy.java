package com.productsup.platform.pages.platformactions;

import java.util.List;
import java.util.Map;


/**
 * 
 * @author Vamsi Manohar
 * 20-Oct-2021
 * 4:21:01 am
 * 
 * 
 *  All the hierarchical classes under this package must implement this interface 
 *  
 */
public interface PlatformHierarchy {

	void selectPlatformHierarchy(Map<String, String> details);
	List<String> availableMenuOptions(Map<String, String> details);
}
