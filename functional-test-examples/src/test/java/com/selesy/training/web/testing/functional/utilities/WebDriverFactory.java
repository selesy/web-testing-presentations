package com.selesy.training.web.testing.functional.utilities;

import org.openqa.selenium.WebDriver;

/**
 * Provides a factory method that produces a WebDriver instance using the value
 * of a system property to decide which implementation should be created.
 * 
 * @author Steve Moyer <smoyer1@selesy.com>
 */
public class WebDriverFactory {
  
  static final String EXCEPTION_UNKNOWN_WEBDRIVER_NAME =
      "The name of a WebDriver was provided but not recognized - valid values "
      + "are ChromeDriver, FirefoxDriver and HtmlUnitDriver";

  // Name of the system property that, if found, controls which WebDriver is
  // instantiated below.
  static final String WEB_DRIVER_TYPE_PROPERTY = "webdriver.name";

  // Valid names for drivers that this factory can create - these names are
  // compared without case-sensitivity below.  More importantly, these
  // Strings are used with class.forName to instantiate the returned
  // WebDriver.
  static final String CHROME_WEBDRIVER_NAME = "ChromeDriver";
  static final String FIREFOX_WEBDRIVER_NAME = "FirefoxDriver";
  static final String HTMLUNIT_WEBDRIVER_NAME = "HtmlUnitDriver";
  
  // Generate the names of the selenium driver classes from the base package,
  // a package extension and the WebDriver name from above.
  static final String SELENIUM_BASE_PACKAGE = "org.openqa.selenium";
  
  static final String CHROME_WEBDRIVER_CLASS = SELENIUM_BASE_PACKAGE + ".chrome." + CHROME_WEBDRIVER_NAME;
  static final String FIREFOX_WEBDRIVER_CLASS = SELENIUM_BASE_PACKAGE + ".firefox." + FIREFOX_WEBDRIVER_NAME;
  static final String HTMLUNIT_WEBDRIVER_CLASS = SELENIUM_BASE_PACKAGE + ".htmlunit." + HTMLUNIT_WEBDRIVER_NAME;

  // Indicates which WebDriver implementation should be used if there isn't
  // one specified via the system property.
  public static final String DEFAULT_WEB_DRIVER = FIREFOX_WEBDRIVER_NAME;
  public static final String DEFAULT_PROFILE_NAME = "selenium";

  /**
   * Returns a WebDriver based on the value passed in via the webdriver.name
   * system property. If this property is missing (or null/empty) the WebDriver
   * specified by the DEFAULT_WEB_DRIVER constant will determine the type of
   * instance returned.
   * 
   * @return an instance of the requested (or default) WebDriver.
   * @throws ClassNotFoundException if the named driver class cannot be found.
   * @throws IllegalAccessException if the security manager doesn't allow the
   * instantiation of the driver class.
   * @throws InstantiationException if an instance of the specified class can
   * not be instantiated or if an unknown name is passed via the system
   * property.
   */
  public static final WebDriver getWebDriver() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
    Class<?> clazz = null;
    
    // Use the default if the property isn't set
    String webDriverName = System.getProperty(WEB_DRIVER_TYPE_PROPERTY);
    if(webDriverName == null || webDriverName.isEmpty()) {
      webDriverName = DEFAULT_WEB_DRIVER;
    }
    
    // Pick a driver class based on the driver name
    if(CHROME_WEBDRIVER_NAME.equalsIgnoreCase(webDriverName)) {
      clazz = (Class<?>) Class.forName(CHROME_WEBDRIVER_CLASS);
    } else if(FIREFOX_WEBDRIVER_NAME.equalsIgnoreCase(webDriverName)) {
      clazz = (Class<?>) Class.forName(FIREFOX_WEBDRIVER_CLASS);
    } else if(HTMLUNIT_WEBDRIVER_NAME.equalsIgnoreCase(webDriverName)) {
      clazz = (Class<?>) Class.forName(HTMLUNIT_WEBDRIVER_CLASS);
    } else {
      // If a driver name was provided but not recognized, that's an error
      throw new InstantiationException(EXCEPTION_UNKNOWN_WEBDRIVER_NAME);
    }
    
    return (WebDriver) clazz.newInstance();
  }

}
