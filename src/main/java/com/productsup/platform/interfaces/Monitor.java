package com.productsup.platform.interfaces;

import java.util.Map;

public interface Monitor {

    public void selectPlatformHierrarchy(Map<String,String> data);
    public void setMonitorAtStage(Map<String, String> data);
    public void selectErrorEvent(Map<String, String> data);
    public void setSeverity(Map<String, String> data);
    public void setActions(Map<String, String> data);
    public boolean validateMonitor(Map<String,String>data);

}
