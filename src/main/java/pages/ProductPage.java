package pages;

import utils.BasePage;
import org.openqa.selenium.*;

public class ProductPage extends BasePage {

    By addBasketBtn = By.className("add-to-bs-tx");
    By basketCountItem = By.cssSelector("div.basket-item-count-container.visible");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void addBasket() throws InterruptedException {
        WebElement addBasketButton = driver.findElement(addBasketBtn);
        scrollIntoViewElement(addBasketButton);
        clickByJS(addBasketButton);
        Thread.sleep(2000);

        if(isExist(basketCountItem)) {
            System.out.println("** Ürün sepete eklendi **");
        }

    }
}
