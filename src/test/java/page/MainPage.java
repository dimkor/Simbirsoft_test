package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import tests.BaseTest;

public class MainPage extends BasePage {

    private final By loginAsCustomerButton = By.xpath("//button[@ng-click='customer()']");
    private final By yourNameSelectField = By.id("userSelect");
    private final By loginButton = By.xpath("//button[text()='Login']");


    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage open(String url) {
        driver.get(url);
        return this;
    }

    public MainPage clickByLoginAsCustomer() {
        driver.findElement(loginAsCustomerButton).click();
        return this;
    }

    public MainPage selectOption(String value) {
        WebElement selectElement = driver.findElement(yourNameSelectField);
        Select select = new Select(selectElement);
        select.selectByVisibleText(value);
        return this;
    }

    public AccountPage clickLoginButton() {
        driver.findElement(loginButton).click();
        return new AccountPage(driver);
    }


}
