import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeleniumTest {

    static WebDriver driver;

    @BeforeEach
    public void initPage() {
        //path to local chromedriver
        System.setProperty("webdriver.chrome.driver", "c:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void case1() throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        OverviewPage overviewPage = new OverviewPage(driver);

        driver.get("https://www.saucedemo.com/inventory.html");
        loginPage.loginByJsonCredentials();
        homePage.putItemsToCart();
        WebElement shoppingCartBadge = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span"));
        assertEquals("2", shoppingCartBadge.getText());

        homePage.clickOnCartIcon();
        cartPage.clickOnCheckoutButton();
        cartPage.fillPersonalInfo("Norbert", "Juhász", "1181");
        cartPage.clickOnContinueButton();
        overviewPage.clickOnFinishButton();
        WebElement confirmationMessage = driver.findElement(By.xpath("//*[@id=\"checkout_complete_container\"]/h2"));
        assertEquals("THANK YOU FOR YOUR ORDER", confirmationMessage.getText());
    }

    @Test
    public void case2() {
        LoginPage loginPage = new LoginPage(driver);

        driver.get("https://www.saucedemo.com/inventory.html");
        loginPage.clickLoginButton();
        WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3"));
        assertEquals("Epic sadface: Username is required", errorMessage.getText());

        loginPage.loginValidUser("standard_user", "secret_sauce");
        WebElement footerMessage = driver.findElement(By.className("footer_copy"));
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1500)");
        assertTrue(footerMessage.getText().contains("2023"));
        assertTrue(footerMessage.getText().contains("Terms of Service"));
    }

    @Test
    public void case3() {
        driver.get("https://onlinehtmleditor.dev");
        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"cke_1_contents\"]/iframe")));
        WebElement textField = driver.findElement(By.cssSelector("body"));
        textField.sendKeys(Keys.CONTROL + "i");
        textField.sendKeys("Automation ");
        textField.sendKeys(Keys.CONTROL + "i");
        textField.sendKeys(Keys.CONTROL + "u");
        textField.sendKeys("Test ");
        textField.sendKeys(Keys.CONTROL + "u");
        textField.sendKeys("Example");
        assertEquals("Automation Test Example", textField.getText());
    }

    @Test
    public void case4() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("http://demo.guru99.com/test/guru99home");
        Thread.sleep(2000);
        driver.switchTo().frame(driver.findElement(By.id("gdpr-consent-notice")));
        WebElement acceptCookiesButton = driver.findElement(By.id("save"));
        acceptCookiesButton.click();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,2000)");
        driver.switchTo().frame(driver.findElement(By.id("a077aa5e")));
        WebElement jMeterImage = driver.findElement(By.tagName("img"));
        jMeterImage.click();
        String oldTab = driver.getWindowHandle();
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        newTab.remove(oldTab);
        driver.switchTo().window(newTab.get(0));
        assertEquals("Selenium Live Project: FREE Real Time Project for Practice", driver.getTitle());

        driver.close();
        driver.switchTo().window(oldTab);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-2000)");
        WebElement testingDropdown = driver.findElement(By.xpath("//*[@id=\"rt-header\"]//div[2]/div/ul/li[2]/a"));
        Actions action = new Actions(driver);
        action.moveToElement(testingDropdown).perform();
        WebElement seleniumMenuPoint = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/table/tbody/tr/td[2]/a[2]"));
        seleniumMenuPoint.click();
        Thread.sleep(3000);
        //Two types of ad's, because of that 2 try catch blocks
        try {
            driver.switchTo().frame(driver.findElement(By.id("google_ads_iframe_/24132379/INTERSTITIAL_DemoGuru99_0")));
            driver.switchTo().frame(driver.findElement(By.id("ad_iframe")));
            WebElement dismissButton = driver.findElement(By.id("dismiss-button"));
            dismissButton.click();

        } catch (WebDriverException e) {

            e.printStackTrace();

        }

        //I don't find the element here
        try {
            driver.switchTo().frame(driver.findElement(By.id("google_ads_iframe_/24132379/INTERSTITIAL_DemoGuru99_0")));
            WebElement dismissButton = driver.findElement(By.id("dismiss-button"));
            dismissButton.click();

        } catch (WebDriverException e) {

            e.printStackTrace();

        }
        WebElement guru99ConsentButton = driver.findElement(By.xpath("//*[contains(text(),'Consent')]"));
        guru99ConsentButton.click();
        Thread.sleep(3000);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,5000)");
        Thread.sleep(1000);
        WebElement joinFreeCourseText = driver.findElement(By.xpath("//*[contains(text(),'Join our FREE Course')]"));
        assertEquals("Join our FREE Course", joinFreeCourseText.getText());
    }
}
