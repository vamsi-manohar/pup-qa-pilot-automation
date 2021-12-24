package com.productsup.platform.pages.site.dataview;

import com.productsup.platform.driver.Driver;
import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.interfaces.DataView;
import com.productsup.platform.pages.account.AccountOverview;
import com.productsup.platform.pages.site.SiteNavigations;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class DataviewActions {

    private DataView dataView;


    public void setDataView(DataView dataView) {
        this.dataView = dataView;
    }

    public boolean setupDataView(Map<String, String> data) {

        new AccountOverview()
                .selectProject(data.get("Project_Name"))
                .selectSite(data.get("Site_Name"))
                .navigateToDataViewPage()
                .selectOptimizationLevelFor(data.get("Export_Channel"))
                .selectAttribute(data.get("Product_Attribute"));

        dataView.getDataBeforeRuleBox(data);
        dataView.addRuleBox(data);
        dataView.enterRuleDetails(data);
        dataView.saveRuleBox(data);
        dataView.ruleTransformation(data);
        return dataView.validateRuleBox(data);


    }


    public void
    destroyRuleBox(Map<String, String> data)
    {

            DataviewPage dataviewPage=new DataviewPage();
            dataviewPage.deleteRuleBox(data.get("Rule_Box"));
            DriverManager.getDriver().navigate().refresh();

        }

    }


