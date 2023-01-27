import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    WebDriver driver;
    public CartPage(WebDriver driver) {
        this.driver=driver;
    }

    private By checkoutButton = By.id("checkout");

    private By firstNameBy = By.id("first-name");

    private By lastNameBy = By.id("last-name");

    private By zipCodeBy = By.id("postal-code");

    private By continueButton = By.id("continue");


    public CartPage clickOnCheckoutButton() {
        driver.findElement(checkoutButton).click();
        return new CartPage(driver);
    }

    public CartPage fillPersonalInfo(String firstName,String lastName,String zipCode) {
        driver.findElement(firstNameBy).sendKeys(firstName);
        driver.findElement(lastNameBy).sendKeys(lastName);
        driver.findElement(zipCodeBy).sendKeys(zipCode);
        return new CartPage(driver);
    }

    public OverviewPage clickOnContinueButton() {
        driver.findElement(continueButton).click();
        return new OverviewPage(driver);
    }

}
