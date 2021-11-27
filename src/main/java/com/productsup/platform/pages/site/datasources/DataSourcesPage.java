package com.productsup.platform.pages.site.datasources;

import com.google.common.util.concurrent.Uninterruptibles;
import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.enums.DataSourceTypes;
import com.productsup.platform.enums.WaitStrategy;
import com.productsup.platform.factories.ExplicitWaitFactory;
import com.productsup.platform.pages.BasePage;
import com.productsup.platform.pages.site.SiteNavigations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.EnumMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DataSourcesPage extends BasePage {



    private WebDriverWait wait;
    private String info = "app-'%s'-tile div[class*='content'] pup-heading";
    private String menu = "//*[text()='%s']";

    By runStatusButton = By.cssSelector("pup-split-button[class='run-status-button split-button");

    @FindBy(xpath = "//*[text()='Add Data Source']")
    private WebElement addDataSource;

    @FindBy(id = "js-importName")
    public WebElement inputDataSourceName;

    @FindBy(css = "div[class='modal-footer'] a")
    private WebElement continueButton;

    @FindBy(css = "app-site-controls  button[type='button']")
    public List<WebElement> siteActionControlButtons;

    @FindBy(css = "#overview td[class*='emphasis']")
    private List<WebElement> availableDateSourcesList;

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
    private WebElement pauseDataSourceBtn;


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


    public String getDataSources(DataSourceTypes dataSources) {
        return dataSources.getData();
    }

    public void assignNameToDataSource(String name) {
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        inputDataSourceName.clear();
        sendKeys(inputDataSourceName, WaitStrategy.VISIBLE, name);

        //inputDataSourceName.sendKeys(name);
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        click(continueButton, WaitStrategy.CLICKABLE);
    }

    public void addDataSource() {
        click(addDataSource, WaitStrategy.CLICKABLE);
    }


    public void triggerSiteActionControls(DataSourceTypes dataSourceTypes) {

        String actionType = null;
        for (int i = 0; i < siteActionControlButtons.size(); i++) {
            actionType = siteActionControlButtons.get(i).getText();
            if (getDataSources(dataSourceTypes).equals(actionType))
            {
                click( siteActionControlButtons.get(i),WaitStrategy.CLICKABLE);
                //siteActionControlButtons.get(i).click();
                Uninterruptibles.sleepUninterruptibly(10,TimeUnit.SECONDS);
                //if (ExplicitWaitFactory.performExplicitWaitChecks(WaitStrategy.DISAPPEAR, runStatusButton)) {
                    break;
                //}

            }

        }
        Uninterruptibles.sleepUninterruptibly(5,TimeUnit.SECONDS);
    }

    public void triggerActionType(DataSourceTypes dataSourceTypes) {
        switch (dataSourceTypes) {
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


    public int getImportedItemsCount()
    {
        int count = new SiteNavigations().navigateToDashboardPage().getImportedItemsCount();
        return count;
    }

    /**
     *  Removes data source based on data source name and performs import action
     * @return
     */
    public  int removeDataSourceAndImport()
    {
        new SiteNavigations().navigateToDataSourcesPage().
        removeDataSource("Main - Feed URL")
                .triggerActionType(DataSourceTypes.IMPORT);
        DriverManager.getDriver().navigate().refresh();
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        DriverManager.getDriver().navigate().refresh();
        Assert.assertTrue(checkIfDataSourceIsRemoved("Main - Feed URL"));
        int count = getImportedItemsCount();
        return count;
    }


    /**
     *  Pauses the data source
     * @param dataSourceName
     */

    public void pauseDataSource(String dataSourceName)
    {
        for(int i=0;i<availableDateSourcesList.size();i++)
        {
            if(availableDateSourcesList.get(i).getText().equalsIgnoreCase(dataSourceName))
            {
                click(pauseDataSourceBtn,WaitStrategy.CLICKABLE);
                break;
            }
        }
    }

}
