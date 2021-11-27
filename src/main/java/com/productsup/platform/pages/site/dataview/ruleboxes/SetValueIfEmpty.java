package com.productsup.platform.pages.site.dataview.ruleboxes;

import com.google.common.util.concurrent.Uninterruptibles;
import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.enums.RuleBoxes;
import com.productsup.platform.enums.WaitStrategy;
import com.productsup.platform.interfaces.DataView;
import com.productsup.platform.pages.site.dataview.DataviewPage;
import com.productsup.platform.utils.DynamicLocatorStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SetValueIfEmpty extends DataviewPage implements DataView {

    private List<String> dataBeforeRuleTransformation = new ArrayList<>();
    private  List<String>transformedData = new ArrayList<>();
    private  List<String>dataAfterRuleTransformation = new ArrayList<>();


    @FindBy(css="iframe[class='viewFrame']")
    private WebElement viewFrame;

    @FindBy(css="iframe[name='editFrame']")
    private WebElement editFrame;


    private String ruleBoxInfo="#dataflow-ul-%s input[placeholder*='Static value']";

    public SetValueIfEmpty()
    {
        super();
        PageFactory.initElements(DriverManager.getDriver(),this);
    }


    @Override
    public void ruleTransformation(Map<String, String> data) {
        System.out.println("No Transformation is needed for this rule box...");
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
    public void enterRuleDetails(Map<String, String> data) {


        if(data.get("Apply_Rulebox_At").equalsIgnoreCase(getRuleBoxes(RuleBoxes.INTERMEDIATE)))
        {
            sendKeys(By.cssSelector(DynamicLocatorStrategy.
                    getDynamicLocator(ruleBoxInfo,"input")),"#DUMMY_INFO",WaitStrategy.PRESENCE);
        }
        else {

            sendKeys(By.cssSelector(DynamicLocatorStrategy.
                    getDynamicLocator(ruleBoxInfo, "output")), "#DUMMY_INFO", WaitStrategy.PRESENCE);
        }
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
        if(!dataBeforeRuleTransformation.equals(dataAfterRuleTransformation))
        {
            return true;
        }
        return false;


    }
}
