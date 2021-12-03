package com.productsup.platform.pages.monitor;

import com.google.common.util.concurrent.Uninterruptibles;
import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.enums.Monitors;
import com.productsup.platform.enums.WaitStrategy;
import com.productsup.platform.pages.BasePage;
import com.productsup.platform.pages.monitor.stages.Import;
import com.productsup.platform.pages.monitor.stages.Intermediate;
import com.productsup.platform.pages.site.SiteNavigations;
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

    @FindBy(css="div[class='trigger'] span")
    private List<WebElement>monitorActionsList;

    @FindBy(css="pup-toggle div div")
    private List<WebElement>actionToggles;

    @FindBy(css="pup-heading span")
    private WebElement pageTitle;



    private String monitorStage = "pup-card img[src*='%s']";
    private String severityLevel = "app-severity-label[class*='%s']";



    public MonitorPage() {

        PageFactory.initElements(DriverManager.getDriver(), this);
        this.wait = new WebDriverWait(DriverManager.getDriver(), 30);
    }


    private static EnumMap<Monitors, String> monitorStagesMapping = new EnumMap<Monitors, String>(Monitors.class);

    static {
        for (Monitors stage : Monitors.values()) {
            monitorStagesMapping.put(stage, stage.getData());
        }
    }

    private Map<String, String> errorEventsMapping = new HashMap<>();


    public String getMonitorStages(Monitors monitorStages) {
        return monitorStages.getData();
    }


    /**
     * Selecting the stages to add monitor events
     * @param monitorStages
     */
    public void selectStage(Monitors monitorStages) {
        DriverManager.getDriver().switchTo().frame(frame);
        click(addErrorEventBtn, WaitStrategy.CLICKABLE);
        click(By.cssSelector(DynamicLocatorStrategy.getDynamicLocator(monitorStage, getMonitorStages(monitorStages))), WaitStrategy.CLICKABLE);
        click(continueButton, WaitStrategy.CLICKABLE);
    }


    /**
     * Adding Error events from the available List
     * @param monitorStages
     */
    public void addErrorEvents(Monitors monitorStages) {
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


    /**
     * Adding monitor event actions
     * @param monitorStages
     */
    public void addMonitorEventActions(Monitors monitorStages) {
        for (int i = 0; i < monitorActionsList.size(); i++) {
            if (monitorActionsList.get(i).getText().equalsIgnoreCase(getMonitorStages(monitorStages))) {
                Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
                click(actionToggles.get(i), WaitStrategy.CLICKABLE);
                Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
                break;
            }
        }
    }

    /**
     * Setting the severity of the error event
     * @param monitorStages
     */
    public void setSeverity(Monitors monitorStages) {
        click(By.cssSelector(DynamicLocatorStrategy.getDynamicLocator(severityLevel, getMonitorStages(monitorStages))), WaitStrategy.CLICKABLE);

    }

    /**
     * Setting the action on the error event
     * @param monitorStages
     */
    public void setAction(Monitors monitorStages) {
        click(By.cssSelector(DynamicLocatorStrategy.getDynamicLocator(severityLevel, getMonitorStages(monitorStages))), WaitStrategy.CLICKABLE);
        click(addBtn, WaitStrategy.CLICKABLE);
    }


    /**
     * This method setups the monitor at the user defined level
     *
     * @param level - Import,Export,Intermediate,General
     */

    public void setMonitorLevel(String level) {
        switch (Monitors.valueOf(level)) {
            case IMPORT:
                selectStage(Monitors.IMPORT);
                break;

            case INTERMEDIATE:
                selectStage(Monitors.INTERMEDIATE);
                break;

            case EXPORT:
                selectStage(Monitors.EXPORT);
                break;

            case GENERAL:
                selectStage(Monitors.GENERAL);
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
        switch (Monitors.valueOf(severityLevel))
        {
            case LOW:
                setSeverity(Monitors.LOW);
                break;

            case MEDIUM:
                setSeverity(Monitors.MEDIUM);
                break;

            case HIGH:
                setSeverity(Monitors.HIGH);
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
    public boolean validateMonitorSetup(Monitors eventName)
    {
        click(addBtn, WaitStrategy.CLICKABLE);
        this.wait.until(d->!monitorsList.isEmpty());
       for(int i=0;i<monitorsList.size();i++)
       {
           scrollIntoView(addErrorEventBtn);
           if(monitorsList.get(i).getText().equalsIgnoreCase(getMonitorStages(eventName)))
           {
               DriverManager.getDriver().switchTo().defaultContent();
               return true;
           }
       }
        DriverManager.getDriver().switchTo().defaultContent();
       return false;
    }


    /***
     * Deleting the Monitor Events
     * @return
     */
    public boolean deleteMonitorEvents()
    {
        if(!pageTitle.getText().equalsIgnoreCase("Monitor"))
        {
            new SiteNavigations().navigateToMonitorPage();
            DriverManager.getDriver().switchTo().frame(frame);
        }

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
                DriverManager.getDriver().switchTo().defaultContent();
                return true;
            }


        }
        return false;
    }


    /**
     * Setting up actions to be performed for the monitor events
     * @param action
     */
    public void setErrorEventAction(String action)
    {
         switch (Monitors.valueOf(action))
         {
             case STOP_PROCESSING:
                 addMonitorEventActions(Monitors.STOP_PROCESSING);
                 break;
         }
    }

}
