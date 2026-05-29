package org.example.scooter.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.example.scooter.test.Resources.CONFIRM_HEADER;

@RunWith(Parameterized.class)
public class OrderTest {

    private WebDriver driver;
    private OrderPageScooter orderPageScooter;

    private final String name;
    private final String surname;
    private final String address;
    private final String subway;
    private final String phoneNumber;
    private final String rentalPeriod;
    private final String color;
    private final String comment;

    public OrderTest(String name, String surname, String address, String subway,
                     String phoneNumber, String rentalPeriod, String color, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.subway = subway;
        this.phoneNumber = phoneNumber;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"Иван", "Иванов", "Москва, улица Ленина, дом 1", "Черкизовская", "+79999999999", "сутки", "чёрный жемчуг", "Позвонить за час"},
                {"Пётр", "Петров", "Москва, улица Пушкина, дом 2", "Сокольники", "+79888888888", "двое суток", "серая безысходность", "Оставить у подъезда"}
        };
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        orderPageScooter = new OrderPageScooter(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/order");
        acceptCookieIfVisible();
    }

    @Test
    public void orderCanBeCreatedWithValidData() {
        String date = LocalDate.now()
                .plusDays(1)
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        orderPageScooter.setName(name);
        orderPageScooter.setSurname(surname);
        orderPageScooter.setAddress(address);
        orderPageScooter.setSubway(subway);
        orderPageScooter.setPhoneNumber(phoneNumber);
        orderPageScooter.clickOrderNextButton();

        orderPageScooter.setDate(date);
        orderPageScooter.setRentalPeriod(rentalPeriod);
        orderPageScooter.setColor(color);
        orderPageScooter.setComment(comment);
        orderPageScooter.clickOrderCreateButton();
        orderPageScooter.clickOrderConfirmButton();

        orderPageScooter.isPageOpen(CONFIRM_HEADER);
    }

    private void acceptCookieIfVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath(".//button[text()='да все привыкли']")
            )).click();
        } catch (TimeoutException ignored) {
        }
    }

    @After
    public void teardown() {
        driver.quit();
    }
}