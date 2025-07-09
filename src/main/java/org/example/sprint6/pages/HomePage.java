package org.example.sprint6.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

// Класс, стартовую страницу
public class HomePage {

    private WebDriver driver;

    // Локаторы для соответствующих элементов
    @FindBy(className="Home_SubHeader__zwi_E")
    private WebElement fAq;// Локатор Вопроcов о важном

    @FindBy(className="accordion")
    private WebElement allQuestions;// Локатор блока со всеми вопросами

    @FindBy(id="accordion__heading-0")
    private WebElement firstQuestion;// Локатор кнопки раскрытия первого вопроса

    @FindBy(id="accordion__panel-0")
    private WebElement firstQuestionText;// Локатор ответа на первый вопрос

    @FindBy(id="accordion__heading-1")
    private WebElement secondQuestion;// Локатор кнопки раскрытия второго вопроса

    @FindBy(id="accordion__panel-1")
    private WebElement secondQuestionText;// Локатор ответа на второй вопрос

    @FindBy(className = "Button_Button__ra12g")
    private WebElement orderButtonTop; // Локатор верхней кнопки заказа

    //@FindBy(className = "Button_Button__ra12g Button_UltraBig__UU3Lp")
    @FindBy(xpath = "/html/body/div/div/div[1]/div[4]/div[2]/div[5]/button")
    private WebElement orderButtonBottom2; // Локатор нижней кнопки заказа

    // Конструктор принимает драйвер
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Инциализация локаторов
    }

    // метод ожидания загрузки страницы
    public void waitForLoadHeader(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Home_SubHeader__zwi_E")));
    }
    // Открытие стартовой страницы
    public void openPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        waitForLoadHeader();
    }

    // Прокручиваем страницу вниз до раздела FAQ
    public void scrollToFAQSection() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);",
                allQuestions
        );
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(allQuestions));
    }

    // Раскрыть содержимое первого вопроса
    public void firstDropDown() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(firstQuestion));
        firstQuestion.click();
    }

    // Проверить видимость раскрывшегося содержимого первого вопроса
    public boolean firstIsDropDownVisible() {
        return firstQuestionText.isDisplayed();
    }

    // Раскрыть содержимое второго вопроса
    public void secondDropDown() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(secondQuestion));
        secondQuestion.click();
    }

    // Проверить видимость раскрывшегося содержимого второго вопроса
    public boolean secondIsDropDownVisible() {
        return secondQuestionText.isDisplayed();
    }
    // Метод клика по верхней кнопке заказа
    public void clickOrderButtonTop() {
        orderButtonTop.click();
    }

    // Метод клика по нижней кнопке заказа
    public void clickOrderButtonBottom() throws InterruptedException {
        orderButtonBottom2.sendKeys(Keys.ENTER);
    }

}
