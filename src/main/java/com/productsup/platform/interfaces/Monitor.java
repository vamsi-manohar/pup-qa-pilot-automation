package com.productsup.platform.interfaces;

import java.util.Map;

public interface Monitor {

    public void selectPlatformHierrarchy(Map<String,String> data);
    public boolean validateMonitor(Map<String,String>data);

}
