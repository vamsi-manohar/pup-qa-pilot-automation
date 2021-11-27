package com.productsup.platform.tests;

import com.productsup.platform.annotations.FrameworkAnnotation;
import com.productsup.platform.enums.CategoryType;
import com.productsup.platform.pages.monitor.MonitorActions;
import com.productsup.platform.pages.monitor.MonitorFactory;
import com.productsup.platform.pages.monitor.MonitorPage;
import com.productsup.platform.reports.ExtentLogger;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

public class MonitorTest extends BaseTest {


    @FrameworkAnnotation(author = {"Vamsi"}, category = {CategoryType.SMOKE})
    @Test
    public void addMonitors(Map<String, String> data) {

        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            MonitorActions monitorActions = new MonitorActions();
            monitorActions.setMonitor(MonitorFactory.get(data.get("Platform_Hierrarchy")));
            ExtentLogger.info("Setup of monitors at Platform on :: " + data.get("Platform_Hierrarchy") + " Level");
            softly.assertThat(monitorActions.setupMonitors(data)).isEqualTo(true);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void destroyMonitor()
    {

            MonitorPage monitorPage = new MonitorPage();
            monitorPage.deleteMonitorEvents();

            //
        }


}
