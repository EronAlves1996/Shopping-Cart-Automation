# Shopping Cart Automation

Automação simples de compra de kit completo Apple via Amazon.
Este projeto é um um projeto apenas para aprendizado e introdução na ferramenta Selenium.

Utilizei alguns princípios de abstração neste para que a função principal dessa automação tenha apenas 5 linhas no seu corpo:

```java
public void doIncludeInCartList ()
      throws InterruptedException {
    driver.get("https://www.amazon.com.br/");
    searchAndBuyAppleProduct("iphone", "iPhone 13");
    searchAndBuyAppleProduct("Carregador iphone", "Cabo");
    searchAndBuyAppleProduct("Carregador iphone", "Adaptador");
    searchAndBuyAppleProduct("fone de ouvido apple", "Apple AirPods");
    searchAndBuyAppleProduct("macbook", "MacBook Air");
  }
```
Sendo assim, o processo fica extremamente simples.
Caso seja necessário comprar qualquer outro produto, basta acionar a função novamente repassando o keyword e o nome do produto da forma que aparece no catálogo da Amazon.

Esta automação apenas adiciona os produtos no carrinho.