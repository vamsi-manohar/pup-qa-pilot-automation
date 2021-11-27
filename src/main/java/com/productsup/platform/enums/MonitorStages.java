package com.productsup.platform.enums;

public enum MonitorStages {

    //Site Action Controls ENUMS

    IMPORT("import"),
    INTERMEDIATE("intermediate"),
    EXPORT("export"),
    GENERAL("general"),

    //Error Events at Import Stage

    IMPORT_FAILED_FOR_DATA_SOURCE("Import failed for Data Source"),
    NO_CHANGE_IN_DATA_SOURCE("Number of days without a change in Data Sources"),


    // Error Events at Intermediate Stage
    PERCENTAGE_OF_ADDED_ITEMS("Percentage of added items"),

    //Error Events at Export Stage

    FEED_UPLOAD_FAILED("Feed upload failed"),
    //Severity Levels

    LOW("low"),
    HIGH("high"),
    MEDIUM("medium");



    private String data;

    MonitorStages(String stage)
    {
        this.data=stage;
    }

    public String getData()
    {
        return data;
    }
}
