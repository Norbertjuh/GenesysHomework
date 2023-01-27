import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver=driver;
    }

    private By usernameBy = By.name("user-name");
    private By passwordBy = By.name("password");
    private By loginBy = By.name("login-button");

    public HomePage loginValidUser(String userName, String password) {
        driver.findElement(usernameBy).sendKeys(userName);
        driver.findElement(passwordBy).sendKeys(password);
        driver.findElement(loginBy).click();
        return new HomePage(driver);
    }

    public HomePage loginByJsonCredentials() throws IOException {
        driver.findElement(usernameBy).sendKeys(parseUsername());
        driver.findElement(passwordBy).sendKeys(parsePassword(12));
        driver.findElement(loginBy).click();
        return new HomePage(driver);
    }

    public LoginPage clickLoginButton() {
        driver.findElement(loginBy).click();
        return new LoginPage(driver);
    }

    public String parseUsername() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("credential.json");
        is.read();
        String result = IOUtils.toString(is, StandardCharsets.UTF_8);
        String[] lines = result.split("\\n");
        String username = lines[1].substring(12, lines[1].indexOf(","));
        return username;
    }

    public String parsePassword(int passwordLength) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("credential.json");
        is.read();
        String result = IOUtils.toString(is, StandardCharsets.UTF_8);

        String[] lines = result.split("\\n");
        String password = lines[2].substring(12, 12+passwordLength);
        return password;
    }
}
