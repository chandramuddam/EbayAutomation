package com.ebay.factory.driver;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public abstract class AbstractDriver implements Driver {

	private static final String DRIVER_PATH_PATTERN = "src/test/resources/driver/%s/%s/%s";

	@Override
	public WebDriver getWebDriver() {
		WebDriver driver = getWebDriverInner();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		return driver;
	}

	protected abstract WebDriver getWebDriverInner();

	protected final void setDriverExecutable(String systemPropertyName, String driverType, String execName) {
		String os = System.getProperty("os.name");
		if (os.contains("Win")) {
			System.setProperty(systemPropertyName,
					String.format(DRIVER_PATH_PATTERN, driverType, "windows", execName + ".exe"));
		} else {
			throw new IllegalArgumentException(String.format("The %s OS is not supported!", os));
		}
	}
}
