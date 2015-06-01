package com.selesy.training.web.testing.functional.pages;

import org.openqa.selenium.WebDriver;

/**
 * Provides common functionality required when implementing the PageObject
 * pattern.
 * 
 * @author Steve Moyer <smoyer1@selesy.com>
 */
public abstract class AbstractPageObject {
  
  WebDriver webDriver;
  
  /**
   * This constructor simply stashes away a reference to the WebDriver so that
   * it can be used by methods in this class as well as in the sub-classes.
   * 
   * @param webDriver a reference to the WebDriver.
   */
  public AbstractPageObject(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  /**
   * Tests whether the current page's title start with the string passed.
   * 
   * @param title the expected title.
   * @return a boolean describing whether the actual title matches the
   * expected title.
   */
  public boolean isTitled(String title) {
    return webDriver.getTitle().toLowerCase().startsWith(title.toLowerCase());
  }

}
