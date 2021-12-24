package com.productsup.platform.tests;

import com.productsup.platform.annotations.FrameworkAnnotation;
import com.productsup.platform.enums.CategoryType;

import com.productsup.platform.pages.site.datasources.DataSourcesActions;
import com.productsup.platform.pages.site.datasources.DataSourcesFactory;
import com.productsup.platform.pages.site.datasources.DataSourcesPage;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.assertj.core.api.SoftAssertions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.Map;

public class DataSourcesTest extends BaseTest{

    private int initialCount=0;
    private int addedAdditionalFeed=7487;
    private String actionType=null;
    SoftAssertions softly = new SoftAssertions();
    boolean executionSuccess=false;




    @FrameworkAnnotation(author = {"Vamsi"}, category = {CategoryType.SMOKE})
    @Test
    public void validateDataSourceSetup(Map<String, String> data)
    {


            this.actionType = data.get("Platform_Action");
            try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
                logEnvironmentInfo();
                DataSourcesPage dataSourcesPage = new DataSourcesPage();
                int total = initialCount + addedAdditionalFeed;
                DataSourcesActions dataSourcesActions = new DataSourcesActions();
                dataSourcesActions.setDataSourceInterface(DataSourcesFactory.get(data.get("Data_Source")));
                dataSourcesActions.setupDataSource(data);
                int importedDataFeed = dataSourcesPage.getImportedItemsCount();
                softly.assertThat(importedDataFeed).isGreaterThan(100)
                        .isEqualTo(total);
                executionSuccess=true;
           }



    }



    @AfterMethod()
    public void deleteDataSource() {


        if(executionSuccess) {
            DataSourcesPage dataSourcesPage = new DataSourcesPage();
            try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions())
            {
                int importedItemsCountAfterRemoval = dataSourcesPage.removeDataSourceAndImport(actionType);
                softly.assertThat(importedItemsCountAfterRemoval == initialCount).isEqualTo(true);
                executionSuccess=false;
            }
        }
    }
    }



