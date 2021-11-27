package com.productsup.platform.interfaces;


import java.util.Map;

public interface DataView
{



    public void ruleTransformation(Map<String,String>data);
    public void getDataBeforeRuleBox(Map<String,String> data);
    public void addRuleBox(Map<String,String> data);
    public void enterRuleDetails(Map<String,String> data);
    public void saveRuleBox(Map<String,String> data);
    public boolean validateRuleBox(Map<String,String>data);

}
