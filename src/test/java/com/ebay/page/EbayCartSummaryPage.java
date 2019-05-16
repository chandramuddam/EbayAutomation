package com.ebay.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class EbayCartSummaryPage {

	private final WebDriver driver;
	private List<String> lstExpectedItems = new ArrayList<String>();
	private List<String> lstActualItems = new ArrayList<String>();

	@FindBy(css = "div.cart-bucket-lineitem div.lineitem-ctr div.listsummary div.cart-bucket-lineitem-foot button[data-test-id='cart-remove-item']")
	public List<WebElement> lstRemoveButtons;

	@FindBy(css = "div.cart-bucket h3.item-title")
	public List<WebElement> lstCartItems;

	public EbayCartSummaryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void cartHasItems() {
		lstExpectedItems.add("SAMSUNG");
		lstExpectedItems.add("LG");
		Collections.sort(lstExpectedItems);

		for (WebElement cartItem : lstCartItems) {
			lstActualItems.add(cartItem.getText());
			System.out.println("cartItem.getText() : " + cartItem.getText());
		}

		Collections.sort(lstActualItems);
		boolean match;
		for (int itemCount = 0; itemCount < lstActualItems.size(); itemCount++) {
			match = lstActualItems.get(itemCount).contains(lstExpectedItems.get(itemCount));
			Assert.assertTrue(match, "Item is not matched");
		}
	}

	public void clearCart() {
		List<WebElement> lstRemoveButtons = driver.findElements(By.cssSelector(
				"div.cart-bucket-lineitem div.lineitem-ctr div.listsummary div.cart-bucket-lineitem-foot button[data-test-id='cart-remove-item']"));
		try {

			for (WebElement removeButton : lstRemoveButtons) {
				TimeUnit.SECONDS.sleep(3);
				removeButton.click();
			}
		} catch (InterruptedException ignore) {
		}

	}
}
