package com.ebay.factory.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirefoxDriver extends AbstractDriver {

    public FirefoxDriver() {
        setDriverExecutable("webdriver.gecko.driver", "firefox", "geckodriver");
    }

    @Override
    protected WebDriver getWebDriverInner() {
        return new org.openqa.selenium.firefox.FirefoxDriver();
    }
}
