package com.productsup.platform.enums;

public enum DataSourceTypes {

    //Site Action Controls ENUMS

    IMPORT("Import"),
    EXPORT("Export"),
    RUN("Run"),

    //Data Source Actions
    PAUSE("pause"),
    ACTIVATE("activate"),

    FEED_URL("Feed URL"),
    GOOGLE_SHEETS("Google Sheets"),
    GOOGLE_MERCHANT_CENTER("Google Merchant Center");


    private String data;

    DataSourceTypes(String dataSource)
    {
        this.data=dataSource;
    }

    public String getData()
    {
        return data;
    }

}
