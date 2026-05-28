package org.example.scooter.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.example.scooter.test.Resources.*;

@RunWith(Parameterized.class)
public class FAQTest {

    private WebDriver driver;
    private HomePageScooter objHomePage;

    private final String questionText;
    private final String expectedAnswerText;

    public FAQTest(String questionText, String expectedAnswerText) {
        this.questionText = questionText;
        this.expectedAnswerText = expectedAnswerText;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"Сколько это стоит? И как оплатить?", ANSWER_1_TEXT},
                {"Хочу сразу несколько самокатов! Так можно?", ANSWER_2_TEXT},
                {"Как рассчитывается время аренды?", ANSWER_3_TEXT},
                {"Можно ли заказать самокат прямо на сегодня?", ANSWER_4_TEXT},
                {"Можно ли продлить заказ или вернуть самокат раньше?", ANSWER_5_TEXT},
                {"Вы привозите зарядку вместе с самокатом?", ANSWER_6_TEXT},
                {"Можно ли отменить заказ?", ANSWER_7_TEXT},
                {"Я жизу за МКАДом, привезёте?", ANSWER_8_TEXT}
        };
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        objHomePage = new HomePageScooter(driver);
        objHomePage.openHomePage();
        objHomePage.acceptCookieButtonClick();
    }

    @Test
    public void FAQCorrectAnswerText() {
        objHomePage.scrollToFAQ();
        objHomePage.clickQuestion(questionText);
        objHomePage.isCorrectText(objHomePage.getOpenedAnswerText(), expectedAnswerText);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}