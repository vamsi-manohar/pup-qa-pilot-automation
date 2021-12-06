package com.productsup.platform.pages.site;

import com.google.common.util.concurrent.Uninterruptibles;
import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.enums.WaitStrategy;
import com.productsup.platform.pages.BasePage;
import com.productsup.platform.pages.site.errorlog.ErrorLogPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SiteDashboard extends BasePage {


    private String info = "app-%s-tile div[class*='content'] pup-heading";
    private String menu = "//*[text()='%s']";


    @FindBy(css = "app-import-tile div[class*='content'] pup-heading")
    private List<WebElement> importedData;

    @FindBy(css = "app-export-tile div[class*='content'] pup-heading")
    private WebElement exportedData;

    @FindBy(css = "iframe[id='frame']")
    private WebElement frame;

    @FindBy(css = "app-errors-tile div[class='item ng-star-inserted']")
    private List<WebElement> errorTileDetails;

    @FindBy(css = "pup-icon[path='line/ic-line-graph']")
    private WebElement errorLogLink;


    public SiteDashboard() {
        PageFactory.initElements(DriverManager.getDriver(), this);
        wait = new WebDriverWait(DriverManager.getDriver(), 30);

    }




    public int getImportedItemsCount() {
        DriverManager.getDriver().switchTo().frame(frame);
        int importedItems = 0;
        if (!importedData.isEmpty()) {
            this.wait.until(d -> !importedData.isEmpty());
            Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
            importedItems = Integer.parseInt(getText(importedData.get(0)).replace(",", ""));
        }
        DriverManager.getDriver().switchTo().defaultContent();
        return importedItems;
    }


    public int getErrorsCount() {
        DriverManager.getDriver().switchTo().frame(frame);
        int errorCount = 0;
        if (errorTileDetails.get(0).getText().contains("1")) {
            errorCount = 1;
        }
        return errorCount;

    }

    public ErrorLogPage navigateToErrorDetails() {

        this.wait.until(d -> errorLogLink.isDisplayed());
        click(errorLogLink, WaitStrategy.CLICKABLE);
        DriverManager.getDriver().switchTo().defaultContent();
        return new ErrorLogPage();
    }

}




