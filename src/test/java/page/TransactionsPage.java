package page;

import io.qameta.allure.Attachment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
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

//    @Attachment(value = "table", type = "text/csv", fileExtension = ".csv")
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

            attachCsvFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Attachment(value = "table", type = "text/csv")
    public byte[] attachCsvFile() throws IOException {
        return Files.readAllBytes(Paths.get("src/test/resources/table.csv"));
    }

    private String formatDateTime(String text) {
        try {
            DateTimeFormatter inputFormatter = new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern("MMM dd, yyyy h:mm:ss a")
                    .toFormatter(Locale.ENGLISH);
            DateTimeFormatter outputFormatter = new DateTimeFormatterBuilder()
                    .appendPattern("dd MMMM yyyy HH:mm:ss")
                    .toFormatter(Locale.ENGLISH);
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
