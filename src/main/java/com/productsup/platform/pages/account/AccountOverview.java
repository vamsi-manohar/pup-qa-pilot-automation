package com.productsup.platform.pages.account;

import com.google.common.util.concurrent.Uninterruptibles;
import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.enums.ConfigProperties;
import com.productsup.platform.enums.SiteSearchCategory;
import com.productsup.platform.enums.WaitStrategy;
import com.productsup.platform.exceptions.FrameworkExceptions;
import com.productsup.platform.pages.BasePage;
import com.productsup.platform.pages.project.ProjectOverview;
import com.productsup.platform.utils.PropertyUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AccountOverview extends BasePage {

    private WebDriverWait wait;


    @FindBy(css="pup-heading span")
    private WebElement pageCaption;

    @FindBy(css="td a")
    private List<WebElement> accountResults;


    @FindBy(css = "a[ng-href*='projects']")
    private List<WebElement> projectList;


    @FindBy(css="pup-heading span[class='caption']")
    private WebElement pageTitle;

    @FindBy(css="app-site-search input[placeholder='Search']")
    private WebElement appSiteSearch;

    @FindBy(css="pup-switch-item span")
    private List<WebElement> categoriesList;

    @FindBy(css="div[class*='result-item']")
    private List<WebElement> resultContainer;




    public AccountOverview() {
        PageFactory.initElements(DriverManager.getDriver(), this);
        this.wait=new WebDriverWait(DriverManager.getDriver(),30);
    }

    private static EnumMap<SiteSearchCategory, String> siteCategoryMapping = new EnumMap<SiteSearchCategory, String>(SiteSearchCategory.class);

    static {
        for (SiteSearchCategory category : SiteSearchCategory.values()) {
            siteCategoryMapping.put(category, category.getData());
        }
    }

    public static String getSearchCategories(SiteSearchCategory siteSearchCategory) {
        return siteSearchCategory.getData();
    }


    /**
     *
     *  Selecting the account
     */
    public void selectAccount()
    {

        Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
        System.out.println("Value :: " +PropertyUtils.getValue(ConfigProperties.ACCOUNT));
        if(pageCaption.getText().equalsIgnoreCase("Accounts"))
        {
            for (int i = 0; i < accountResults.size(); i++)
            {
                System.out.println("Account names :: => " + accountResults.get(i).getText());
                if (accountResults.get(i).getText().
                        equalsIgnoreCase(PropertyUtils.getValue(ConfigProperties.ACCOUNT))) {
                    click(accountResults.get(i), WaitStrategy.CLICKABLE);
                    this.wait.until(d->!projectList.isEmpty());
                }
            }
        }
    }

    /**
     * Selecting the projects with in the account
     * @param projectName - this is supplied as the test data from the spreadsheet
     * @return
     */
    public ProjectOverview selectProject(String projectName)
    {
        this.wait.until(d -> projectList.size() > 0);
        for (int i = 0; i < projectList.size(); i++)
        {
            System.out.println("Project Name :: " + projectList.get(i).getText());
            if (projectList.get(i).getText().contains(projectName)) {
                click(projectList.get(i), WaitStrategy.CLICKABLE);
                this.wait.until(d -> pageTitle.getText().equalsIgnoreCase(projectName));
                System.out.println(pageTitle.getText());
                return new ProjectOverview();

            }

        }
         throw new FrameworkExceptions("Provided Project name is not available within the account on the platform." +
                "Please check the provided project name !!!");

    }



    public void selectSearchCategory(SiteSearchCategory siteSearchCategory)
    {
        for(int i=0;i<categoriesList.size();i++)
        {
            if(categoriesList.get(i).getText().contains(getSearchCategories(siteSearchCategory)))
            {
                click(categoriesList.get(i),WaitStrategy.CLICKABLE);
                break;
            }
        }
    }


    public void selectCategory(String category)
    {
        switch(SiteSearchCategory.valueOf(category))
        {
            case FAVOURITE_SITES:
                selectSearchCategory(SiteSearchCategory.FAVOURITE_SITES);
                break;

            case SITE_NAMES:
                selectSearchCategory(SiteSearchCategory.SITE_NAMES);
                break;

            case SITE_ID:
                selectSearchCategory(SiteSearchCategory.SITE_ID);
                break;

            case EXPORT_CHANNEL_ID:
                selectSearchCategory(SiteSearchCategory.EXPORT_CHANNEL_ID);
                break;
        }
    }


    public AccountOverview globalSiteSearch(String site, String category)
    {
        click(appSiteSearch,WaitStrategy.CLICKABLE);
        sendKeys(appSiteSearch,WaitStrategy.VISIBLE,site);
        selectCategory(category);
        this.wait.until(d->!resultContainer.isEmpty());
        return this;
    }


    /**
     * This method return all the search results
     * @return
     */
    public List<String>getSearchResults()
    {
        List<String>resultSet = new ArrayList<>();
        for(int i=0;i<resultContainer.size();i++)
        {
            resultSet.add(resultContainer.get(i).getText());

        }

        return resultSet;
    }




}

