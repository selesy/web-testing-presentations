/**
 * 
 */
package com.selesy.training.web.testing.functional.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selesy.training.web.testing.functional.pages.GoogleSearchHomePage;
import com.selesy.training.web.testing.functional.pages.GoogleSearchResultsPage;

/**
 * Provides a slightly altered version of the Java test example provided by the
 * Selenium documentation. See http://docs.seleniumhq.org/docs/03_webdriver.jsp#introducing-the-selenium-webdriver-api-by-example
 * This test provides the exact same functionality but uses the abstract base
 * test class to set-up and tear-down the WebDriver and replaces that output to
 * the console with test assertions.
 * 
 * @author Steve Moyer <smoyer1@selesy.com>
 */
public class GoogleCheeseTest extends AbstractSeleniumTest {

  /**
   * This test is equivalent to the one referenced by the link above with the
   * changes needed to turn it into a JUnit test.
   */
  @Test
  public void testCheeseSearch() {
    // Navigate to Google's search home page and verify that the page title
    // matches the expected "Google".
    webDriver.get("https://www.google.com");
    assertEquals("Google", webDriver.getTitle());

    // Find the search text input and "type" the phrase "Cheese!" into it.
    // Then submit the form containing the element.
    WebElement element = webDriver.findElement(By.name("q"));
    element.sendKeys("Cheese!");
    element.submit();

    // Wait until search results have loaded (with a 10 second time-out) by
    // waiting until the title changes to match the search string
    (new WebDriverWait(webDriver, 10)).until(new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver d) {
        return d.getTitle().toLowerCase().startsWith("cheese!");
      }
    });

    // Verify that the title has changed to the expected value
    assertEquals("Cheese! - Google Search", webDriver.getTitle());
  }

  /**
   * This test is equivalent to the test above but uses the PageObject pattern
   * to abstract the description of the page away from the browser automation
   * and test assertions.  Notice how conversational the tests become when the
   * operational portions of the automation is moved into the page objects.
   */
  @Test
  public void testCheeseSearchWithPageObjects() {
    GoogleSearchHomePage homePage = new GoogleSearchHomePage(webDriver);
    assertTrue(homePage.isTitled("Google"));
    homePage.setSearchTerms("Cheese!");
    GoogleSearchResultsPage resultsPage = homePage.clickGoogleSearchButton();
    assertTrue(resultsPage.isTitled("Cheese! - Google Search"));
  }

}
