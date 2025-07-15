package org.example.sprint6.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

// Класс, стартовую страницу
public class HomePage {

    private WebDriver driver;

    // Локаторы для соответствующих элементов
    @FindBy(className = "Home_SubHeader__zwi_E")
    private WebElement fAq;// Локатор Вопроcов о важном

    @FindBy(className = "accordion")
    private WebElement allQuestions;// Локатор блока со всеми вопросами

    // Общий локатор для всех заголовков вопросов
    @FindBy(css = ".accordion > div.accordion__item > div.accordion__heading") // Все кнопки вопросов
    private List<WebElement> allQuestionHeaders;

    // Общий локатор для всех панелей ответов
    @FindBy(css = ".accordion > div.accordion__item > div.accordion__panel") // Все панели ответов
    private List<WebElement> allQuestionPanels;

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
    public void waitForLoadHeader() {
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


    //Метод раскрытия вопроса по указанному индексу
    public void faqDropDown(int index) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(allQuestionHeaders.get(index)));
        allQuestionHeaders.get(index).click();
    }


    //Возвращает текст открытого вопроса по указанному индексу
    public String faqDropDownText(int index) {
        return allQuestionPanels.get(index).getText();
    }

    public boolean isFaqDropDownTextVisible(int index) {
        return allQuestionPanels.get(index).isDisplayed();
    }

    public void clickOrderButtonTop() {
        orderButtonTop.click();
    }

    // Метод клика по нижней кнопке заказа
    public void clickOrderButtonBottom() throws InterruptedException {
        orderButtonBottom2.sendKeys(Keys.ENTER);
    }

}
