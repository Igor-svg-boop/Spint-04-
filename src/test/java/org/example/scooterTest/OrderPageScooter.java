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

    private WebDriver driver;

    private By orderHeader = By.xpath(".//div[text()='Для кого самокат']");
    private By acceptCookieButton = By.xpath(".//button[text()='да все привыкли']");

    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private By subwayField = By.xpath(".//input[@placeholder='* Станция метро']");
    private By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By orderNextButton = By.xpath(".//button[text()='Далее']");

    private By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private By rentalPeriodField = By.className("Dropdown-placeholder");
    private By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    private By orderCreateButton = By.xpath("//div[contains(@class,'Order_Buttons')]/button[text()='Заказать']");
    private By orderConfirmButton = By.xpath(".//button[text()='Да']");

    private By confirmHeader = By.xpath("//div[contains(@class, 'Order_ModalHeader') and contains(., 'Заказ оформлен')]");

    public OrderPageScooter(WebDriver driver) {
        this.driver = driver;
    }

    public String getOrderHeader() {
        return driver.findElement(orderHeader).getText();
    }

    public String getConfirmHeader() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmHeader)).getText();
    }

    public void isPageOpen(String headerText, String text) {
        MatcherAssert.assertThat(headerText, containsString(text));
    }

    public void acceptCookieButtonClick() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(acceptCookieButton)).click();
    }

    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void setSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }

    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void setSubway(String subway) {
        driver.findElement(subwayField).click();
        driver.findElement(By.xpath(".//div[text()='" + subway + "']")).click();
    }

    public void setPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    public void clickOrderNextButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(orderNextButton)).click();
    }

    public void setDate(String date) {
        driver.findElement(dateField).sendKeys(date, Keys.ENTER);
    }

    public void setRentalPeriod(String rentalPeriod) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement rentalPeriodElement = wait.until(ExpectedConditions.visibilityOfElementLocated(rentalPeriodField));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                rentalPeriodElement
        );

        wait.until(ExpectedConditions.elementToBeClickable(rentalPeriodElement)).click();

        WebElement rentalPeriodOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, 'Dropdown-option') and text()='" + rentalPeriod + "']")
        ));

        rentalPeriodOption.click();
    }

    public void setColor(String color) {
        driver.findElement(By.xpath(".//label[text()='" + color + "']")).click();
    }

    public void setComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void clickOrderCreateButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(orderCreateButton)).click();
    }

    public void clickOrderConfirmButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(orderConfirmButton)).click();
    }
}