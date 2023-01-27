import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OverviewPage {

    WebDriver driver;
    public OverviewPage(WebDriver driver) {
        this.driver=driver;
    }

    private By finishButton = By.id("finish");

    public OverviewPage clickOnFinishButton() {
        driver.findElement(finishButton).click();
        return new OverviewPage(driver);
    }
}
