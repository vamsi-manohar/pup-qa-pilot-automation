package com.productsup.platform.pages.account;

import com.google.common.util.concurrent.Uninterruptibles;
import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.enums.Navigations;
import com.productsup.platform.enums.WaitStrategy;
import com.productsup.platform.interfaces.PlatformNavigation;
import com.productsup.platform.pages.BasePage;
import com.productsup.platform.pages.project.ProjectOverview;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AccountOverview extends BasePage {


    @FindBy(css = "#js-projectList td a")
    private List<WebElement> projectList;



    public AccountOverview() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public ProjectOverview selectProject(String projectName) {
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        for (int i = 0; i < projectList.size(); i++) {
            if (projectList.get(i).getText().contains(projectName)) {
                click(projectList.get(i), WaitStrategy.CLICKABLE);

            }

        }

        return new ProjectOverview();

    }



}

