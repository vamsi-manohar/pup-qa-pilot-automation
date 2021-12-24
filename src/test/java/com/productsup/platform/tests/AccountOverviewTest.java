package com.productsup.platform.tests;

import com.productsup.platform.annotations.FrameworkAnnotation;
import com.productsup.platform.enums.CategoryType;
import com.productsup.platform.pages.account.AccountOverview;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class AccountOverviewTest extends BaseTest
{

    @FrameworkAnnotation(author = {"Vamsi"}, category = {CategoryType.SMOKE})
    @Test
    public void globalSiteSearch(Map<String, String> data) {

        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions())
        {

            AccountOverview accountOverview = new AccountOverview();
            accountOverview.globalSiteSearch(data.get("Site_Search_Value"),data.get("Site_Search_Category"));
            List<String> searchResults = accountOverview.getSearchResults();
            System.out.println("Results are :: " +searchResults);
            softly.assertThat(searchResults)
                    .isNotEmpty()
                    .hasSizeGreaterThan(0);

        }
    }


}
