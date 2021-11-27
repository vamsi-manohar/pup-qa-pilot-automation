package com.productsup.platform.pages.site;

import com.google.common.util.concurrent.Uninterruptibles;
import com.productsup.platform.driver.Driver;
import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.pages.BasePage;
import com.productsup.platform.utils.DynamicLocatorStrategy;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class SiteDashboard extends BasePage {


    private String info="app-%s-tile div[class*='content'] pup-heading";
    private String menu="//*[text()='%s']";



    @FindBy(css="app-import-tile div[class*='content'] pup-heading")
    private List<WebElement> importedData;

    @FindBy(css="app-export-tile div[class*='content'] pup-heading")
    private WebElement exportedData;

    @FindBy(css="iframe[id='frame']")
    private WebElement frame;



    public SiteDashboard()
    {
        PageFactory.initElements(DriverManager.getDriver(),this);
        wait = new WebDriverWait(DriverManager.getDriver(),30);
    }

//    By frame = By.cssSelector("iframe[id='frame']");


    public int getImportedItemsCount() {
            int importedItems = 0;
            DriverManager.getDriver().switchTo().frame(frame);
            if(!importedData.isEmpty())
            {
                this.wait.until(d -> !importedData.isEmpty());
                Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
                importedItems = Integer.parseInt(getText(importedData.get(0)).replace(",", ""));
            }
                DriverManager.getDriver().switchTo().defaultContent();
                return importedItems;
            }

    }


/*    public int getExportedItems()
    {
       // int exportedItems =Integer.parseInt(getText(By.cssSelector(DynamicLocatorStrategy.getDynamicLocator(info,"export"))).replace(",",""));
       // return exportedItems;
    }*/




