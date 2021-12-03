package com.productsup.platform.pages.monitor.stages;

import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.enums.Monitors;
import com.productsup.platform.enums.WaitStrategy;
import com.productsup.platform.pages.monitor.MonitorPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Export extends MonitorPage
{

    private WebDriverWait wait;
    public Export()
    {
        PageFactory.initElements(DriverManager.getDriver(),this);
        this.wait = new WebDriverWait(DriverManager.getDriver(), 30);
    }

    @FindBy(css="input[type='number']")
    private WebElement thresholdValue;

    @FindBy(xpath="//span[text()='Continue']")
    private WebElement continueButton;

    public void setErrorEventsAtExportStage(String eventName)
    {
        switch(Monitors.valueOf(eventName))
        {
            case PERCENTAGE_OF_ADDED_ITEMS:
                addErrorEvents(Monitors.PERCENTAGE_OF_ADDED_ITEMS);
                click(continueButton, WaitStrategy.CLICKABLE);
                this.wait.until(d->thresholdValue.isDisplayed());
                thresholdValue.clear();
                sendKeys(thresholdValue,WaitStrategy.VISIBLE,"10");
                break;



        }
    }
}
