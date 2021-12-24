package com.productsup.platform.enums;

public enum Monitors {

    //Site Action Controls ENUMS

    IMPORT("import"),
    INTERMEDIATE("intermediate"),
    EXPORT("export"),
    GENERAL("general"),

    STOP_PROCESSING("Stop Processing"),
    EMAIL_NOTIFICATION("Email Notification"),

    //Error Events at Import Stage

    IMPORT_FAILED_FOR_DATA_SOURCE("Import failed for Data Source"),
    NO_CHANGE_IN_DATA_SOURCE("Number of days without a change in Data Sources"),
    IMPORTED_ITEMS_COUNT_DECREASED("Number of imported items decreased"),


    // Error Events at Intermediate Stage
    PERCENTAGE_OF_ADDED_ITEMS("Percentage of added items"),

    //Error Events at Export Stage

    FEED_UPLOAD_FAILED("Feed upload failed"),
    PERCENTAGE_OF_MISSING_MANDATORY_ATTRIBUTES("Percentage of missing values in all mandatory attributes"),
    //Severity Levels

    LOW("low"),
    HIGH("high"),
    MEDIUM("medium");



    private String data;

    Monitors(String stage)
    {
        this.data=stage;
    }

    public String getData()
    {
        return data;
    }
}
