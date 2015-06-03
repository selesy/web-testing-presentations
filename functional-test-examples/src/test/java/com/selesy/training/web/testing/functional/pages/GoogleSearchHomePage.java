package com.selesy.training.web.testing.functional.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Provides access to the elements of the Google Search Home Page needed to
 * execute the test cases.
 * 
 * @author Steve Moyer
 */
public class GoogleSearchHomePage extends AbstractPageObject {
  
  String searchTerms;
  
  /**
   * Connects the provided WebDriver to the Google search page and constructs
   * a new instance of the GoogleSearchHomePage class.
   * 
   * @param webDriver the current instance of the WebDriver.
   */
  public GoogleSearchHomePage(WebDriver webDriver) {
    super(webDriver);
    webDriver.get("https://www.google.com");
  }
  
  /**
   * Clicks the search button on the Google search home page.
   * 
   * @return an instance of the resulting GoogleSearchResultsPage.
   */
  public GoogleSearchResultsPage clickGoogleSearchButton() {
    WebElement element = null;
    try{
      element = webDriver.findElement(By.name("btnK"));
    } catch(NoSuchElementException e) {
      element = webDriver.findElement(By.name("btnG"));
    }
    element.submit();

    (new WebDriverWait(webDriver, 10)).until(new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver d) {
        return d.getTitle().toLowerCase().startsWith(searchTerms.toLowerCase());
      }
    });
    
    return new GoogleSearchResultsPage(webDriver);
  }

  public boolean hasSearchInputField() {
    return false;
  }
  
  /**
   * Verifies that the actual title (retrieved via the WebDriver) to the
   * expected titled passed as the only parameter.
   * 
   * @param title the expected title of the current web page.
   * @return a boolean true value if the retrieved title matches the expected
   *         title, otherwise false.
   */
  public boolean isTitled(String title) {
    return webDriver.getTitle().toLowerCase().startsWith(title.toLowerCase());
  }
  
  /**
   * Enters the search terms into the Google Search page's search field.
   * 
   * @param searchTerms the terms to be searched.
   */
  public void setSearchTerms(String searchTerms) {
    this.searchTerms = searchTerms;
    
    WebElement element = webDriver.findElement(By.name("q"));
    element.sendKeys(searchTerms);
  }

}
