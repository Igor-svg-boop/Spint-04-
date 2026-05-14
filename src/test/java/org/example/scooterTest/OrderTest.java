package org.example.scooterTest;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.example.scooterTest.Resources.confirmHeader;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String subway;
    private final String phoneNumber;
    private final String date;
    private final String rentalPeriod;
    private final String color;
    private final String comment;

    public OrderTest(String name, String surname, String address, String subway, String phoneNumber, String date, String rentalPeriod, String color, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.subway = subway;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getDateSetForOrder() {
        return new Object[][] {
                {"Иван", "Иванов", "г. Москва, ул. Пушкина, д.10", "Театральная", "89151234567", "01.01.2050", "сутки", "чёрный жемчуг", "Не звонить в дверь"},
                {"Ирина", "Авдеева", "проспект Маяковского 6", "Маяковская", "+79657654321", "10.10.2030", "двое суток", "серая безысходность", "Привезите чистый самокат"},
        };
    }

    @Test
    public void OrderPositiveTest() {
        // Создать веб-драйвер для Firefox
        driver = new FirefoxDriver();
        // Открыть страницу заказа Яндекс Самокат
        driver.get("https://qa-scooter.praktikum-services.ru");
        // Создать объект класса с домашней страницей
        HomePageScooter objHomePage = new HomePageScooter(driver);
        // Нажать на кнопку Заказать на чердаке
        objHomePage.clickHeaderOrderButton();
        // Создать объект класса со страницей заказа
        OrderPageScooter objOrderPage = new OrderPageScooter(driver);
        // Принять куки
        objOrderPage.acceptCookieButtonClick();
        // Позитивный сценарий оформления заказа
        objOrderPage.setName(name);
        objOrderPage.setSurname(surname);
        objOrderPage.setAddress(address);
        objOrderPage.setSubway(subway);
        objOrderPage.setPhoneNumber(phoneNumber);
        objOrderPage.clickOrderNextButton();
        objOrderPage.setDate(date);
        objOrderPage.setRentalPeriod(rentalPeriod);
        objOrderPage.setColor(color);
        objOrderPage.setComment(comment);
        objOrderPage.clickOrderCreateButton();
        objOrderPage.clickOrderConfirmButton();
        // Проверить, что открылась страница успешного создания заказа
        objOrderPage.isPageOpen(objOrderPage.getConfirmHeader() ,confirmHeader);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}