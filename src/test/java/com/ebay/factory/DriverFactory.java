package com.ebay.factory;

import com.ebay.factory.driver.ChromeDriver;
import com.ebay.factory.driver.Driver;
import com.ebay.factory.driver.FirefoxDriver;

import java.util.Optional;
import java.util.function.Supplier;

public class DriverFactory {

    private static final DriverFactory _instance = new DriverFactory();

    private DriverFactory() {}

    public static DriverFactory getInstance() {
        return _instance;
    }

    public Optional<Driver> getDriver(String browserName) {
        if (browserName == null || browserName.trim().isEmpty()) {
            return Optional.empty();
        }

        DriverType driverType;
        try {
            driverType = DriverType.valueOf(browserName.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return Optional.empty();
        }

        return Optional.of(driverType.getInstance());
    }

    public enum DriverType {
        CHROME(ChromeDriver::new),
        FIREFOX(FirefoxDriver::new);

        private final Supplier<Driver> typeSupplier;

        DriverType(Supplier<Driver> typeSupplier) {

            this.typeSupplier = typeSupplier;
        }

        public Driver getInstance() {
            return typeSupplier.get();
        }
    }
}
