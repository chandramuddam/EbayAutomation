package com.ebay;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.ebay.stepdefinitions.EBayStepDefinition;

@CucumberOptions(plugin = { "pretty",
		"json: target/cucumber-json-report.json" }, features = "src/test/resources/features/ebay.feature")
public class RunCukeTest extends AbstractTestNGCucumberTests {
	public static List<String> lstBrowserName = new ArrayList<String>();
	public EBayStepDefinition eBayStepDefinition = new EBayStepDefinition();

	@Parameters("browserName")
	@BeforeTest
	public void beforeTest(String browserName) {
		System.setProperty("ebay." + browserName.toLowerCase(), "not-used");
	}
}
