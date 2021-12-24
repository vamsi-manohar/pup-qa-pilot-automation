package com.productsup.platform.pages.site.dataexports.destinations;

import com.productsup.platform.enums.ExportDestinations;
import com.productsup.platform.interfaces.DataExports;
import com.productsup.platform.pages.site.dataexports.DataExportsPage;


public class ExportDataSource extends DataExportsPage implements DataExports {


    @Override
    public void setDestination() {
        addDestinationDetails(ExportDestinations.EXPORT_DATASOURCE);
    }

}
