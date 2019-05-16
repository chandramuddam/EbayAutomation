package com.ebay.factory.driver;

import org.openqa.selenium.WebDriver;

public class ChromeDriver extends AbstractDriver {

    public ChromeDriver() {
        setDriverExecutable("webdriver.chrome.driver", "chrome", "chromedriver");
    }

    @Override
    protected WebDriver getWebDriverInner() {
        return new org.openqa.selenium.chrome.ChromeDriver();
    }
}
