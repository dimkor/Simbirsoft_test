package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends BasePage{

    private final By transactionsButton = By.xpath("//button[@ng-click='transactions()']");
    private final By depositeButton = By.xpath("//button[@ng-click='deposit()']");
    private final By withdrawlButton = By.xpath("//button[@ng-click='withdrawl()']");
    private final By amountField = By.xpath("//input[@ng-model='amount']");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public AccountPage clickOnDepositButton() {
        driver.findElement(depositeButton).click();
        return this;
    }


    public AccountPage addToAmountField(int amount) {
//        driver.findElement(depositeButton).click();
//        driver.findElement(depositeButton).sendKeys(String.valueOf(amount));
//        return this;
    }

}
