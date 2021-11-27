package com.productsup.platform.pages.site.dataview;

import com.google.common.base.Supplier;
import com.productsup.platform.interfaces.DataView;
import com.productsup.platform.pages.site.dataview.ruleboxes.*;

import java.util.HashMap;
import java.util.Map;

public class DataViewFactory extends DataviewPage {

    private static final Supplier<DataView> uppercaseToHuman = () -> new UppercaseToHuman();
    private static final Supplier<DataView> removeHTMLTags = () -> new RemoveHTMLTags();
    private static final Supplier<DataView> setValueIfEmpty = () -> new SetValueIfEmpty();
    private static final Supplier<DataView> capitalizeWords = () -> new CapitalizeWords();
    private static final Supplier<DataView> mapReplace = () -> new MapReplace();
    private static final Supplier<DataView> removeWhiteSpaces = () -> new RemoveWhiteSpaces();

    private static final Map<String, Supplier<DataView>> MAP = new HashMap<>();

    static {
        MAP.put("UPPERCASE_TO_HUMAN", uppercaseToHuman);
        MAP.put("REMOVE_HTML_TAGS", removeHTMLTags);
        MAP.put("SET_VALUE_IF_EMPTY",setValueIfEmpty);
        MAP.put("CAPITALIZE_WORDS",capitalizeWords);
        MAP.put("MAP_REPLACE",mapReplace);
        MAP.put("REMOVE_WHITESPACES",removeWhiteSpaces);


    }

    public static DataView get(String option) {
        return MAP.get(option).get();
    }
}
