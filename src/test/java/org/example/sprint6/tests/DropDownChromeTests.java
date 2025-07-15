package org.example.sprint6.tests;

import org.example.sprint6.pages.HomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DropDownChromeTests {

    private WebDriver driver;
    private HomePage mainPage;
    private WebDriverWait wait;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup(); // Автоматически устанавливает нужный драйвер

    }

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize(); // Максимизируем окно браузера
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Создаем единый таймаут
    }

    public static Object[][] getTest() {
        return new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @ParameterizedTest
    @MethodSource("getTest")
    public void testFaqDropdownOpensCorrectly(int answer, String expectedText) {
        mainPage = new HomePage(driver);
        mainPage.openPage(); // Открываем стартовую страницу
        mainPage.scrollToFAQSection();  // Прокручиваемся вниз до блока FAQ
        mainPage.faqDropDown(answer); // Кликаем на кнопку раскрытия вопроса
        // Обязательно проверяем видимость
        Assertions.assertTrue(mainPage.isFaqDropDownTextVisible(answer), "Текст ответа не виден");
        // Проводим основное утверждение о содержании текста
        Assertions.assertEquals(expectedText, mainPage.faqDropDownText(answer), "Текст ответа не совпал");


    }

    // Завершаем работу с браузером
    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
