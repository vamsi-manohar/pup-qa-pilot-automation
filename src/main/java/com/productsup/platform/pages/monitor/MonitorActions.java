package com.productsup.platform.pages.monitor;

import com.productsup.platform.interfaces.Monitor;

import java.util.Map;

public class MonitorActions {

    private Monitor monitor;

    public void setMonitor(Monitor monitor)
    {
        this.monitor=monitor;
    }

    public boolean setupMonitors(Map<String,String> data)
    {
        monitor.selectPlatformHierrarchy(data);
        monitor.setMonitorAtStage(data);
        monitor.selectErrorEvent(data);
        monitor.setSeverity(data);
       return monitor.validateMonitor(data);
    }
}
