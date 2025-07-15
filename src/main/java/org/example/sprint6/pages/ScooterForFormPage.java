package org.example.sprint6.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ScooterForFormPage {
    private WebDriver driver;

    @FindBy(className = "Order_Content__bmtHS")
    private WebElement OrderForm; // Локатор первой формы заказа "Для кого самокат"

    @FindBy(xpath = "//input[@placeholder='* Имя']")
    private WebElement nameField; // Локатор поля Имя

    @FindBy(xpath = "//input[@placeholder='* Фамилия']")
    private WebElement surnameField; // Локатор поля Фамилия

    @FindBy(xpath = "//input[@placeholder='* Адрес: куда привезти заказ']")
    private WebElement addressField; // Локатор поля Адрес

    @FindBy(className = "select-search__input")
    private WebElement metroField; // Локатор поля Станция метро (ввод)

    @FindBy(className = "select-search__value")
    private WebElement metroSelectField; // Локатор поля выбора Станция метро

    @FindBy(xpath = "//input[@placeholder='* Телефон: на него позвонит курьер']")
    private WebElement phoneField;          // Локатор поля Телефон

    //@FindBy(xpath = "//button[.='Далее']")
    @FindBy(css = ".Button_Button__ra12g.Button_Middle__1CSJM")
    private WebElement furtherButton; // Локатор кнопки Далее

    public ScooterForFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Метод проверки открытия  первой формы заказа "Для кого самокат"
    public boolean isOrderForm() {
        return OrderForm.isDisplayed();
    }

    // Метод заполнения формы заказа (Имя, Фамилия, Адрес, Телефон)
    public void fillOrderForm(String name, String surname, String address, String phoneNumber) {
        nameField.sendKeys(name);
        surnameField.sendKeys(surname);
        addressField.sendKeys(address);
        phoneField.sendKeys(phoneNumber);
        phoneField.sendKeys(Keys.TAB);
        phoneField.sendKeys(Keys.ENTER);
    }

    // Метод выбора станции Метро
    public void selectMetroStation(String metroStation) {

        metroField.click(); // Кликаем по полю Метро, чтобы активировать автозаполнение
        metroField.sendKeys(Keys.ARROW_DOWN);
        metroField.sendKeys(Keys.ENTER);
    }

    // Метод нажатия на кнопку Далее
    public void furtherButtonClick() {
         furtherButton.click();
    }

}
