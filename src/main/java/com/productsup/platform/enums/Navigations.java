package com.productsup.platform.enums;

public enum Navigations {

  //  DASHBOARD,
//    AUTHENTICATION,
    //USER_MANAGEMENT,

    DATA_SOURCES("Data Sources"),
    //EXPORTS,
    DATA_VIEW("Data View"),

    DATA_EXPORTS("Exports A/B"),

    DATA_FLOW("Dataflow"),

    MONITOR("Monitor"),

    DASHBOARD("Dashboard");
    //MONITOR;

    private String data;

    Navigations(String dataSource)
    {
        this.data=dataSource;
    }

    public String getData()
    {
        return data;
    }
}

