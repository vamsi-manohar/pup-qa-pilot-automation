package com.productsup.platform.pages.monitor;

import com.google.common.base.Supplier;
import com.productsup.platform.interfaces.DataSource;
import com.productsup.platform.interfaces.Monitor;
import com.productsup.platform.pages.monitor.levels.Account;
import com.productsup.platform.pages.monitor.levels.Project;
import com.productsup.platform.pages.monitor.levels.Site;
import com.productsup.platform.pages.site.datasources.types.FeedURL;

import java.util.HashMap;
import java.util.Map;

public class MonitorFactory {

    private static final Supplier<Monitor> account = () -> new Account();
    private static final Supplier<Monitor> project = () -> new Project();
    private static final Supplier<Monitor> site = () -> new Site();

    private static final Map<String, Supplier<Monitor>> MAP = new HashMap<>();

    static {
        MAP.put("Account", account);
        MAP.put("Project", project);
        MAP.put("Site", site);

    }

    public static Monitor get(String option) {
        return MAP.get(option).get();
    }
}
