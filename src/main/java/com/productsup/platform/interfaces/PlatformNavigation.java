package com.productsup.platform.interfaces;

import com.google.common.util.concurrent.Uninterruptibles;
import com.productsup.platform.enums.Navigations;
import com.productsup.platform.enums.WaitStrategy;
import com.productsup.platform.pages.BasePage;
import com.productsup.platform.utils.DynamicLocatorStrategy;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public interface PlatformNavigation {


    By navbar = By.className("sidebar");

   void navigateTo(String menu, Navigations navigations);
    default void navigateToMenuBar()
    {

        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);

        BasePage.scrollIntoView(navbar);
        BasePage.mouseHover(navbar);

    }

}
