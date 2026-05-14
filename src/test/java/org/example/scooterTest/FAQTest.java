package org.example.scooterTest;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.example.scooterTest.Resources.*;

public class FAQTest {

    private WebDriver driver;

    @Test
    public void FAQCorrectAnswerText() {
        // Создать веб-драйвер для Google Chrome
        driver = new ChromeDriver();
        // Открыть страницу домашнюю Яндекс Самокат
        driver.get("https://qa-scooter.praktikum-services.ru");
        // Проскролить страницу до появления таблицы с вопросами
        WebElement tableFAQ = driver.findElement(By.xpath(".//div[@class='accordion']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", tableFAQ);
        // Создать объект класса с домашней страницей
        HomePageScooter objHomePage = new HomePageScooter(driver);

        // Проверка соответствия текста ответа с ожидаемым
        objHomePage.clickQuestion1();
        objHomePage.isCorrectText(objHomePage.getAnswer1(), answer1Text);

        objHomePage.clickQuestion2();
        objHomePage.isCorrectText(objHomePage.getAnswer2(), answer2Text);

        objHomePage.clickQuestion3();
        objHomePage.isCorrectText(objHomePage.getAnswer3(), answer3Text);

        objHomePage.clickQuestion4();
        objHomePage.isCorrectText(objHomePage.getAnswer4(), answer4Text);

        objHomePage.clickQuestion5();
        objHomePage.isCorrectText(objHomePage.getAnswer5(), answer5Text);

        objHomePage.clickQuestion6();
        objHomePage.isCorrectText(objHomePage.getAnswer6(), answer6Text);

        objHomePage.clickQuestion7();
        objHomePage.isCorrectText(objHomePage.getAnswer7(), answer7Text);

        objHomePage.clickQuestion8();
        objHomePage.isCorrectText(objHomePage.getAnswer8(), answer8Text);

    }

    @After
    public void teardown() {
        driver.quit();
    }

}