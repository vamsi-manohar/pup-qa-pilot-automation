package com.productsup.platform.pages.site.datasources.types;

import com.productsup.platform.driver.DriverManager;

import com.productsup.platform.enums.DataSourceTypes;
import com.productsup.platform.enums.WaitStrategy;
import com.productsup.platform.interfaces.DataSource;

import com.productsup.platform.pages.site.datasources.DataSourcesPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FeedURL extends DataSourcesPage implements DataSource
{


    @FindBy(css="#new-import-form input")
    private WebElement searchDataSource;

    @FindBy(css="div[class='import-box ng-scope'] button")
    private List<WebElement> addDataSourceBtns;

    @FindBy(id="js-feedUrl")
    public WebElement feedUrl;

    @FindBy(id="import_username")
    public WebElement feedUsername;

    @FindBy(css="input[type='password']")
    public WebElement feedPassword;

    @FindBy(id="js-addImport")
    public WebElement saveDataSource;

    @FindBy(id="js-saveAllSettings")
    public WebElement saveAllSettings;


    public  FeedURL()
    {
        PageFactory.initElements(DriverManager.getDriver(),this);
    }


    @Override
    public void selectDataSource()
    {

        addDataSource();
        sendKeys(searchDataSource,WaitStrategy.VISIBLE,getDataSources(DataSourceTypes.FEED_URL));
        click(addDataSourceBtns.get(0),WaitStrategy.CLICKABLE);
        assignNameToDataSource("Main - Feed URL");

    }

    @Override
    public void enterDetails() {

        sendKeys(feedUrl,WaitStrategy.VISIBLE, "ftp://transport.productsup.io/productfeed.csv");
        sendKeys(feedUsername,WaitStrategy.VISIBLE,"feedfighter");
        sendKeys(feedPassword,WaitStrategy.VISIBLE,"NeverNotLearning");
        click(saveDataSource,WaitStrategy.CLICKABLE);
        click(saveAllSettings,WaitStrategy.CLICKABLE);
    }



    @Override
    public void triggerAction() {
          triggerActionType(DataSourceTypes.IMPORT);
    }


}
