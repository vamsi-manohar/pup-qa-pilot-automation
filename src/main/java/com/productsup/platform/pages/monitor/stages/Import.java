package com.productsup.platform.pages.monitor.stages;

import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.enums.MonitorStages;
import com.productsup.platform.enums.WaitStrategy;
import com.productsup.platform.interfaces.Monitor;
import com.productsup.platform.pages.monitor.MonitorPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Map;

public class Import extends MonitorPage {


    public Import()
    {
        PageFactory.initElements(DriverManager.getDriver(),this);
    }



    @FindBy(css="pup-toggle div")
    private List<WebElement> selectDataSource;

    @FindBy(xpath="//span[text()='Continue']")
    private WebElement continueButton;

    public void setErrorEventsAtImportStage(String eventName)
    {
        switch(MonitorStages.valueOf(eventName))
        {
            case IMPORT_FAILED_FOR_DATA_SOURCE:
                addErrorEvents(MonitorStages.IMPORT_FAILED_FOR_DATA_SOURCE);
                click(selectDataSource.get(0), WaitStrategy.CLICKABLE);
                click(continueButton,WaitStrategy.CLICKABLE);
                break;

            case NO_CHANGE_IN_DATA_SOURCE:
                addErrorEvents(MonitorStages.NO_CHANGE_IN_DATA_SOURCE);
                click(continueButton,WaitStrategy.CLICKABLE);
                break;

        }
    }

}
