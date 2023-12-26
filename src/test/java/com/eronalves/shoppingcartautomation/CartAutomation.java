package com.eronalves.shoppingcartautomation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartAutomation {

  private WebDriver driver;

  @BeforeMethod
  public void beforeMethod () {
    System.setProperty(
        "webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver"
    );
    driver = new ChromeDriver();
  }

  @Test
  public void doIncludeInCartList ()
      throws InterruptedException {
    driver.get("https://www.amazon.com.br/");
    WebElement homeSearchBox = driver.findElement(By.id("twotabsearchtextbox"));
    homeSearchBox.sendKeys("iphone");
    homeSearchBox.submit();
    WebElement brandFilter = driver.findElement(By.id("brandsRefinements"));
    WebElement appleFilter = brandFilter.findElement(By.linkText("Apple"));
    appleFilter.click();
    WebElement iphone13Link =
        driver.findElement(By.partialLinkText("iPhone 13"));
    iphone13Link.click();
    WebElement addToCart = driver.findElement(By.id("add-to-cart-button"));
    addToCart.click();
    List<WebElement> addCoverageButton =
        driver.findElements(By.id("attachSiNoCoverage"));
    if (!addCoverageButton.isEmpty()) {
      WebElement input =
          addCoverageButton.get(0).findElement(By.tagName("input"));
      new WebDriverWait(driver, 2000).until(
          t -> t.findElement(By.id("attachSiNoCoverage"))
              .findElement(By.tagName("input"))
              .isDisplayed()
      );
      input.click();
    }
  }

}
