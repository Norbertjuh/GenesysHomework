import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;

    public By sauceLabsBackpackPurchaseButton = By.id("add-to-cart-sauce-labs-backpack");

    public By sauceLabsFleeceJacketPurchaseButton = By.id("add-to-cart-sauce-labs-fleece-jacket");

    public By shoppingCartBadge = By.xpath("//*[@id=\"shopping_cart_container\"]/a/span");


    public HomePage(WebDriver driver) {
        this.driver=driver;
    }

    public HomePage putItemsToCart() {
        driver.findElement(sauceLabsBackpackPurchaseButton).click();
        driver.findElement(sauceLabsFleeceJacketPurchaseButton).click();
        return new HomePage(driver);
    }
    public CartPage clickOnCartIcon() {
        driver.findElement(shoppingCartBadge).click();
        return new CartPage(driver);
    }
}
