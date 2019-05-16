package com.ebay.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class EbayCartDetailsPage {

	private final WebDriver driver;

	@FindBy(css = "a.btn.btn-prim.vi-VR-btnWdth-XL")
	private WebElement cartTable;

	@FindBy(css = "div.clzBtn.viicon-close")
	private WebElement closeCartWindow;

	@FindBy(xpath = "//span[contains(text(),'Go to cart')]")
	public WebElement goToCartSummaryButton;

	public EbayCartDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void hasItems(Integer numberOfItems) {
		String text = driver.findElement(By.cssSelector("a.btn.btn-prim.vi-VR-btnWdth-XL")).getText();
		System.out.println("cartTable.getText()  " + text);
		boolean contains = text
				.contains(String.format("Checkout of " + numberOfItems + " items"));

		Assert.assertTrue(contains, "The item does not seem to be added in the cart!");
	}

	public void closeCartWindow() {
		closeCartWindow.click();
	}

	public void goToCartSummaryPage() {

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(goToCartSummaryButton));
		goToCartSummaryButton.click();
	}
}
