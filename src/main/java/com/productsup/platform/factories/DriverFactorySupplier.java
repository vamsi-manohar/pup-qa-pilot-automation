package com.productsup.platform.factories;

import com.productsup.platform.enums.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DriverFactorySupplier {

    private DriverFactorySupplier() {

    }


    private static final Supplier<WebDriver> CHROME = () ->
    {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    };

    private static final Supplier<WebDriver> FIREFOX = () ->
    {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    };

    private static final Supplier<WebDriver> EDGE = () ->
    {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    };

    private static final Supplier<WebDriver> SAFARI = () ->
    {
        WebDriverManager.safaridriver().setup();
        return new SafariDriver();
    };

    private static final Map<BrowserType, Supplier<WebDriver>> MAP = new EnumMap<>(BrowserType.class);

    static {
        MAP.put(BrowserType.CHROME, CHROME);
        MAP.put(BrowserType.FIREFOX, FIREFOX);
        MAP.put(BrowserType.EDGE, EDGE);
        MAP.put(BrowserType.SAFARI, SAFARI);
    }


    public static WebDriver getDriver(BrowserType browser)
    {
        return MAP.get(browser).get();

    }


}
