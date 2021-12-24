package com.productsup.platform.pages.site.dataexports;

import com.google.common.util.concurrent.Uninterruptibles;
import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.enums.ExportDestinations;
import com.productsup.platform.enums.WaitStrategy;
import com.productsup.platform.pages.BasePage;
import com.productsup.platform.pages.site.SiteNavigations;
import com.productsup.platform.utils.DynamicLocatorStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.EnumMap;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class DataExportsPage extends BasePage
{

    private WebDriverWait wait;


    private String buttons = "//*[text()='%s']";

    @FindBy(css="#browse input[placeholder*='Export']")
    private WebElement searchExportChannel;

    @FindBy(css="i[ng-click*='browse']")
    private WebElement searchIcon;

    @FindBy(css="div[ng-repeat*='enabled'] button")
    private List<WebElement> addBtns;

    @FindBy(css="div[ng-repeat*='enabled'] button")
    private WebElement addButton;

    @FindBy(id="name")
    private WebElement inputExportTemplateName;

    @FindBy(css="div[class='modal-footer'] button")
    private WebElement continueButton;

    @FindBy(css="button[data-target*='destination']")
    private WebElement addDestinatonBtn;

    @FindBy(id="js-channelDestination")
    private WebElement selectDestination;

    @FindBy(id="js-addDestination")
    private WebElement saveDestination;

    @FindBy(css="a[href*='export']")
    private WebElement exportsPageMainLink;

    @FindBy(css="span[class*='channel-header']")
    private List<WebElement> exportChannelsList;

    @FindBy(css="a[uib-tooltip='Setup']")
    private List<WebElement> exportChannelSetupBtn;

    @FindBy(css="button[class*='remove']")
    private WebElement deleteExportBtn;

    @FindBy(xpath = "//button[text()='Yes']")
    private WebElement confirmDeleteExportBtn;

    @FindBy(css="pup-breadcrumbs[class*='bread']")
    private WebElement banner;

    @FindBy(css="#dataflowFrame")
    private WebElement dataflowFrame;




    public DataExportsPage()
    {
        PageFactory.initElements(DriverManager.getDriver(),this);
        this.wait = new WebDriverWait(DriverManager.getDriver(), 30);
    }


    private static EnumMap<ExportDestinations, String> exportChannelsMapping = new EnumMap<ExportDestinations, String>(ExportDestinations.class);

    static {
        for (ExportDestinations exportChannel : ExportDestinations.values()) {
            exportChannelsMapping.put(exportChannel, exportChannel.getData());
        }
    }


    public String getExportChannels(ExportDestinations exportChannels) {
        return exportChannels.getData();
    }

    public void addExports()
    {
        click(By.xpath(DynamicLocatorStrategy.getDynamicLocator(buttons,"Add Export")), WaitStrategy.CLICKABLE);
    }

    public DataExportsPage addExportChannelTemplate()
    {



            Uninterruptibles.sleepUninterruptibly(5,TimeUnit.SECONDS);
            this.wait.until((d)->addBtns.get(0).isDisplayed());
            click(addBtns.get(0),WaitStrategy.CLICKABLE);
            return this;


    }

    public void assignNameToExportsTemplate(String name) {
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        inputExportTemplateName.clear();
        sendKeys(inputExportTemplateName, WaitStrategy.VISIBLE, name);

        //inputDataSourceName.sendKeys(name);
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        click(continueButton, WaitStrategy.CLICKABLE);
    }

    /**
     * Seearching and adding the export channel template
     * @param
     * @param name
     */
/*    public void selectExportChannel(ExportChannels exportChannels,String name)
    {
      sendKeys(searchExportChannel,WaitStrategy.VISIBLE,getExportChannels(exportChannels));
      click(searchIcon,WaitStrategy.CLICKABLE);
      addExportChannelTemplate();
      assignNameToExportsTemplate(name);
    }*/


    public void selectExportChannel(String name)
    {
        addExports();
        sendKeys(searchExportChannel,WaitStrategy.VISIBLE,name);
        click(searchIcon,WaitStrategy.CLICKABLE);
        addExportChannelTemplate();
        assignNameToExportsTemplate(name);
    }


    /**
     *  Adding destination details
     * @param exportChannels
     */
    public void addDestinationDetails(ExportDestinations exportChannels)
    {
        this.wait.until(driver->addDestinatonBtn.isDisplayed());
       click(addDestinatonBtn,WaitStrategy.CLICKABLE);
       this.wait.until(driver->selectDestination.isDisplayed());
       Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
       selectValueFromDropdown(selectDestination,getExportChannels(exportChannels));
       this.wait.until(d->saveDestination.isDisplayed());
       click(saveDestination,WaitStrategy.CLICKABLE);
       Uninterruptibles.sleepUninterruptibly(5,TimeUnit.SECONDS);

    }


    public void selectExportDestinationType(String destinationType)
    {
        switch(ExportDestinations.valueOf(destinationType))
        {
            case EXPORT_DATASOURCE:
                addDestinationDetails(ExportDestinations.EXPORT_DATASOURCE);
                break;
        }
    }


    public DataExportsPage navigateBackToMainExportsPage()
    {
        this.wait.until(d->exportsPageMainLink.isDisplayed());
        click(exportsPageMainLink,WaitStrategy.CLICKABLE);
        return this;
    }


    /**
     * This method verifies if a new export channel is added or not
     * returns true - if added else returns false
     * @param
     * @return
     */
/*    public boolean verifyExportChannelIsAdded(ExportChannels exportChannels)
    {
        for(int i=0;i<exportChannelsList.size();i++)
        {
            if(exportChannelsList.get(i).getText().contains(getExportChannels(exportChannels)))
            {
                return true;
            }
        }

        return false;
    }*/


    public boolean verifyExportChannelIsAdded(String exportChannelName)
    {
        for(int i=0;i<exportChannelsList.size();i++)
        {
            if(exportChannelsList.get(i).getText().contains(exportChannelName))
            {
                return true;
            }
        }

        return false;
    }

    public List<String> verifyAttributesOfExportChannelTemplate(String channelName)
    {
        return new SiteNavigations().navigateToDataFlowPage()
                .selectExportChannel(channelName)
                .getAllExportAttributes();

    }

    public boolean verifyExportTemplateInDataView(String channelName)
    {
       return  new SiteNavigations().navigateToDataViewPage()
                .getExportChannelList().contains(channelName);


    }


    /**
     *  Selecting destination type
     * @param destination
     */
    public void selectDestinationDetails(String destination)
    {
        switch(ExportDestinations.valueOf(destination))
        {
            case EXCEL_MACRO:
                addDestinationDetails(ExportDestinations.EXCEL_MACRO);
                break;

            case EXPORT_DATASOURCE:
                addDestinationDetails(ExportDestinations.EXPORT_DATASOURCE);
                break;
        }
    }


    public void deleteExportsTemplate(String exportChannelName)
    {
        DriverManager.getDriver().switchTo().defaultContent();
        new SiteNavigations().navigateToDataExportsPage();
        int totalExportsAvailable = exportChannelsList.size();
        for(int i=0;i<exportChannelsList.size();i++)
        {
            if(exportChannelsList.get(i).getText().equalsIgnoreCase(exportChannelName))
            {
                click(exportChannelSetupBtn.get(i),WaitStrategy.CLICKABLE);
                this.wait.until(d->deleteExportBtn.isDisplayed());
                click(deleteExportBtn,WaitStrategy.CLICKABLE);
                this.wait.until(d->confirmDeleteExportBtn.isDisplayed());
                click(confirmDeleteExportBtn,WaitStrategy.CLICKABLE);
                this.wait.until(d->exportChannelsList.size()==0 ||
                        exportChannelsList.size() == totalExportsAvailable-1);

            }
        }

    }


    public boolean isOnDataExportsPage()
    {
        scrollIntoView(banner);
        return banner.getText().contains("Exports");
    }



}
