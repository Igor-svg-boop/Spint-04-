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
    public static Object[][] getQuestionAndAnswerText() {
        return new Object[][]{
                {"Сколько это стоит? И как оплатить?", RENT_PRICE_ANSWER},
                {"Хочу сразу несколько самокатов! Так можно?", SEVERAL_SCOOTERS_ORDER_ANSWER},
                {"Как рассчитывается время аренды?", RENT_START_TIME_ANSWER},
                {"Можно ли заказать самокат прямо на сегодня?", ORDER_DATE_LIMIT_ANSWER},
                {"Можно ли продлить заказ или вернуть самокат раньше?", ORDER_EXTENSION_ANSWER},
                {"Вы привозите зарядку вместе с самокатом?", SCOOTER_CHARGE_ANSWER},
                {"Можно ли отменить заказ?", ORDER_CANCELLATION_ANSWER},
                {"Я жизу за МКАДом, привезёте?", DELIVERY_AREA_ANSWER}
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