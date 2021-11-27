package com.productsup.platform.pages.site.dataexports;

import com.google.common.base.Supplier;
import com.productsup.platform.interfaces.DataExports;
import com.productsup.platform.pages.site.dataexports.destinations.ExportDataSource;

import java.util.HashMap;
import java.util.Map;

public class DataExportsFactory {


    private static final Supplier<DataExports> exportToDataSource = () -> new ExportDataSource();


    private static final Map<String, Supplier<DataExports>> MAP = new HashMap<>();

    static {
        MAP.put("EXPORT_DATASOURCE", exportToDataSource);

    }

    public static DataExports get(String option) {
        return MAP.get(option).get();
    }
}
