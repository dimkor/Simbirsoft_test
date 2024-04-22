package tests;

import org.junit.jupiter.api.Test;
import page.AccountPage;
import page.MainPage;

import java.time.LocalDate;

public class BankingProjectTest extends BaseTest {


    @Test
    public void firstTest() throws InterruptedException {

        AccountPage accountPage = new MainPage(driver)
                .open("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login")
                .clickByLoginAsCustomer()
                .selectOption("Harry Potter")
                .clickLoginButton()
                .clickOnDepositButton()
                .addToAmountField(fibbonachiN());

        Thread.sleep(10000);
    }

    private int fibbonachiN() {
        int dayOfMonth = LocalDate.now().getDayOfMonth() + 1;

        if (dayOfMonth <= 1)
            return dayOfMonth;
        int previous = 0, current = 1;
        for (int i = 2; i <= dayOfMonth; i++) {
            int temp = current;
            current = previous + current;
            previous = temp;
        }
        return current;
    }
}
