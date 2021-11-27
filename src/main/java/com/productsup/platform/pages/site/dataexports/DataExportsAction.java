package com.productsup.platform.pages.site.dataexports;

import com.productsup.platform.enums.ExportDestinations;
import com.productsup.platform.interfaces.DataExports;
import com.productsup.platform.pages.account.AccountOverview;
import com.productsup.platform.pages.site.SiteNavigations;
import com.productsup.platform.pages.site.dataflow.DataFlowPage;
import org.testng.Assert;

import java.util.Map;

public class DataExportsAction {

//

    private DataExports dataExports;

    public void setDataExports(DataExports dataExports)
    {
        this.dataExports=dataExports;
    }


    public void setupExportChannel(Map<String,String> data)
    {

        DataExportsPage dataExportsPage = new DataExportsPage();

            new AccountOverview().selectProject(data.get("Project_Name"))
                .selectSite(data.get("Site_Name"))
                .navigateToDataExportsPage();
        dataExportsPage.selectExportChannel(data.get("Export_Channel"));
        dataExports.setDestination();



    }

    public boolean peformExportOperations(Map<String,String>data)
    {

        new SiteNavigations().navigateToDataFlowPage()
                .selectExportChannel(data.get("Export_Channel"))
                .addAdditionalAttributes(DataFlowPage.INTERMEDIATE,"Automated_attr")
                .addAdditionalAttributes(DataFlowPage.EXPORT,"Automated_attr")
                .performMappingFromIntermediateToExport();
        return true;
    }



/*
    public boolean peformExportOperationsDemo()
    {
        boolean flag = new DataExportsPage().navigateBackToMainExportsPage()
                .verifyExportChannelIsAdded(ExportChannels.FP_TEST_AUTOMATION);
        Assert.assertTrue(flag);
        new SiteNavigations().navigateToDataFlowPage()
                .selectExportChannel("FP - Test Automation Export")
                .performMappingFromIntermediateToExport();
        return true;
    }
*/

    public void destroyExports(String exportChannelName)
    {
        new DataExportsPage().deleteExportsTemplate(exportChannelName);
    }

}
