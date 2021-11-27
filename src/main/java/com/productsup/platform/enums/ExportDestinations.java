package com.productsup.platform.enums;

public enum ExportDestinations {


  /*  GOOGLE_MERCHANT_CENTER("Google Merchant Center"),
    FACEBOOK_DYNAMIC_ADS("Facebook Dynamic Ads"),
    FP_TEST_AUTOMATION("FP - Test Automation Export"),*/






    // Destination Details

    SFTP_SERVER("SFTP/FTP/FTPS Server (with optional proxy)"),
    EXCEL_MACRO("Excel Macro Files (xlsm)"),
    EXPORT_DATASOURCE("Export to Data Source");






    private String data;

    ExportDestinations(String dataSource)
    {
        this.data=dataSource;
    }

    public  String getData()
    {
        return data;
    }

}
