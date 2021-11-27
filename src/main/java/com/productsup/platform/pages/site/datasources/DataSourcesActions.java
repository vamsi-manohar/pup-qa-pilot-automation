package com.productsup.platform.pages.site.datasources;



import com.productsup.platform.interfaces.DataSource;
import com.productsup.platform.pages.PlatformRouting;
import java.util.Map;

public class DataSourcesActions {

    private DataSource dataSource;

    public void setDataSourceInterface(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public void setupDataSource(Map<String, String> details) {
        new PlatformRouting().getLoginPage().loginToPlatform().selectProject(details.get("Project_Name"))
                .selectSite(details.get("Site_Name"))
                .navigateToDataSourcesPage();
       dataSource.selectDataSource();
       dataSource.enterDetails();
       dataSource.triggerAction();
    }

}
