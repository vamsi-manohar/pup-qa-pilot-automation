package com.productsup.platform.pages.site.dataexports.destinations;

import com.productsup.platform.enums.ExportDestinations;
import com.productsup.platform.interfaces.DataExports;
import com.productsup.platform.pages.site.dataexports.DataExportsPage;


public class ExportDataSource extends DataExportsPage implements DataExports {


/*
    @Override
    public void selectExport() {

        addExports();
        selectExportChannel(ExportChannels.FP_TEST_AUTOMATION,"FP - Test Automation Export");

    }
*/

    @Override
    public void setDestination() {

        addDestinationDetails(ExportDestinations.EXPORT_DATASOURCE);
        //selectExportDestinationType(data.get("Export_Destination"));
    }

  /*  @Override
    public void limitProductsExport() {

    }*/
}
