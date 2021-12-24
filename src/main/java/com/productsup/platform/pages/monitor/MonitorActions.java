package com.productsup.platform.pages.monitor;

import com.productsup.platform.interfaces.Monitor;
import com.productsup.platform.pages.site.SiteNavigations;

import java.util.Map;

public class MonitorActions {

    private Monitor monitor;

    public void setMonitor(Monitor monitor)
    {
        this.monitor=monitor;
    }


    /***
     * Setting up the Monitor Interface with class objects
     * @param data
     * @return
     */
    public boolean setupMonitors(Map<String,String> data)
    {
        setMonitor(MonitorFactory.get(data.get("Platform_Hierrarchy")));
        monitor.selectPlatformHierrarchy(data);
        new MonitorPage()
                .setMonitorLevel(data.get("Monitor_At"))
                .selectErrorEvents(data)
                .setThresholdPercentage(data.get("Threshold%"))
                .setSeverityLevel(data.get("Severity"))
                .setErrorEventAction(data.get("Monitor_Action"));


//        monitor.setMonitorAtStage(data);
//        monitor.selectErrorEvent(data);
//        monitor.setSeverity(data);
//        monitor.setActions(data);
        return monitor.validateMonitor(data);
    }



    public void destroyMonitor()
    {
        MonitorPage monitorPage= new MonitorPage();
        if(!monitorPage.isOnMonitorPage())
        {
              new SiteNavigations().navigateToMonitorPage()
                      .deleteMonitorEvents();
        }
    }

    public void switchToMonitorFrame()
    {

    }




}
