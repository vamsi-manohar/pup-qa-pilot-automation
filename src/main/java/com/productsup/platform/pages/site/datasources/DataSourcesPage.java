package com.productsup.platform.pages.site.datasources;

import com.google.common.util.concurrent.Uninterruptibles;
import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.enums.DataSourceTypes;
import com.productsup.platform.enums.WaitStrategy;
import com.productsup.platform.pages.BasePage;
import com.productsup.platform.pages.site.SiteNavigations;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.EnumMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DataSourcesPage extends BasePage {



    private final WebDriverWait wait;

    @FindBy(xpath = "//*[text()='Add Data Source']")
    private WebElement addDataSource;

    @FindBy(id = "js-importName")
    public WebElement inputDataSourceName;

    @FindBy(css = "div[class='modal-footer'] a")
    private WebElement continueButton;

    @FindBy(css = "app-site-controls  button[type='button']")
    private List<WebElement> siteActionControlButtons;

    @FindBy(css = "#overview td[class*='emphasis']")
    private  List<WebElement> availableDateSourcesList;

    @FindBy(css = "#overview a[class*='btn']")
    private List<WebElement> settingsIcon;

    @FindBy(xpath = "//span[text()='Advanced Settings']")
    private WebElement advancedSettingsTab;

    @FindBy(css = "a[href*='delete']")
    private WebElement deleteImport;

    @FindBy(xpath = "//button[text()='Delete']")
    private WebElement confirmDeleteImport;

    @FindBy(xpath = "//button[text()='Yes']")
    private WebElement confirmModalBox;

    @FindBy(css="button[data-status='1']")
    private List<WebElement> pauseDataSourceBtn;

    @FindBy(css="button[data-status='0']")
    private List<WebElement> activateDataSources;


    public DataSourcesPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
        this.wait = new WebDriverWait(DriverManager.getDriver(), 10);
    }

    private static EnumMap<DataSourceTypes, String> dataSourcesMapping = new EnumMap<DataSourceTypes, String>(DataSourceTypes.class);

    static {
        for (DataSourceTypes dataSources : DataSourceTypes.values()) {
            dataSourcesMapping.put(dataSources, dataSources.getData());
        }
    }


    /**
     * Stores all the enum data
     * @param dataSources
     * @return
     */
    public String getDataSources(DataSourceTypes dataSources) {
        return dataSources.getData();
    }


    /**
     * Assigning name to the newly added Data Source
     * @param name
     */
    public void assignNameToDataSource(String name) {
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        inputDataSourceName.clear();
        sendKeys(inputDataSourceName, WaitStrategy.VISIBLE, name);
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        click(continueButton, WaitStrategy.CLICKABLE);
    }

    public void addDataSource() {
        click(addDataSource, WaitStrategy.CLICKABLE);
    }


    /**
     *  Triggers the platform action on the data source
     * @param dataSourceTypes
     */
    private void triggerSiteActionControls(DataSourceTypes dataSourceTypes) {

        String actionType = null;
        for (int i = 0; i < siteActionControlButtons.size(); i++) {
            actionType = siteActionControlButtons.get(i).getText();
            if (getDataSources(dataSourceTypes).equalsIgnoreCase(actionType))
            {
                click( siteActionControlButtons.get(i),WaitStrategy.CLICKABLE);
                Uninterruptibles.sleepUninterruptibly(10,TimeUnit.SECONDS);
                    break;
            }
        }
        Uninterruptibles.sleepUninterruptibly(5,TimeUnit.SECONDS);
    }


    /**
     * Sets the platform actions on the Data Source
     * @param actionType - RUN,IMPORT and EXPORT
     */
    public void triggerAction(String actionType)
    {
        switch(DataSourceTypes.valueOf(actionType))
        {
            case IMPORT:
                triggerSiteActionControls(DataSourceTypes.IMPORT);
                break;

            case EXPORT:

                triggerSiteActionControls(DataSourceTypes.EXPORT);
                break;

            case RUN:
                triggerSiteActionControls(DataSourceTypes.RUN);
                break;

        }

    }

    /**
     * Removes the data source
     * @param dataSourceName
     * @return
     */
    public DataSourcesPage removeDataSource(String dataSourceName) {
        this.wait.until(d->!availableDateSourcesList.isEmpty());
        for (int i = 0; i < availableDateSourcesList.size(); i++)
        {
            if (availableDateSourcesList.get(i).getText().contains(dataSourceName)) {
                click(settingsIcon.get(i), WaitStrategy.CLICKABLE);
                break;
            }
        }
        click(advancedSettingsTab,WaitStrategy.CLICKABLE);
        click(deleteImport,WaitStrategy.CLICKABLE);
        click(confirmDeleteImport,WaitStrategy.CLICKABLE);
        click(confirmModalBox,WaitStrategy.CLICKABLE);
        return this;
    }


    /**
     * Checks if Data source is successfully added
     * @param dataSourceName
     * @return
     */
    public boolean checkIfDataSourceIsRemoved(String dataSourceName) {

        if(availableDateSourcesList.size()==0)
        {
            return true;
        }

        else {
            for (int i = 0; i < availableDateSourcesList.size(); i++) {
                System.out.println(availableDateSourcesList.get(i).getText());
                if (!availableDateSourcesList.get(i).getText().contains(dataSourceName)) {
                    return true;

                }
            }
        }
        return false;
    }


    /**
     * Gets the imported data count
     * @return
     */
    public int getImportedItemsCount()
    {
        int count = new SiteNavigations().navigateToDashboardPage().getImportedItemsCount();
        return count;
    }

    /**
     *  Removes data source based on data source name and performs import action
     * @return
     */
    public  int removeDataSourceAndImport(String action)
    {

            new SiteNavigations().navigateToDataSourcesPage().
                    removeDataSource("Main - Feed URL")
                    .triggerAction(action);
            DriverManager.getDriver().navigate().refresh();
            Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
            DriverManager.getDriver().navigate().refresh();
            Assert.assertTrue(checkIfDataSourceIsRemoved("Main - Feed URL"));
            int count = getImportedItemsCount();
            return count;

    }


    /**
     * Performs actions on the selected data source
     * @param dataSourceName
     * @param dataSourceTypes - Allowed Actions are PAUSE and ACTIVATE
     * @return
     */
    public DataSourcesPage performActionOnDataSource(String dataSourceName,DataSourceTypes dataSourceTypes)
    {
        for(int i=0;i<availableDateSourcesList.size();i++)
        {
            System.out.println("Available Data Sources names :: " +availableDateSourcesList.get(i).getText());
            if(availableDateSourcesList.get(i).getText().equalsIgnoreCase(dataSourceName)
            && getDataSources(dataSourceTypes).equalsIgnoreCase("pause"))
            {

                click(pauseDataSourceBtn.get(i),WaitStrategy.CLICKABLE);
            }
            else if(availableDateSourcesList.get(i).getText().equalsIgnoreCase(dataSourceName)
                    && getDataSources(dataSourceTypes).equalsIgnoreCase("activate"))
            {
                click(activateDataSources.get(i),WaitStrategy.CLICKABLE);
            }
            break;
        }
        return this;
    }
}
