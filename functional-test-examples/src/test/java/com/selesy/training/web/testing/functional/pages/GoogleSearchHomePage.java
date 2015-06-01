package com.selesy.training.web.testing.functional.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchHomePage extends AbstractPageObject {
  
  String searchTerms;
  
  public GoogleSearchHomePage(WebDriver webDriver) {
    super(webDriver);
    webDriver.get("https://www.google.com");
  }
  
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
  
  public boolean isTitled(String title) {
    return webDriver.getTitle().toLowerCase().startsWith(title.toLowerCase());
  }
  
  public void setSearchTerms(String searchTerms) {
    this.searchTerms = searchTerms;
    
    WebElement element = webDriver.findElement(By.name("q"));
    element.sendKeys(searchTerms);
  }

}
