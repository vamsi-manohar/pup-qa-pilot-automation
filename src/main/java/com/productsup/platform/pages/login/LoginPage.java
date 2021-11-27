package com.productsup.platform.pages.login;

import java.util.Base64;

import com.productsup.platform.pages.account.AccountOverview;
import org.openqa.selenium.By;

import com.productsup.platform.driver.DriverManager;
import com.productsup.platform.enums.ConfigProperties;
import com.productsup.platform.enums.WaitStrategy;
import com.productsup.platform.pages.BasePage;
import com.productsup.platform.utils.DynamicLocatorStrategy;
import com.productsup.platform.utils.PropertyUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    private WebDriverWait wait;

    public LoginPage()
    {
        PageFactory.initElements(DriverManager.getDriver(),this);
        this.wait= new WebDriverWait(DriverManager.getDriver(),60);

    }

    private String inputType = "input[name='%s']";
    private String nextButton = "//*[text()='%s']";

    @FindBy(id="frame")
    private WebElement loginFrame;




    public AccountOverview loginToPlatform() {
        DriverManager.getDriver().navigate().to(PropertyUtils.getValue(ConfigProperties.URL));
        this.wait.until(d->loginFrame.isDisplayed());
        DriverManager.getDriver().switchTo().frame(loginFrame);

      /*  DriverManager.getDriver().switchTo().frame(DriverManager.getDriver().
                findElement(By.cssSelector(
                        "[src='//login.productsup.com?hl=en&url=https://platform.productsup.com']")))
        ;*/
        enterUsername();
        enterPassword();
        clickLogin();


// Not working effectively when navigating to data view module
/*        DriverManager.getDriver().navigate().to(PropertyUtils.getValue(ConfigProperties.URL));
        Cookie cookie = new Cookie("PHPSESSID", "gnlidb9cm070n1990hvl4mbg8q");
        DriverManager.getDriver().manage().addCookie(cookie);
        DriverManager.getDriver().navigate().to(PropertyUtils.getValue(ConfigProperties.URL));
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);*/
        return new AccountOverview();
    }

    private void enterUsername() {
        sendKeys(By.cssSelector(DynamicLocatorStrategy.getDynamicLocator(inputType, "username")),
                PropertyUtils.getValue(ConfigProperties.USERNAME), WaitStrategy.PRESENCE);
        click(By.xpath(DynamicLocatorStrategy.getDynamicLocator(nextButton, "Next")), WaitStrategy.CLICKABLE);
    }

    private void enterPassword() {
        String decodedPassword = decodePassword();
        sendKeys(By.cssSelector(DynamicLocatorStrategy.getDynamicLocator(inputType, "current-password")),
                decodedPassword, WaitStrategy.PRESENCE);
    }

    private void clickLogin() {
        click(By.xpath(DynamicLocatorStrategy.getDynamicLocator(nextButton, "Log In")), WaitStrategy.CLICKABLE);
    }

    private String decodePassword() {
        return new String(Base64.getDecoder().decode(PropertyUtils.getValue(ConfigProperties.PASSWORD).getBytes()));

    }

}
