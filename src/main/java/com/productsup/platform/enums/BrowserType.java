package com.productsup.platform.enums;

public enum BrowserType {

    CHROME("chrome"),
    FIREFOX("firefox"),
    SAFARI("safari"),
    EDGE("edge");

    private String data;

    BrowserType(String dataSource)
    {
        this.data=dataSource;
    }

    public String getData()
    {
        return data;
    }

}
