package com.productsup.platform.pages.site.datasources;

import com.google.common.base.Supplier;
import com.productsup.platform.interfaces.DataSource;
import com.productsup.platform.pages.site.datasources.types.FeedURL;

import java.util.HashMap;
import java.util.Map;

public class DataSourcesFactory {


    private static final Supplier<DataSource> feedURL = () -> new FeedURL();


    private static final Map<String, Supplier<DataSource>> MAP = new HashMap<>();

    static {
        MAP.put("Feed URL", feedURL);

    }

    public static DataSource get(String option) {
        return MAP.get(option).get();
    }
}
