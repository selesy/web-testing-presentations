package com.selesy.training.web.testing.functional.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import com.selesy.training.web.testing.functional.utilities.WebDriverFactory;

/**
 * Provides an abstract base class for Selenium tests that simply provides the
 * set-up and tear-down of the selected WebDriver.
 * 
 * @author Steve Moyer <smoyer1@selesy.com>
 */
public abstract class AbstractSeleniumTest {

  WebDriver webDriver;

  /**
   * Use the factory to obtain the selected WebDriver instance.
   * 
   * @throws ClassNotFoundException see WebDriverFactory.class
   * @throws InstantiationException see WebDriverFactory.class
   * @throws IllegalAccessException see WebDriverFactory.class
   */
  @Before
  public void setUp() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
    webDriver = WebDriverFactory.getWebDriver();
  }

  /**
   * Close the driver if it's not null.
   */
  @After
  public void tearDown() {
    if (webDriver != null) {
      webDriver.quit();
    }
  }

}
