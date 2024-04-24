package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TransactionsPage extends BasePage{

    private final By headersInTable = By.xpath("//table/thead");
    private final By rowsInTable = By.xpath("//table/tbody/tr");
    private final By backButton = By.xpath("//button[text()='Back']");
    private final By transactionsButton = By.xpath("//button[@ng-click='transactions()']");

    public TransactionsPage(WebDriver driver) {
        super(driver);
    }

    public int getRowCount() throws InterruptedException {
        int count = driver.findElements(rowsInTable).size();
        if (count == 0) {
            driver.findElement(backButton).click();
            driver.findElement(transactionsButton).click();
        }
        return driver.findElements(rowsInTable).size();
    }

    private void makeSomeone() {
        List<WebElement> headers = driver.findElements(headersInTable);
        System.out.print("hz");
    }

}
