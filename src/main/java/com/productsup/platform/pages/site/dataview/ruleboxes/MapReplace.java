package com.productsup.platform.pages.site.dataview.ruleboxes;

import com.google.common.util.concurrent.Uninterruptibles;
import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.enums.WaitStrategy;
import com.productsup.platform.interfaces.DataView;
import com.productsup.platform.pages.site.dataview.DataviewPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MapReplace  extends DataviewPage implements DataView {

    @FindBy(css="iframe[class='viewFrame']")
    private WebElement viewFrame;

    @FindBy(css="li[data-title='map-replace'] textarea[name]")
    private WebElement placeholder;

    private List<String> dataBeforeRuleTransformation = new ArrayList<>();
    private  List<String>transformedData = new ArrayList<>();
    private  List<String>dataAfterRuleTransformation = new ArrayList<>();

    public MapReplace()
    {
        super();
        PageFactory.initElements(DriverManager.getDriver(),this);
    }


    @Override
    public void ruleTransformation(Map<String, String> data)
    {
        for(int i=0;i<dataBeforeRuleTransformation.size();i++)
        {
            if(dataBeforeRuleTransformation.get(i).contains(data.get("Attribute_Value")))

                this.transformedData.
                        add(dataBeforeRuleTransformation.get(i).replace(data.get("Attribute_Value"),
                                data.get("Replace_With")));
            }
        }


    @Override
    public void getDataBeforeRuleBox(Map<String, String> data) {
        this.dataBeforeRuleTransformation = getAttributeData(data.get("Product_Attribute"));
    }

    @Override
    public void addRuleBox(Map<String, String> data) {
        addRuleBox(data.get("Rule_Box"));
        applyRuleBox(data.get("Apply_Rulebox_At"),data.get("Rule_Box"));
    }

    @Override
    public void enterRuleDetails(Map<String, String> data)
    {

        String value = data.get("Attribute_Value")+":"+data.get("Replace_With");
        sendKeys(placeholder, WaitStrategy.VISIBLE,value);

    }

    @Override
    public void saveRuleBox(Map<String, String> data) {
        saveRuleBox();
    }

    @Override
    public boolean validateRuleBox(Map<String, String> data) {
        DriverManager.getDriver().navigate().refresh();
        Uninterruptibles.sleepUninterruptibly(10, TimeUnit.SECONDS);
        DriverManager.getDriver().switchTo().frame(viewFrame);
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        this.dataAfterRuleTransformation = getAttributeData(data.get("Product_Attribute"));
        System.out.println("Data Before Transformation :: " + dataBeforeRuleTransformation);
        System.out.println("Transformed Data ::  " + transformedData);
        System.out.println("Data After Transformation" + dataAfterRuleTransformation);
        if(!dataBeforeRuleTransformation.equals(dataAfterRuleTransformation)
                && transformedData.equals(dataAfterRuleTransformation))
        {
            System.out.println("Rule Box Applied Successfully !!!");
            return true;
        }


        return false;

    }
}
