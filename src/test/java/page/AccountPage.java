package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountPage extends BasePage {

    private final By transactionsButton = By.xpath("//button[@ng-click='transactions()']");
    private final By depositTabButton = By.xpath("//button[@ng-click='deposit()']");
    private final By withdrawlTabButton = By.xpath("//button[@ng-click='withdrawl()']");
    private final By amountField = By.xpath("//input[@ng-model='amount']");
    private final By amountField2 = By.cssSelector(".form-control");
    private final By addToDepositButton = By.xpath("//button[text()='Deposit']");
    private final By addToWithdrawButton = By.xpath("//button[text()='Withdraw']");
    private final By ownerNameSpan = By.xpath("//span[contains(@class,'fontBig')]");
    //    private final By successfulDepositOperationMessage = By.xpath("//span[text()='Deposit Successful']");
    private final By successfulMessage = By.xpath("//span[@ng-show='message']");


    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @Step("Переключиться на таб \"Deposit\"")
    private AccountPage clickOnDepositTabButton() {
        wait.until(ExpectedConditions.presenceOfElementLocated(depositTabButton));
        driver.findElement(depositTabButton).click();
        return this;
    }

    @Step("Установить значение {value} в поле \"amount\"")
    private AccountPage setValueInAmountDepositField(int value) {
        WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(amountField));
        inputField.sendKeys(String.valueOf(value));
        return this;
    }

    @Step("Клик на кнопку \"Deposit\" для добавления суммы в депозит")
    private AccountPage clickOnAddToDepositButton() {
        WebElement addToDeposit = wait.until(ExpectedConditions.presenceOfElementLocated(addToDepositButton));
        addToDeposit.click();
        return this;
    }

    @Step("Внести сумму, равную {value} на депозит")
    public AccountPage addToDeposit(int value) {
        clickOnDepositTabButton();
        setValueInAmountDepositField(value);
        clickOnAddToDepositButton();
        return this;
    }

    @Step("Установить значение {value} в поле \"amount\"")
    private AccountPage setValueInAmountWithdrawField(int value) throws InterruptedException {
        WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(amountField));
        Thread.sleep(1000);
        inputField.click();
        inputField.sendKeys(String.valueOf(value));
        return this;
    }


    @Step("Переключиться на таб \"Withdrawl\"")
    private AccountPage clickOnWithdrawlTabButton() {
        driver.findElement(withdrawlTabButton).click();
        return this;
    }

    @Step("Клик на кнопку \"Withdrawl\" для списания суммы с депозита")
    private AccountPage clickOnWithdrawlButton() {
        driver.findElement(addToWithdrawButton).click();
        return this;
    }

    @Step("Списать сумму, равную {value} с депозита")
    public AccountPage withdrawlFromDeposit(int value) throws InterruptedException {
//        driver.navigate().refresh();
        clickOnWithdrawlTabButton();
        setValueInAmountWithdrawField(value);
        clickOnWithdrawlButton();
        return this;
    }

    public String getOwnerName() {
        return driver.findElement(ownerNameSpan).getText();
    }

    public String getMessage() {
        return driver.findElement(successfulMessage).getText();
    }


}
