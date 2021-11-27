package com.productsup.platform.pages.monitor.levels;

import com.productsup.platform.enums.MonitorStages;
import com.productsup.platform.interfaces.Monitor;
import com.productsup.platform.pages.PlatformRouting;
import com.productsup.platform.pages.account.AccountOverview;
import com.productsup.platform.pages.monitor.MonitorPage;
import com.productsup.platform.pages.site.SiteNavigations;

import java.util.Map;

public class Project extends MonitorPage implements Monitor
{

    @Override
    public void selectPlatformHierrarchy(Map<String, String> data) {
        new AccountOverview().selectProject(data.get("Project_Name"));
        new SiteNavigations().navigateToMonitorPage();
    }
    @Override
    public void setMonitorAtStage(Map<String, String> data)
    {
        setMonitorLevel(data.get("Monitor_At"));
    }

    @Override
    public void selectErrorEvent(Map<String, String> data) {
        setErrorEvents(data);
    }

    @Override
    public void setSeverity(Map<String, String> data) {
        setSeverityLevel(data.get("Severity"));
    }

    @Override
    public boolean validateMonitor(Map<String, String> data) {
        return validateMonitorSetup(MonitorStages.valueOf(data.get("Error_Event")));
    }
}
