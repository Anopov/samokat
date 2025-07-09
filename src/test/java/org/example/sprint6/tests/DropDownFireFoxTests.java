package org.example.sprint6.tests;


import org.example.sprint6.pages.HomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import io.github.bonigarcia.wdm.WebDriverManager;

public class DropDownFireFoxTests {

    private WebDriver driver;
    private HomePage mainPage;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.firefoxdriver().setup(); // Автоматически устанавливает нужный драйвер
    }

    @BeforeEach
    public void setup() {
        driver = new FirefoxDriver();
    }

    @Test
    public void testFaqDropdownOpensCorrectly() {
        mainPage = new HomePage(driver);
        mainPage.openPage(); // Открываем стартовую страницу
        mainPage.scrollToFAQSection();  // Прокручиваемся вниз до блока FAQ

        mainPage.firstDropDown(); // Кликаем на кнопку раскрытия первого вопроса
        Assertions.assertTrue(mainPage.firstIsDropDownVisible()); // Проверяем, открылся ли ответ на первый вопрос

        mainPage.secondDropDown(); // Кликаем на кнопку раскрытия второго вопроса
        Assertions.assertTrue(mainPage.secondIsDropDownVisible()); // Проверяем, открылся ли ответ на второй вопрос

    }

    // Завершаем работу с браузером
    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
