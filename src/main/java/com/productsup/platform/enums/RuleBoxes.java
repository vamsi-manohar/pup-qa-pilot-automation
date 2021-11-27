package com.productsup.platform.enums;

public enum RuleBoxes

{
    INTERMEDIATE("Intermediate"),
    EXPORT("Export"),


    //Rule Boxes Types

    UPPERCASE_TO_HUMAN("uppercase to human"),
    REMOVE_HTML_TAGS("remove html tags"),
    SET_VALUE_IF_EMPTY("set value if empty"),
    CAPITALIZE_WORDS("capitalize words"),
    MAP_REPLACE("map-replace"),
    REMOVE_WHITESPACES("remove consecutive whitespace");

    private String data;

    RuleBoxes(String stage)
    {
        this.data=stage;
    }

    public String getData()
    {
        return data;
    }
}
