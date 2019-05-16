package com.ebay.stepdefinitions;

import com.ebay.factory.driver.Driver;
import com.ebay.factory.DriverFactory;
import com.ebay.page.EBayHomePage;
import com.ebay.page.EbayItemPage;
import com.ebay.page.EbaySearchResultPage;
import com.ebay.page.EbayCartDetailsPage;
import com.ebay.page.EbayCartSummaryPage;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import org.openqa.selenium.WebDriver;

public class EBayStepDefinition {

	private WebDriver driver;
	private EBayHomePage eBayHomePage;
	private EbaySearchResultPage ebaySearchResultPage;
	private EbayItemPage ebayItemPage;
	private EbayCartDetailsPage ebayCartDetailsPage;
	private EbayCartSummaryPage ebayCartSummaryPage;
	private String propertyUsed;
	// private String browserName;

	@Before
	public void setup() {
		propertyUsed = System.getProperty("ebay.firefox");
		AtomicReference<String> browserName = new AtomicReference<>("chrome");
		synchronized (propertyUsed) {
			while ("not-used".equals((propertyUsed = System.getProperty("ebay.firefox")))) {
				System.out.println(propertyUsed);
				propertyUsed = "used";
				System.setProperty("ebay.firefox", propertyUsed);
				browserName.set("firefox");
			}
		}
		System.out.println("browserName: " + browserName);
		Optional<Driver> optionalDriver = DriverFactory.getInstance().getDriver(browserName.get());
		driver = optionalDriver
				.orElseThrow(() -> new IllegalArgumentException("Could not find driver for " + browserName.get()))
				.getWebDriver();
		driver.manage().window().maximize();
	}

	@Given("^I launch eBay website$")
	public void i_launch_eBay_website() {
		driver.get("http://www.ebay.com.au");
	}

	@When("^I enter the text \"([^\"]*)\"$")
	public void i_enter_the_text(String searchItem) {
		eBayHomePage = new EBayHomePage(driver);
		eBayHomePage.searchFor(searchItem);
	}

	@When("^I click on the search item$")
	public void i_click_on_the_search_item() {
		ebaySearchResultPage = new EbaySearchResultPage(driver);
		ebaySearchResultPage.clickFirstItem();
	}

	@Then("^I navigate to the \"([^\"]*)\"$")
	public void i_navigate_to_the(String page) {

		switch (page) {
		case "Item page":
			ebayItemPage = new EbayItemPage(driver);
			break;

		case "Cart summary page":
			ebayCartDetailsPage = new EbayCartDetailsPage(driver);
			ebayCartDetailsPage.goToCartSummaryPage();
			ebayCartSummaryPage = new EbayCartSummaryPage(driver);
			break;
		}
	}

	@When("^I click on \"([^\"]*)\" button$")
	public void i_click_on_button(String click) {
		switch (click) {
		case "Add to cart":
			ebayItemPage.addToCart();
			break;

		case "Close cart window":
			ebayCartDetailsPage.closeCartWindow();
			break;

		default:
			throw new IllegalArgumentException("Please check the action '" + click + "'");
		}
	}
	@Then("^The item should be added to the shopping cart$")
	public void the_item_should_be_added_to_the_shopping_cart() {
		ebayCartDetailsPage = new EbayCartDetailsPage(driver);
	}

	@And("^I verify the items in the shopping cart$")
	public void i_verify_the_items_in_the_shopping_cart() {
		ebayCartSummaryPage.cartHasItems();
	}

	@And("^I clear the cart$")
	public void i_clear_the_cart() {
		ebayCartSummaryPage.clearCart();
	}

	@After
	public void afterScenario() {
		driver.quit();
	}
}
