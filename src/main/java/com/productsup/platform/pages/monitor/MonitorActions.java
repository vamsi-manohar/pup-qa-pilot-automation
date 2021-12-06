package com.productsup.platform.pages.monitor;

import com.productsup.platform.interfaces.Monitor;

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
        monitor.selectPlatformHierrarchy(data);
        monitor.setMonitorAtStage(data);
        monitor.selectErrorEvent(data);
        monitor.setSeverity(data);
        monitor.setActions(data);
        return monitor.validateMonitor(data);
    }


    public void triggerErrorEventAction()
    {

    }


}
