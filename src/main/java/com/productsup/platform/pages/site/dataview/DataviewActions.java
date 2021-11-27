package com.productsup.platform.pages.site.dataview;

import com.productsup.platform.interfaces.DataView;
import com.productsup.platform.pages.account.AccountOverview;
import org.testng.Assert;

import java.util.Map;

public class DataviewActions extends DataviewPage{

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
        Assert.assertTrue(dataView.validateRuleBox(data));
        return validateRuleBoxesAddedToDataflow(data.get("Product_Attribute"),data.get("Apply_Rulebox_At"),
                data.get("Rule_Box"));

    }
}
