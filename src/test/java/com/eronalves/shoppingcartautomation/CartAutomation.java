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

  private void search (String product) {
    WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
    searchBox.click();
    searchBox.sendKeys(product);
    searchBox.submit();
  }

  private void filterByBrand (String brand) {
    driver.findElement(By.id("brandsRefinements"))
        .findElement(By.linkText(brand))
        .click();
  }

  private void clickOnProduct (String partialProductName) {
    driver.findElement(By.partialLinkText(partialProductName)).click();
  }

  private void addToCart () {
    driver.findElement(By.id("add-to-cart-button")).click();
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

  private void waitToLoadPage (String pageTitle) {
    new WebDriverWait(driver, 2).until(t -> t.getTitle().equals(pageTitle));
  }

  @Test
  public void doIncludeInCartList ()
      throws InterruptedException {
    driver.get("https://www.amazon.com.br/");
    searchAndBuyAppleProduct("iphone", "iPhone 13");
    searchAndBuyAppleProduct("Carregador iphone", "Cabo");
    searchAndBuyAppleProduct("Carregador iphone", "Adaptador");
    searchAndBuyAppleProduct("fone de ouvido apple", "Apple AirPods");
    searchAndBuyAppleProduct("macbook", "MacBook Air");
  }

  private void searchAndBuyAppleProduct (
      String keyword,
      String product
  )
      throws InterruptedException {
    search(keyword);
    getOnlyAppleProducts();
    addProductToCart(product);
  }

  private void addProductToCart (String product)
      throws InterruptedException {
    clickOnProduct(product);
    Thread.sleep(1000);
    addToCart();
    waitToLoadPage("Carrinho de compras da Amazon.com");
  }

  private void getOnlyAppleProducts () {
    filterByBrand("Apple");
  }

}
