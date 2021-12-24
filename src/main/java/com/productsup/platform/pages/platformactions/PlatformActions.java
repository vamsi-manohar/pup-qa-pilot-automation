package com.productsup.platform.pages.platformactions;

import com.google.common.util.concurrent.Uninterruptibles;
import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.enums.DataSourceTypes;
import com.productsup.platform.enums.WaitStrategy;
import com.productsup.platform.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class PlatformActions extends BasePage {

    private WebDriverWait wait;

    @FindBy(css = "app-site-controls  button[type='button']")
    private List<WebElement> siteActionControlButtons;

    public PlatformActions()
    {
        PageFactory.initElements(DriverManager.getDriver(), this);
        this.wait = new WebDriverWait(DriverManager.getDriver(), 10);
    }


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
     *  Triggers the platform action on the data source
     * @param dataSourceTypes
     */
    private void triggerSiteActionControls(DataSourceTypes dataSourceTypes) {

        System.out.println("Get DATA:: " + dataSourceTypes.getData());
        String actionType = null;
        for (int i = 0; i < siteActionControlButtons.size(); i++) {
            actionType = siteActionControlButtons.get(i).getText();
            if (dataSourceTypes.getData().equalsIgnoreCase(actionType))
            {
                click( siteActionControlButtons.get(i), WaitStrategy.CLICKABLE);
                Uninterruptibles.sleepUninterruptibly(10, TimeUnit.SECONDS);
                break;
            }
        }
        Uninterruptibles.sleepUninterruptibly(5,TimeUnit.SECONDS);
    }
}
