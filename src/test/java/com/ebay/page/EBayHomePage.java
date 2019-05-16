package com.ebay.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class EBayHomePage {
    @FindBy(css = "input[placeholder='Search for anything']")
    private WebElement searchElement;

    public EBayHomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void searchFor(String searchItem) {
    	searchElement.clear();
    	searchElement.sendKeys(searchItem);
    	searchElement.sendKeys(Keys.RETURN);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ignore) {
        }
    }
}
