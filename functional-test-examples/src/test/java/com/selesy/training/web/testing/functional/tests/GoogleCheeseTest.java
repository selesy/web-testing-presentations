/**
 * 
 */
package com.selesy.training.web.testing.functional.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

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

  @Test
  public void testCheeseSearch() {
    webDriver.get("https://www.google.com");
    assertEquals("Google", webDriver.getTitle());

    WebElement element = webDriver.findElement(By.name("q"));
    element.sendKeys("Cheese!");
    element.submit();

    (new WebDriverWait(webDriver, 10)).until(new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver d) {
        return d.getTitle().toLowerCase().startsWith("cheese!");
      }
    });

    assertEquals("Cheese! - Google Search", webDriver.getTitle());
  }

}
