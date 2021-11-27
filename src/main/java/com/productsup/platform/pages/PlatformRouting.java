package com.productsup.platform.pages;

import com.productsup.platform.pages.account.AccountOverview;
import com.productsup.platform.pages.login.LoginPage;
import com.productsup.platform.pages.project.ProjectOverview;
import com.productsup.platform.pages.site.SiteNavigations;


/**
 *  This class is responsible for delegating responsibilities of different functionalities on the platform
 */
public class PlatformRouting {

    public LoginPage getLoginPage()
    {
        return new LoginPage();
    }

    public AccountOverview getAccountOverviewPage()
    {
        return new AccountOverview();
    }

    public ProjectOverview getProjectOverviewPage()
    {
        return new ProjectOverview();
    }
    public SiteNavigations getSiteOverviewPage()
    {
        return new SiteNavigations();
    }
}
