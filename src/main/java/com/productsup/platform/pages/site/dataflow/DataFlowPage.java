package com.productsup.platform.pages.site.dataflow;

import com.google.common.util.concurrent.Uninterruptibles;
import com.productsup.platform.driver.Driver;
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

    public  final String INTERMEDIATE = "intermediate";
    public  final String EXPORT = "export";
    public  final String INPUT_MODE = "input";
    public  final String OUTPUT_MODE = "output";

    private String addAttributelevel="li[class*='add-%s-column']";
    private String attributeInfo="input[class*='add-%s-column']";
    private String saveAttribute="button[class*='add-%s-column']";
    private String ruleBoxLines="g[column='%replace1%'][mode='%replace2%'] line[boxes]";
    private String circle = "g[column='%replace1%][mode='%replace2%'] circle[class='step']";
    private String intermediateAttributeInfo="div[class='connection-output output-intermediate'][name='%s']";
    private String exportAttributeInfo="div[class='connection-input input-target'][name='%s']";
    //private String clickedIntermedaiteAttr="div[class='connection-output output-intermediate click-from'][name='%s']";

    private String exportAttributeEnabled="div[class='connection-input input-target enable-input-click'][name='%s']";

    private String line = "g[column='%replace1%'][mode='%replace2%']";
    private String mappedAttributeLine="g[column='%s'] line[id*='connection']";
    private String deleteConnection="button[class*='danger'][column='%replace1%'][mode='%replace2%']";
    private String attributeTile="a[href*='%replace1%'][title='%replace2%']";

    @FindBy(xpath = "//button[text()='OK']")
            private List<WebElement> okBtns;


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

    @FindBy(css="li[class='add-export-column js-add-export-column']")
    private WebElement dragAtExport;

    @FindBy(xpath="//button[text()='Yes']")
    private WebElement confirmDeleteLinks;

    @FindBy(css="div[class*='ajs-success']")
    private WebElement linkRemovedSuccessMsg;

    @FindBy(css="li[class*='custom highlight'] button[class*='delete-custom']")
    private WebElement deleteAttributeBtn;

    @FindBy(id="delete-line")
    private WebElement deleteLine;

    @FindBy(css="div[class='alertify  ajs-movable ajs-closable ajs-pinnable ajs-pulse'] button[class*='ok']")
    private WebElement removeConnectionBtn;



    public DataFlowPage()
    {
        PageFactory.initElements(DriverManager.getDriver(),this);
        this.wait = new WebDriverWait(DriverManager.getDriver(), 30);

    }


    /**
     * Expanding the export channels list
     */
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


    public void linkToExportByDragAndDrop(String attribute)
    {
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
         click(By.cssSelector(DynamicLocatorStrategy.
                 getDynamicLocator(intermediateAttributeInfo,attribute)),WaitStrategy.CLICKABLE);
        click(By.cssSelector(DynamicLocatorStrategy.
                getDynamicLocator(exportAttributeEnabled,attribute)),WaitStrategy.CLICKABLE);
        Uninterruptibles.sleepUninterruptibly(5,TimeUnit.SECONDS);

    }


    public DataFlowPage deleteLinks(String attribute, String mode)
    {
        Uninterruptibles.sleepUninterruptibly(5,TimeUnit.SECONDS);
        String locator = DynamicLocatorStrategy.getDynamicLocator(line,attribute,mode);
        //clickUsingJSExecutor(By.cssSelector(locator));
        click(By.cssSelector(locator),WaitStrategy.CLICKABLE);
        this.wait.until(d->deleteLine.isDisplayed());
        click(deleteLine,WaitStrategy.CLICKABLE);
        confirmDeleteConnection();
        DriverManager.getDriver().switchTo().frame(dataflowFrame);
        return this;

    }
    public DataFlowPage deleteAttributes(String attribute, String mode)
    {
         mouseHover(By.cssSelector(DynamicLocatorStrategy.getDynamicLocator(attributeTile,mode,attribute)));
         this.wait.until(d->deleteAttributeBtn.isDisplayed());
         click(deleteAttributeBtn,WaitStrategy.CLICKABLE);
         DriverManager.getDriver().switchTo().defaultContent();
         this.wait.until(d->removeConnectionBtn.isDisplayed());
         click(removeConnectionBtn,WaitStrategy.CLICKABLE);
         DriverManager.getDriver().switchTo().frame(dataflowFrame);
         return this;
    }

    /**
     *   Adds Additional Attributes at Intermediate and Export Levels
     * @param platformLevel
     * @param info
     * @return
     */
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


    public void confirmDeleteConnection()
    {
        DriverManager.getDriver().switchTo().defaultContent();
        this.wait.until(d->removeConnectionBtn.isDisplayed());
        click(removeConnectionBtn,WaitStrategy.CLICKABLE);
        this.wait.until(d->linkRemovedSuccessMsg.isDisplayed());
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
