package com.ebay;

import org.testng.IExecutionListener;

public class TestNGExecutionListener implements IExecutionListener {

	@Override
	public void onExecutionStart() {
		System.out.println("TestNG started");
	}

	@Override
	public void onExecutionFinish() {
		System.out.println("Generating the HTML Report");
		GenerateReport.GenerateMasterthoughtReport();
		System.out.println("TestNG finished");
	}
}
