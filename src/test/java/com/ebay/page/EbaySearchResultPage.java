package com.ebay.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EbaySearchResultPage {

    private final WebDriver driver;

    @FindBy(css = "input[aria-label='Brand New']")
    private WebElement newItem;

    public EbaySearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickFirstItem() {
    	newItem.click();
        driver.findElement(By.cssSelector("li#srp-river-results-listing1 a.s-item__link")).click();
    }

}
