package com.productsup.platform.enums;

public enum SiteSearchCategory {


    FAVOURITE_SITES("Favorite Sites"),
    SITE_NAMES("Site Names"),
    SITE_ID("Site IDs"),
    EXPORT_CHANNEL_ID("Export Channel IDs");

    private String data;

    SiteSearchCategory(String dataSource)
    {
        this.data=dataSource;
    }

    public String getData()
    {
        return data;
    }
}
