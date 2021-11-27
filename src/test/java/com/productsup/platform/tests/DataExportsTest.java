package com.productsup.platform.tests;

import com.productsup.platform.annotations.FrameworkAnnotation;
import com.productsup.platform.driver.Driver;
import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.enums.CategoryType;
import com.productsup.platform.enums.ExportDestinations;
import com.productsup.platform.interfaces.DataExports;
import com.productsup.platform.pages.site.SiteNavigations;
import com.productsup.platform.pages.site.dataexports.DataExportsAction;
import com.productsup.platform.pages.site.dataexports.DataExportsFactory;
import com.productsup.platform.pages.site.dataexports.DataExportsPage;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DataExportsTest extends BaseTest
{
    private static int counter=0;

    private List<String> attributeList=new ArrayList<>(Arrays.asList("brand","discounted price"
    ,"product name","product url","retail price","product id","product category"
    ,"image link","product description"));

    private String exportChannelTemplate=null;
    DataExportsAction dataExportsAction = new DataExportsAction();

    @FrameworkAnnotation(author = {"Vamsi"}, category = {CategoryType.SMOKE})
    @Test
    public void addExports(Map<String, String> data) {
        DataExportsPage dataExportsPage = new DataExportsPage();
        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions())
        {
            this.exportChannelTemplate = data.get("Export_Channel");
            dataExportsAction.setDataExports(DataExportsFactory.get(data.get("Export_Destination")));
            dataExportsAction.setupExportChannel(data);
            boolean flag = new DataExportsPage().navigateBackToMainExportsPage()
                    .verifyExportChannelIsAdded(data.get("Export_Channel"));
            softly.assertThat(flag).isEqualTo(true);
            softly.assertThat(dataExportsPage.
                    verifyAttributesOfExportChannelTemplate(data.get("Export_Channel"))).containsAll(attributeList);
            softly.assertThat(dataExportsPage.verifyExportTemplateInDataView(data.get("Export_Channel")))
                    .isEqualTo(true);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void resetDataExports()
    {
        System.out.println("Entered into After Method");
        System.out.println(counter);
        if(counter==0) {
            dataExportsAction.destroyExports(exportChannelTemplate);
            counter++;
        }

    }







}

