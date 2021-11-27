package com.productsup.platform.pages.project;

import com.google.common.util.concurrent.Uninterruptibles;
import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.enums.WaitStrategy;
import com.productsup.platform.pages.BasePage;
import com.productsup.platform.pages.site.SiteNavigations;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProjectOverview extends BasePage {

    @FindBy(css = "#js-projectList td[class='emphasis'] a")
    private List<WebElement> sitesList;

    public ProjectOverview() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }


    public SiteNavigations selectSite(String siteName) {
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        for (int i = 0; i < sitesList.size(); i++) {
            if (sitesList.get(i).getText().contains(siteName)) {

                click(sitesList.get(i), WaitStrategy.CLICKABLE);


            }

        }

        return new SiteNavigations();

    }


}
