package com.productsup.platform.tests;

import com.productsup.platform.annotations.FrameworkAnnotation;
import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.enums.CategoryType;
import com.productsup.platform.enums.DataSourceTypes;
import com.productsup.platform.pages.monitor.MonitorActions;
import com.productsup.platform.pages.monitor.MonitorFactory;
import com.productsup.platform.pages.monitor.MonitorPage;
import com.productsup.platform.pages.platformactions.PlatformActions;
import com.productsup.platform.pages.site.SiteDashboard;
import com.productsup.platform.pages.site.SiteNavigations;
import com.productsup.platform.pages.site.errorlog.ErrorLogPage;
import com.productsup.platform.reports.ExtentLogger;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MonitorTest extends BaseTest {

    MonitorActions monitorActions = new MonitorActions();

    boolean destroyMonitor=false;
    boolean destroyErrorLogs=false;
    boolean resetDataSource=false;

    private final String ERROR_MESSAGE="Aborted due to Error Monitoring Configuration";
    private String dataSourceName=null;
    private String platformAction=null;

    @FrameworkAnnotation(author = {"Vamsi"}, category = {CategoryType.SMOKE})
   // @Test
    public void addMonitors(Map<String, String> data) {

        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            MonitorActions monitorActions = new MonitorActions();
            monitorActions.setMonitor(MonitorFactory.get(data.get("Platform_Hierrarchy")));
            ExtentLogger.info("Setup of monitors at Platform on :: " + data.get("Platform_Hierrarchy") + " Level");
            softly.assertThat(monitorActions.setupMonitors(data)).isEqualTo(true);
            destroyMonitor=true;
        }
    }


    @FrameworkAnnotation(author = {"Vamsi"}, category = {CategoryType.SMOKE})
    @Test
    public void setErrorEventsAndValidate(Map<String, String> data) {
        SiteNavigations siteNavigations=new SiteNavigations();
        SiteDashboard siteDashboard= new SiteDashboard();
        ErrorLogPage errorLogPage = new ErrorLogPage();

        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions())
        {
            logEnvironmentInfo();
            this.dataSourceName=data.get("Data_Source");
            this.platformAction=data.get("Platform_Action");

            monitorActions.setupMonitors(data);
            siteNavigations.navigateToDataSourcesPage()
                    .performActionOnDataSource(data.get("Data_Source"), DataSourceTypes.PAUSE)
                    .triggerAction(data.get("Platform_Action"));
            int errorCount = siteNavigations.navigateToDashboardPage()
                    .getErrorsCount();
            softly.assertThat(errorCount).isEqualTo(1);
            List<String> errorDetails=  siteDashboard.navigateToErrorDetails().getErrorDetail();
            softly.assertThat(errorDetails).contains(ERROR_MESSAGE);


        }

        destroyErrorLogs=true;
        resetDataSource=true;
        destroyMonitor=true;
    }

    @FrameworkAnnotation(author = {"Vamsi"}, category = {CategoryType.SMOKE})
    @Test
    public void setErrorEventAtExportStage(Map<String, String> data) {
        SiteNavigations siteNavigations=new SiteNavigations();
        MonitorPage monitorPage = new MonitorPage();
        SiteDashboard siteDashboard= new SiteDashboard();
        ErrorLogPage errorLogPage = new ErrorLogPage();

        logEnvironmentInfo();
        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions())
        {
            monitorActions.setupMonitors(data);
            monitorPage.switchToMonitorFrame();
            new PlatformActions().triggerAction(data.get("Platform_Action"));
            switchToDefaultContent();
            int errorCount = siteNavigations.navigateToDashboardPage()
                    .getErrorsCount();
            softly.assertThat(errorCount).isEqualTo(1);
            List<String> errorDetails=  siteDashboard.navigateToErrorDetails().getErrorDetail();
            softly.assertThat(errorDetails).contains(ERROR_MESSAGE);

        }
        destroyErrorLogs=true;
        destroyMonitor=true;
    }

    @AfterMethod(alwaysRun = true)
    public void destroyMonitor()
    {
        try {

            if (destroyMonitor)
            {
                monitorActions.destroyMonitor();
            }

            destroyMonitor = false;
        }catch(Exception e)
        {
            e.getMessage();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void destroyErrorLogs()
    {
        try {
            if (destroyErrorLogs) {
                ErrorLogPage errorLogPage = new ErrorLogPage();
                errorLogPage.navigateBackToErrorLog()
                        .deleteErrorLogs();

            }
            destroyErrorLogs = true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }




    @AfterMethod(alwaysRun = true)
    public void resetDataSource()
    {
        try {
            if (resetDataSource) {
                SiteNavigations siteNavigations = new SiteNavigations();
                siteNavigations.navigateToDataSourcesPage()
                        .performActionOnDataSource(dataSourceName, DataSourceTypes.ACTIVATE)
                        .triggerAction(platformAction);
            }

            resetDataSource = false;
        }catch(Exception e)
        {
            e.getMessage();
        }

    }






}
