package com.selesy.training.web.testing.functional.pages;

import org.openqa.selenium.WebDriver;

/**
 * Provides methods needed to interogate the state of the Google search results
 * page.
 * 
 * @author Steve Moyer <smoyer1@selesy.com>
 */
public class GoogleSearchResultsPage extends AbstractPageObject {

  /**
   * Constructs a Google search results page object using the current state
   * of the WebDriver provided as a parameter.  Since there is no call to a
   * URL, this constructor should only be called by clicking the search button
   * (using the appropriate method in the GoogleSearchHomePage).
   * 
   * @param webDriver the current test's WebDriver instance.
   */
  public GoogleSearchResultsPage(WebDriver webDriver) {
    super(webDriver);
  }

}
