package com.productsup.platform.pages.site;

import com.google.common.util.concurrent.Uninterruptibles;
import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.enums.Navigations;
import com.productsup.platform.enums.WaitStrategy;
import com.productsup.platform.interfaces.PlatformNavigation;
import com.productsup.platform.pages.BasePage;
import com.productsup.platform.pages.monitor.MonitorPage;
import com.productsup.platform.pages.site.dataexports.DataExportsPage;
import com.productsup.platform.pages.site.dataflow.DataFlowPage;
import com.productsup.platform.pages.site.dataview.DataviewPage;
import com.productsup.platform.pages.site.datasources.DataSourcesPage;
import com.productsup.platform.utils.DynamicLocatorStrategy;
import org.openqa.selenium.By;

import java.util.EnumMap;
import java.util.concurrent.TimeUnit;

public class SiteNavigations extends BasePage implements PlatformNavigation

{

    private String menu = "//*[text()='%s']";
    private static EnumMap<Navigations,String>navigationsMapping = new EnumMap<Navigations, String>(Navigations.class);
    static
    {
        for(Navigations navigation: Navigations.values())
        {
            navigationsMapping.put(navigation,navigation.getData());
        }
    }

    private DataSourcesPage dataSourcesPage;
    private DataviewPage  dataviewPage;
    private SiteDashboard dashboardPage;
    private DataExportsPage dataExportsPage;
    private DataFlowPage dataFlowPage;
    private MonitorPage monitorPage;



    public SiteNavigations()
    {
        this.dataviewPage=new DataviewPage();
        this.dataSourcesPage=new DataSourcesPage();
        this.dashboardPage = new SiteDashboard();
        this.dataFlowPage = new DataFlowPage();
        this.monitorPage = new MonitorPage();
    }


    public DataSourcesPage navigateToDataSourcesPage()
    {
        navigateToMenuBar();
        navigateTo(menu,Navigations.DATA_SOURCES);
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        return dataSourcesPage;
    }


    public DataviewPage navigateToDataViewPage() {
        navigateToMenuBar();
        navigateTo(menu,Navigations.DATA_VIEW);
        return dataviewPage;
    }

    public SiteDashboard navigateToDashboardPage() {
        navigateToMenuBar();
        navigateTo(menu,Navigations.DASHBOARD);
        DriverManager.getDriver().navigate().refresh();
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        return dashboardPage;
    }

    public DataExportsPage navigateToDataExportsPage()
    {
        navigateToMenuBar();
        navigateTo(menu,Navigations.DATA_EXPORTS);
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        return dataExportsPage;

    }

    public DataFlowPage navigateToDataFlowPage()
    {
        navigateToMenuBar();
        navigateTo(menu,Navigations.DATA_FLOW);
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        return dataFlowPage;
    }

    public MonitorPage navigateToMonitorPage()
    {
        navigateToMenuBar();
        navigateTo(menu,Navigations.MONITOR);
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        return monitorPage;
    }




    public String getNavigationMapping(Navigations navigation)
    {
        return navigation.getData();
    }

    @Override
    public void navigateTo(String menu, Navigations navigations) {
        click(By.xpath(DynamicLocatorStrategy.getDynamicLocator(menu, getNavigationMapping(navigations))), WaitStrategy.CLICKABLE);
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);

    }





}
