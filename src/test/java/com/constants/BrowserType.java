package com.constants;

public enum BrowserType {
    CHROME,
    FIREFOX,
    SAFARI;

    public static BrowserType fromString(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                return CHROME;
            case "firefox":
                return FIREFOX;
            case "safari":
                return SAFARI;
            default:
                throw new IllegalArgumentException("Invalid browser name: " + browserName);
        }
    }
}

