package com.productsup.platform.pages.platform.hierrarchy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.google.common.util.concurrent.Uninterruptibles;
import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.pages.BasePage;

public class SidebarNavigationMenu extends BasePage {

	List<String> availableOptions = new ArrayList<>();

	public SidebarNavigationMenu() {
		PageFactory.initElements(DriverManager.getDriver(), this);
	}

	@FindBy(css = "a span[class='title']")
	List<WebElement> menuOptions;

	By navBar = By.className("sidebar");

	public List<String> getAvailableOptions() {
		Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
		mouseHover(navBar);
		Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
		for (int i = 0; i < menuOptions.size(); i++) {
			availableOptions.add(menuOptions.get(i).getText());
		}

		return availableOptions;
	}

}
