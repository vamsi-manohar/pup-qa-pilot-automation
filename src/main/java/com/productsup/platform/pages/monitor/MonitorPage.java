package com.productsup.platform.pages.monitor;

import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.Uninterruptibles;
import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.enums.MonitorStages;
import com.productsup.platform.enums.WaitStrategy;
import com.productsup.platform.pages.BasePage;
import com.productsup.platform.pages.monitor.stages.Import;
import com.productsup.platform.pages.monitor.stages.Intermediate;
import com.productsup.platform.utils.DynamicLocatorStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MonitorPage extends BasePage {


    private WebDriverWait wait;

    @FindBy(xpath = "//span[text()='Add Error Event']")
    private WebElement addErrorEventBtn;

    @FindBy(xpath = "//span[text()='Continue']")
    private WebElement continueButton;

    @FindBy(xpath = "//span[text()='Add']")
    private WebElement addBtn;

    @FindBy(css = "pup-card pup-heading[class*='4']")
    private List<WebElement> errorEventsList;

    @FindBy(css="iframe[id='frame']")
    private WebElement frame;

    @FindBy(css="pup-paragraph[class*='name']")
    private List<WebElement>monitorsList;

    @FindBy(css="pup-icon[path*='dots'] svg")
    private List<WebElement>monitorSettingsBtn;

    @FindBy(css="pup-dropdown-menu-item[class*='dangerous']")
    private List<WebElement> deleteEvents;

    @FindBy(css="pup-button[class*='danger'] button")
    private WebElement confirmDeleteEventBtn;

    private String monitorStage = "pup-card img[src*='%s']";
    private String severityLevel = "app-severity-label[class*='%s']";


    public MonitorPage() {

        PageFactory.initElements(DriverManager.getDriver(), this);
        this.wait = new WebDriverWait(DriverManager.getDriver(), 30);
    }


    private static EnumMap<MonitorStages, String> monitorStagesMapping = new EnumMap<MonitorStages, String>(MonitorStages.class);

    static {
        for (MonitorStages stage : MonitorStages.values()) {
            monitorStagesMapping.put(stage, stage.getData());
        }
    }

    private Map<String, String> errorEventsMapping = new HashMap<>();


    public String getMonitorStages(MonitorStages monitorStages) {
        return monitorStages.getData();
    }


    public void selectStage(MonitorStages monitorStages) {
        DriverManager.getDriver().switchTo().frame(frame);
        click(addErrorEventBtn, WaitStrategy.CLICKABLE);
        click(By.cssSelector(DynamicLocatorStrategy.getDynamicLocator(monitorStage, getMonitorStages(monitorStages))), WaitStrategy.CLICKABLE);
        click(continueButton, WaitStrategy.CLICKABLE);
    }

    public void addErrorEvents(MonitorStages monitorStages) {
        this.wait.until(d->!errorEventsList.isEmpty());
        for (int i = 0; i < errorEventsList.size(); i++) {
            if (errorEventsList.get(i).getText().equalsIgnoreCase(getMonitorStages(monitorStages))) {
                System.out.println("Error Events are :: " +errorEventsList.get(i).getText());
                Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
                click(errorEventsList.get(i), WaitStrategy.CLICKABLE);
                Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
                break;
            }
        }
    }

    public void setSeverity(MonitorStages monitorStages) {
        click(By.cssSelector(DynamicLocatorStrategy.getDynamicLocator(severityLevel, getMonitorStages(monitorStages))), WaitStrategy.CLICKABLE);
        click(addBtn, WaitStrategy.CLICKABLE);
    }


    /**
     * This method setups the monitor at the user defined level
     *
     * @param level - Import,Export,Intermediate,General
     */

    public void setMonitorLevel(String level) {
        switch (MonitorStages.valueOf(level)) {
            case IMPORT:
                selectStage(MonitorStages.IMPORT);
                break;

            case INTERMEDIATE:
                selectStage(MonitorStages.INTERMEDIATE);
                break;

            case EXPORT:
                selectStage(MonitorStages.EXPORT);
                break;

            case GENERAL:
                selectStage(MonitorStages.GENERAL);
                break;

        }
    }

    /**
     * Sets up the severity level for the error events
     *
     * @param severityLevel
     */
    public void setSeverityLevel(String severityLevel)

    {
        switch (MonitorStages.valueOf(severityLevel))
        {
            case LOW:
                setSeverity(MonitorStages.LOW);
                break;

            case MEDIUM:
                setSeverity(MonitorStages.MEDIUM);
                break;

            case HIGH:
                setSeverity(MonitorStages.HIGH);
                break;


        }
    }

    /***
     *  Adds Error Events in the platform
     * @param data
     */
    public void setErrorEvents(Map<String,String>data)
    {
        String stage = data.get("Monitor_At");
        if(stage.equalsIgnoreCase("IMPORT"))
        {
            new Import().setErrorEventsAtImportStage(data.get("Error_Event"));
        }
        else if(stage.equalsIgnoreCase("INTERMEDIATE"))
        {
            new Intermediate().setErrorEventsAtIntermediateStage(data.get("Error_Event"));
        }
    }


    /**
     * Validating if Error events are added on the platform
     * @param eventName
     * @return
     */
    public boolean validateMonitorSetup(MonitorStages eventName)
    {
        this.wait.until(d->!monitorsList.isEmpty());
       for(int i=0;i<monitorsList.size();i++)
       {
           scrollIntoView(addErrorEventBtn);
           if(monitorsList.get(i).getText().equalsIgnoreCase(getMonitorStages(eventName)))
           {
               return true;
           }
       }
       return false;
    }


    public boolean deleteMonitorEvents()
    {
        for(int i=0;i<monitorSettingsBtn.size();i++)
        {
            scrollIntoView(monitorSettingsBtn.get(i));
            click(monitorSettingsBtn.get(i),WaitStrategy.CLICKABLE);
            this.wait.until(d->!deleteEvents.isEmpty());
            click(deleteEvents.get(i),WaitStrategy.CLICKABLE);
            scrollIntoView(confirmDeleteEventBtn);
            this.wait.until(d->confirmDeleteEventBtn.isDisplayed());
            click(confirmDeleteEventBtn,WaitStrategy.CLICKABLE);
            Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);

            if(monitorsList.isEmpty())
            {
                System.out.println("Event is Deleted !!!");
                return true;
            }

        }
        return false;
    }


}
