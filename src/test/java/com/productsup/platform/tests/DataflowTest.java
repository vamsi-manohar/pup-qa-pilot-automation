package com.productsup.platform.tests;


import com.productsup.platform.annotations.FrameworkAnnotation;
import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.enums.CategoryType;
import com.productsup.platform.pages.account.AccountOverview;
import com.productsup.platform.pages.site.SiteNavigations;
import com.productsup.platform.pages.site.dataflow.DataFlowPage;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Map;

public class DataflowTest extends BaseTest

{
    private String addedAttribute = "automated_attribute";
    private String exportChannel;


    @FrameworkAnnotation(author = {"Vamsi"}, category = {CategoryType.SMOKE})
    @Test
    public void addAdditionalAttributesAndLink(Map<String, String> data) {
        DataFlowPage dataFlowPage = new DataFlowPage();

        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions())
        {
            this.exportChannel=data.get("Export_Channel");
            new AccountOverview()
                    .selectProject(data.get("Project_Name"))
                    .selectSite(data.get("Site_Name"))
                    .navigateToDataFlowPage()
                    .selectExportChannel(data.get("Export_Channel"));
            dataFlowPage.addAdditionalAttributes(dataFlowPage.INTERMEDIATE,addedAttribute);
            dataFlowPage.addAdditionalAttributes(dataFlowPage.EXPORT,addedAttribute);
            dataFlowPage.linkToExportByDragAndDrop(addedAttribute);
            DriverManager.getDriver().switchTo().defaultContent();
            softly.assertThat(new SiteNavigations().navigateToDataViewPage()
                    .selectOptimizationLevelFor(data.get("Export_channel")).getColumnLables()).contains(addedAttribute);

        }
    }


   @AfterMethod(alwaysRun = true)
    public void deleteAttributes()
    {
        try {
            DataFlowPage dataFlowPage = new DataFlowPage();
            DriverManager.getDriver().switchTo().defaultContent();
            new SiteNavigations().navigateToDataFlowPage().selectExportChannel(exportChannel)
                    .deleteLinks(addedAttribute, dataFlowPage.OUTPUT_MODE)
                    .deleteAttributes(addedAttribute, dataFlowPage.INTERMEDIATE)
                    .deleteAttributes(addedAttribute, dataFlowPage.EXPORT);
        }catch(Exception e)
        {
            e.getMessage();
        }
    }

}
