package com.productsup.platform.pages.site.dataflow;

import com.google.common.util.concurrent.Uninterruptibles;
import com.productsup.platform.driver.DriverManager;

import com.productsup.platform.enums.WaitStrategy;
import com.productsup.platform.pages.BasePage;
import com.productsup.platform.utils.DynamicLocatorStrategy;
import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DataFlowPage extends BasePage {

    private WebDriverWait wait;

    public static final String INTERMEDIATE = "intermediate";
    public static final String EXPORT = "export";

    private String addAttributelevel="li[class*='add-%s-column']";
    private String attributeInfo="input[class*='add-%s-column']";
    private String saveAttribute="button[class*='add-%s-column']";
    private String ruleBoxLines="g[column='%replace1%'][mode='%replace2%'] line[boxes]";
    private String circle = "g[column='%replace1%][mode='%replace2%'] circle[class='step']";

    Map<String, Integer> intermediateToExportMapping = new CaseInsensitiveMap<String, Integer>();
    List<String> exportAttr = new ArrayList<String>();

    @FindBy(css="button[class*='primary btn-sm dropdown-toggle']")
    private WebElement expandButton;

    @FindBy(css="div[class='btn-group open']")
    private WebElement checkStatusButton;

    @FindBy(css="ul[class*='dropdown-menu'] a div[class*='export-name']")
    private List<WebElement> availableExportChannels;

    @FindBy(css="#dataflowFrame")
    private WebElement dataflowFrame;


    @FindBy(css="th[class*='export']>span>span:nth-child(1)")
    private WebElement exportStageLabel;

    @FindBy(css="td[class='export-columns td-last'] ul li")
    private List<WebElement>exportColumnAttributes;

    @FindBy(css="td[class='intermediate-columns'] a")
    public List<WebElement>intermediateColumnAttributes;

    @FindBy(css="div[class='connection-output output-intermediate']")
    public List<WebElement>intermediateList;

    @FindBy(css="div[class='connection-input input-target enable-input-click']")
    public List<WebElement>exportList;



    public DataFlowPage()
    {
        PageFactory.initElements(DriverManager.getDriver(),this);
        this.wait = new WebDriverWait(DriverManager.getDriver(), 30);

    }



    public void expandChannelsList()
    {
        DriverManager.getDriver().switchTo().frame(dataflowFrame);
        click(expandButton,WaitStrategy.CLICKABLE);
        this.wait.until(d->checkStatusButton.isDisplayed());

    }


    /**
     * This method selects export channel
     * @param channelName
     * @return
     */
    public DataFlowPage selectExportChannel(String channelName)
    {
        expandChannelsList();
         for(int i=0;i<availableExportChannels.size();i++)
         {
             if(availableExportChannels.get(i).getText().contains(channelName))
             {
                 System.out.println(availableExportChannels.get(i).getText());
                 click(availableExportChannels.get(i), WaitStrategy.CLICKABLE);
                 boolean flag = this.wait.until(d->exportStageLabel.getText().equalsIgnoreCase(channelName));
             }
         }
         return this;
    }


    public DataFlowPage addAdditionalAttributes(String platformLevel, String info)
    {
        scrollIntoView(By.cssSelector(DynamicLocatorStrategy.getDynamicLocator(addAttributelevel,platformLevel)));
       click(By.cssSelector(DynamicLocatorStrategy.getDynamicLocator(addAttributelevel,platformLevel)),WaitStrategy.CLICKABLE);
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
       sendKeys(By.cssSelector(DynamicLocatorStrategy.getDynamicLocator(attributeInfo,platformLevel)),info,WaitStrategy.PRESENCE);
       click(By.cssSelector(DynamicLocatorStrategy.getDynamicLocator(saveAttribute,platformLevel)),WaitStrategy.CLICKABLE);
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
       return this;
    }


    public void performMappingFromIntermediateToExport()
    {
        getAllExportAttributes();
        System.out.println("Start time::");
        System.out.println("Exported List ::: " + exportAttr);
        for (int i = 0; i < intermediateColumnAttributes.size(); i++)
        {
            String attributeName = intermediateColumnAttributes.get(i).getText();
            System.out.println("Attribute Name :: " + attributeName);
          if(containsIgnoreCase(attributeName,exportAttr))
            {
                if (intermediateColumnAttributes.get(i).getAttribute("class").contains("not")) {
                    intermediateList.get(i).click();
                    int indexValue = getIndexValueForTheAttribute(attributeName);
                    exportList.get(indexValue).click();

                }
            }
        }
    }




    public boolean containsIgnoreCase(String str, List<String> list){
        for(String i : list){
            if(i.equalsIgnoreCase(str))
                return true;
        }
        return false;
    }


    public List<String> getAllExportAttributes() {

        for (int i = 0; i < exportColumnAttributes.size(); i++) {
            this.exportAttr.add(exportColumnAttributes.get(i).getText());
            this.intermediateToExportMapping.put(exportColumnAttributes.get(i).getText(), i);
        }

        DriverManager.getDriver().switchTo().defaultContent(); // Added this line to verify exports test it may effect data mapping functionality


          return exportAttr;
    }

    public int getIndexValueForTheAttribute(String attributeName) {
        return intermediateToExportMapping.get(attributeName);

    }


    public boolean validateRuleBoxesAdded(String attributeName, String level, String ruleBoxesType)
    {
            DriverManager.getDriver().switchTo().frame(dataflowFrame);
            String locator = DynamicLocatorStrategy.getDynamicLocator(ruleBoxLines,attributeName,level);
            String boxes = DriverManager.getDriver().findElement(By.cssSelector(locator)).getAttribute("boxes");
            if(boxes.toLowerCase().contains(ruleBoxesType.toLowerCase()))
        {
            mouseHover(By.cssSelector(locator));
                   return true;
        }
            return false;
    }


}
