package org.example.scooter.test;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.containsString;

public class OrderPageScooter {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By orderHeader = By.xpath(".//div[text()='Для кого самокат']");
    private final By acceptCookieButton = By.xpath(".//button[text()='да все привыкли']");

    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By subwayField = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By orderNextButton = By.xpath(".//button[text()='Далее']");

    private final By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodField = By.className("Dropdown-placeholder");
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    private final By orderCreateButton = By.xpath("//div[contains(@class,'Order_Buttons')]/button[text()='Заказать']");
    private final By orderConfirmButton = By.xpath(".//button[text()='Да']");

    private final By confirmHeader = By.xpath("//div[contains(@class, 'Order_ModalHeader') and contains(., 'Заказ оформлен')]");

    public OrderPageScooter(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public String getOrderHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderHeader)).getText();
    }

    public String getConfirmHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmHeader)).getText();
    }

    public void isPageOpen(String text) {
        wait.until(ExpectedConditions.urlContains("/order"));
        MatcherAssert.assertThat(getOrderHeader(), containsString(text));
    }

    public void acceptCookieButtonClick() {
        wait.until(ExpectedConditions.elementToBeClickable(acceptCookieButton)).click();
    }

    public void setName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
    }

    public void setSurname(String surname) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(surnameField)).sendKeys(surname);
    }

    public void setAddress(String address) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addressField)).sendKeys(address);
    }

    public void setSubway(String subway) {
        wait.until(ExpectedConditions.elementToBeClickable(subwayField)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//div[text()='" + subway + "']"))).click();
    }

    public void setPhoneNumber(String phoneNumber) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumberField)).sendKeys(phoneNumber);
    }

    public void clickOrderNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(orderNextButton)).click();
    }

    public void setDate(String date) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(dateField)).sendKeys(date, Keys.ENTER);
    }

    public void setRentalPeriod(String rentalPeriod) {
        WebElement rentalPeriodElement = wait.until(ExpectedConditions.visibilityOfElementLocated(rentalPeriodField));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                rentalPeriodElement
        );

        wait.until(ExpectedConditions.elementToBeClickable(rentalPeriodElement)).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, 'Dropdown-option') and text()='" + rentalPeriod + "']")
        )).click();
    }

    public void setColor(String color) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//label[text()='" + color + "']"))).click();
    }

    public void setComment(String comment) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(commentField)).sendKeys(comment);
    }

    public void clickOrderCreateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(orderCreateButton)).click();
    }

    public void clickOrderConfirmButton() {
        wait.until(ExpectedConditions.elementToBeClickable(orderConfirmButton)).click();
    }
}