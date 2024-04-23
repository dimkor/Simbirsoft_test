package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.AccountPage;
import page.MainPage;

import java.time.LocalDate;

public class BankingProjectTest extends BaseTest {

    String link = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login";

    @Test
    @DisplayName("Открытие страницы сайта")
    public void openSite() {
        MainPage mainPage = new MainPage(driver)
                .open(link);
        Assertions.assertEquals("XYZ Bank", driver.getTitle());
    }
    @Test
    @DisplayName("Авторизация")
    public void authorizationTest() {
        String accountOwner = "Harry Potter";
        AccountPage accountPage = new MainPage(driver)
                .open(link)
                .loginAs(accountOwner);
        Assertions.assertEquals(accountOwner, accountPage.getOwnerName());
    }
    @Test
    @DisplayName("Пополнение депозита")
    public void addToDepositTest() throws InterruptedException {
        String accountOwner = "Harry Potter";
        int fibbonachiNum = fibbonachiCalc();

        AccountPage accountPage = new MainPage(driver)
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

        AccountPage accountPage = new MainPage(driver)
                .open(link)
                .loginAs(accountOwner)
                .addToDeposit(fibbonachiNum)
                .withdrawlFromDeposit(fibbonachiNum);

        Thread.sleep(5000);
        Assertions.assertEquals("Transaction successful", accountPage.getMessage());
    }

    private int fibbonachiCalc() {
        int dayOfMonth = LocalDate.now().getDayOfMonth() + 1;

//        if (dayOfMonth <= 1)
//            return dayOfMonth;
        int previous = 0, current = 1;
        for (int i = 2; i <= dayOfMonth; i++) {
            int temp = current;
            current = previous + current;
            previous = temp;
        }
        return current;
    }
}
