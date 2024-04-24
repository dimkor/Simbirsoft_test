package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.AccountPage;
import page.LoginPage;
import page.TransactionsPage;

import java.time.LocalDate;

public class BankingProjectTest extends BaseTest {

    String link = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login";

    @Test
    @DisplayName("Открытие страницы сайта")
    public void openSite() {
        LoginPage loginPage = new LoginPage(driver)
                .open(link);
        Assertions.assertEquals("XYZ Bank", driver.getTitle());
    }
    @Test
    @DisplayName("Авторизация")
    public void authorizationTest() {
        String accountOwner = "Harry Potter";
        AccountPage accountPage = new LoginPage(driver)
                .open(link)
                .loginAs(accountOwner);
        Assertions.assertEquals(accountOwner, accountPage.getOwnerName());
    }
    @Test
    @DisplayName("Пополнение депозита")
    public void addToDepositTest() throws InterruptedException {
        String accountOwner = "Harry Potter";
        int fibbonachiNum = fibbonachiCalc();

        AccountPage accountPage = new LoginPage(driver)
                .open(link)
                .loginAs(accountOwner)
                .addToDeposit(fibbonachiNum);

        Assertions.assertEquals("Deposit Successful", accountPage.getMessage());
    }
    @Test
    @DisplayName("Списание средств")
    public void withdrawlTest() throws InterruptedException {
        String accountOwner = "Harry Potter";
        int fibbonachiNum = fibbonachiCalc();

        AccountPage accountPage = new LoginPage(driver)
                .open(link)
                .loginAs(accountOwner)
                .addToDeposit(fibbonachiNum)
                .withdrawlFromDeposit(fibbonachiNum);

        Assertions.assertEquals("Transaction successful", accountPage.getMessage());
        Assertions.assertEquals("0", accountPage.getBalance());
    }

    @Test
    @DisplayName("Проверка информации о транзакциях")
    public void openTransactionsTest() throws InterruptedException {
        String accountOwner = "Harry Potter";
        int fibbonachiNum = fibbonachiCalc();

        TransactionsPage transactionsPage = new LoginPage(driver)
                .open(link)
                .loginAs(accountOwner)
                .addToDeposit(fibbonachiNum)
                .withdrawlFromDeposit(fibbonachiNum)
                .clickOnTransactionTabButton();
        Assertions.assertEquals(2, transactionsPage.getRowCount());
    }

    private int fibbonachiCalc() {
        int dayOfMonth = LocalDate.now().getDayOfMonth() + 1;

        int previous = 0, current = 1;
        for (int i = 2; i <= dayOfMonth; i++) {
            int temp = current;
            current = previous + current;
            previous = temp;
        }
        return current;
    }
}
