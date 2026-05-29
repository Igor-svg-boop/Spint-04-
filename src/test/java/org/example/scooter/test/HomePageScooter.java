package org.example.scooter.test;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.is;

public class HomePageScooter {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Кнопка принятия cookies
    private final By acceptCookieButton = By.xpath(".//button[text()='да все привыкли']");

    // Таблица с вопросами FAQ
    private final By tableFAQ = By.xpath(".//div[@class='accordion']");

    // Маленькая кнопка Заказать в шапке
    private final By headerOrderButton = By.xpath("(.//button[text()='Заказать'])[1]");

    // Большая кнопка Заказать на странице
    private final By pageOrderButton = By.xpath(".//div[contains(@class, 'Home_FinishButton')]/button");

    public HomePageScooter(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    // Метод для открытия главной страницы
    public void openHomePage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    // Метод для принятия cookies
    public void acceptCookieButtonClick() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(acceptCookieButton)).click();
        } catch (TimeoutException | NoSuchElementException ignored) {
        }
    }

    // Метод для скролла до блока FAQ
    public void scrollToFAQ() {
        WebElement faqElement = wait.until(ExpectedConditions.presenceOfElementLocated(tableFAQ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", faqElement);
    }

    // Метод для раскрытия вопроса по тексту
    public void clickQuestion(String questionText) {
        driver.findElement(By.xpath(".//div[text()='" + questionText + "']")).click();
    }

    // Метод для получения текста открытого ответа
    public String getOpenedAnswerText() {
        return driver.findElement(By.xpath(".//div[contains(@id, 'accordion__panel') and not(@hidden)]")).getText();
    }

    // Метод для сравнения ответа на вопрос с правильным текстом
    public void isCorrectText(String answer, String text) {
        MatcherAssert.assertThat(answer, is(text));
    }

    // Метод для клика по маленькой кнопке Заказать в шапке
    public void clickHeaderOrderButton() {
        wait.until(ExpectedConditions.elementToBeClickable(headerOrderButton)).click();
    }

    // Метод для клика по большой кнопке Заказать на главной странице
    public void clickPageOrderButton() {
        WebElement bigButton = wait.until(ExpectedConditions.presenceOfElementLocated(pageOrderButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                bigButton
        );

        wait.until(ExpectedConditions.elementToBeClickable(bigButton)).click();
    }
}