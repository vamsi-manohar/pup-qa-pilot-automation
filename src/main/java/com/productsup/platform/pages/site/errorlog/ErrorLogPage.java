package com.productsup.platform.pages.site.errorlog;

import com.google.common.util.concurrent.Uninterruptibles;
import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.enums.WaitStrategy;
import com.productsup.platform.pages.BasePage;
import com.productsup.platform.pages.site.SiteNavigations;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ErrorLogPage extends BasePage
{


    private WebDriverWait wait;
    private List<String> errorDetails=new ArrayList<>();

    @FindBy(css="pup-heading span")
    private WebElement pageTitle;

    @FindBy(css="a[class*='bread'][href*='error']")
    private WebElement errorLogLink;

    @FindBy(css="#js-errorList p[class*='message']")
    private List<WebElement>errorInfo;

    @FindBy(css="tr[ng-repeat] button")
    private List<WebElement>deleteErorLogs;

    @FindBy(css="div[class*='alertify  ajs-movable ajs-closable ajs-pinnable ajs-pulse'] button[class*='ok']")
    private WebElement confirmDeleteBtn;




    public ErrorLogPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
        this.wait = new WebDriverWait(DriverManager.getDriver(), 10);

    }


    public ErrorLogPage navigateBackToErrorLog()
    {
        if(!pageTitle.getText().contains("Error"))
        {
            new SiteNavigations().navigateToErrorLogPage();
        }
      this.wait.until(d->errorLogLink.isDisplayed());
      click(errorLogLink, WaitStrategy.CLICKABLE);
      return this;
    }

    public List<String> getErrorDetail()
    {
        for(int i=0;i<errorInfo.size();i++) {
            errorDetails.add(errorInfo.get(i).getText());
        }
        return errorDetails;
    }

    public void deleteErrorLogs()
    {
        for(int i=0;i<deleteErorLogs.size();i++)
        {
            click(deleteErorLogs.get(i),WaitStrategy.CLICKABLE);
            this.wait.until(d->confirmDeleteBtn.isDisplayed());
            click(confirmDeleteBtn,WaitStrategy.CLICKABLE);

        }
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
    }



}
