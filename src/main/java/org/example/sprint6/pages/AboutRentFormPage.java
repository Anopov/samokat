package org.example.sprint6.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class AboutRentFormPage {
    private WebDriver driver;

    @FindBy(className = "Order_Content__bmtHS")
    private WebElement RentalForm; // Локатор второй формы заказа "Про аренду"

    @FindBy(xpath = "//input[@placeholder='* Когда привезти самокат']")
    private WebElement whenBringField; // Локатор поля Когда привезти самокат

    @FindBy(className = "Dropdown-placeholder")
    private WebElement rentalPeriodField; // Локатор поля Срок аренды

    @FindBy(className = "Dropdown-down")
    private WebElement rentalSpisokPeriodField; // Локатор выпадающего списка со Сроком аренды

    //@FindBy(xpath = "//div[@text()='сутки']")
    @FindBy(className = "Dropdown-option")
    private WebElement rentalSpisokPeriod1Field; // Локатор выпадающего списка со Сроком аренды

    @FindBy(className = "Order_Title__3EKne")
    private WebElement scooterColorField; // Локатор поля выбора цвета самоката

    @FindBy(id = "black")
    private WebElement blacklCheckbox; // Локатор цвета чёрный жемчуг

    @FindBy(id = "grey")
    private WebElement greyCheckbox; // Локатор цвета серая безысходность

    @FindBy(xpath = "//input[@placeholder='Комментарий для курьера']")
    private WebElement commentCourierField; // Локатор поля Комментарий для курьера

    //    Button_Button__ra12g Button_Middle__1CSJM
    @FindBy(xpath = "/html/body/div/div/div[2]/div[3]/button[2]")
    private WebElement orderСontinuationField; // Локатор кнопки продолжения заказа

    @FindBy(xpath = "/html/body/div/div/div[2]/div[5]/div[2]/button[2]")
    private WebElement orderButton; // Локатор Кнопки завершения Заказа

    @FindBy(className = "Order_ModalHeader__3FDaJ")
    private WebElement trackСontent; // Локатор оформленного заказа с треком

    public AboutRentFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Метод проверки открытия  второй формы заказа "Для кого самокат"
    public boolean isRentalForm() {
        return RentalForm.isDisplayed();
    }
    // Метод для ввода значения даты "Когда привезти самокат" вручную
    public void setDeliveryDate(String dateString) {
        whenBringField.clear();
        whenBringField.sendKeys(dateString);
        whenBringField.sendKeys(Keys.ENTER);
    }

    // Метод выбора дня "Когда привезти самокат" из выпадающего календаря
    public void selectCalendarDay(int day) {
        String cssSelectorForDay = String.format(".react-datepicker__day--%s", day);
        WebElement calendarDay = driver.findElement(By.cssSelector(cssSelectorForDay));
        calendarDay.click();
    }

    // Метод выбора срока аренды
    public void rentalPeriod(String[] args) throws InterruptedException {
        rentalPeriodField.click();// Кликаем по кнопке, чтобы раскрыть менюшку со сроками аренды
        //rentalSpisokPeriodField.click();
        rentalSpisokPeriod1Field.click();
    }

    // Методы для работы с чекбоксами цвета самоката
    public void selectBlackColor() {
        blacklCheckbox.click();
    }
    public void selectGreyColor() {
        greyCheckbox.click();
    }

    // Методы проверки состояния чекбоксов
    public boolean isBlackChecked() {
        return blacklCheckbox.isSelected();
    }
    public boolean isGreyChecked() {
        return greyCheckbox.isSelected();
    }

    // Метод для ввода текста в поле комментария
    public void enterComment(String comment) {
        commentCourierField.clear();
        commentCourierField.sendKeys(comment);
    }
    // Метод этапа завершения оформления заказа
    public void orderСontinuationStep() {
        orderСontinuationField.click();
    }

    // Метод подтвержнения завершения оформления заказа
    public void placeOrder() throws InterruptedException {
        orderButton.click();
    }

    // Проверка всплывающего окна с сообщением об успешном создании заказа.
    public boolean isSuccessMessageDisplayed() {
        return trackСontent.isDisplayed(); // Возвращает true, если уведомление отображается
    }
}
