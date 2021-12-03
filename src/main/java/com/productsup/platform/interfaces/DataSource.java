package com.productsup.platform.interfaces;

import com.google.common.util.concurrent.Uninterruptibles;
import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.enums.DataSourceTypes;
import com.productsup.platform.enums.WaitStrategy;
import com.productsup.platform.pages.BasePage;
import com.productsup.platform.pages.site.SiteNavigations;
import com.productsup.platform.pages.site.datasources.DataSourcesPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public interface DataSource {



     void selectDataSource();
     void enterDetails();
     void triggerAction(Map<String,String> data);












}
