package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TransactionsPage extends BasePage {

    private final By headersInTable = By.xpath("//table/thead/tr/td");
    private final By rowsInTable = By.xpath("//table/tbody/tr");
    private final By backButton = By.xpath("//button[text()='Back']");
    private final By transactionsButton = By.xpath("//button[@ng-click='transactions()']");

    public TransactionsPage(WebDriver driver) {
        super(driver);
    }

    public int getRowCount() {
        int count = driver.findElements(rowsInTable).size();
        if (count == 0) {
            driver.findElement(backButton).click();
            driver.findElement(transactionsButton).click();
        }
        parseTableAndWriteToCSV();
        return driver.findElements(rowsInTable).size();
    }


    public void parseTableAndWriteToCSV() {
        try {
            FileWriter csvWriter = new FileWriter("src/test/resources/table.csv");
            List<WebElement> rows = driver.findElements(rowsInTable);
            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                for (WebElement cell : cells) {
                    String text = cell.getText();
                    text = formatDateTime(text);
                    csvWriter.append(text);
                    csvWriter.append(";");
                }
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String formatDateTime(String text) {
        try {
            DateTimeFormatter inputFormatter = new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern("MMM dd, yyyy K:mm:ss a")
                    .toFormatter(Locale.ENGLISH);
            DateTimeFormatter outputFormatter = new DateTimeFormatterBuilder()
                    .appendPattern("dd MMMM yyyy HH:mm:ss")
                    .toFormatter(new Locale("ru", "RU"));
            LocalDateTime dateTime = LocalDateTime.parse(text, inputFormatter);
            return capitalizeFirstLetters(dateTime.format(outputFormatter));
        } catch (Exception e) {
            return text;
        }
    }

    private String capitalizeFirstLetters(String str) {
        StringBuilder sb = new StringBuilder();
        String[] words = str.split(" ");
        for (String word : words) {
            sb.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1)).append(" ");
        }
        return sb.toString().trim();
    }
}
