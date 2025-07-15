package org.example.sprint6.tests;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.sprint6.pages.AboutRentFormPage;
import org.example.sprint6.pages.HomePage;
import org.example.sprint6.pages.ScooterForFormPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderScooterFireFoxTests {

    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;
    private ScooterForFormPage scooterPage;
    private AboutRentFormPage aboutRentPage;


    @BeforeAll
    public static void setupClass() {
        WebDriverManager.firefoxdriver().setup(); //  устанавливаем нужный драйвер
    }

    @BeforeEach
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize(); // Максимизируем окно браузера
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Создаем единый таймаут
    }

    // Тестовые данные для заказа самоката
    @ParameterizedTest
    @CsvSource({
            "Александр, Анопов, Волгоград ул.Вокзальная д.1, +79951161602, clickOrderButtonTop()",
            "Анна, Петрова, СПб Невский проспект дом 91, +79123456789"
    })

    // проверяем работу верхней кнопки заказа
    public void testSubmitOrderTopScooter(String name, String surname, String address, String phoneNumber) throws InterruptedException
    {
        homePage = new HomePage(driver);
        homePage.openPage(); // Открываем стартовую страницу
        homePage.clickOrderButtonTop();// кликаем по верхней кнопке заказа

        scooterPage = new ScooterForFormPage(driver);
        // Проверяем, появилась ли первая форма заказа
        assertTrue(scooterPage.isOrderForm(), "Первая форма заказа не отображается");

        scooterPage.fillOrderForm(name, surname, address, phoneNumber); // Заполним данные о покупатере на первой форме заказа
        scooterPage.selectMetroStation("Комсомольская"); // Выбираем станцию метро
        scooterPage.furtherButtonClick(); // кликаем на кнопку Далее


        aboutRentPage = new AboutRentFormPage(driver);
        // Проверяем, появилась ли вторая форма заказа
        assertTrue(aboutRentPage.isRentalForm(), "Вторая форма заказа не отображается");

        aboutRentPage.selectBlackColor(); // Кликаем на чек-бокс черного
        assertTrue(aboutRentPage.isBlackChecked(), "Черный цвет не отмечен"); // Проверим что черный чек-бокс отжат

        aboutRentPage.setDeliveryDate("2025-06-10"); // Вручную заполняем дату доставки
        aboutRentPage.rentalPeriod(new String[]{"два дня"});
        aboutRentPage.enterComment("Жду заказ"); // Заполняем комментарий

        aboutRentPage.orderСontinuationStep(); // Завершаем оформление заказа
        aboutRentPage.placeOrder(); // Подтверждаем оформление заказа

        assertTrue(aboutRentPage.isSuccessMessageDisplayed(), "Сообщение об успешной отправке заказа не появилось.");
    }

    // Тестовые данные для заказа самоката
    @ParameterizedTest
    @CsvSource({
            "Анна, Котова, Москва ул.Москавская д.188, +79876543000",
            "Иван, Грозный, СПб Брянская улица дом 991, +79123456777"
    })
    // проверяем работу нижней кнопки заказа
    public void testSubmitOrderBottomScooter(String name, String surname, String address, String phoneNumber) throws InterruptedException {

        homePage = new HomePage(driver);
        homePage.openPage(); // Открываем стартовую страницу
        homePage.clickOrderButtonBottom();// кликаем по нижней кнопке заказа

        scooterPage = new ScooterForFormPage(driver);
        // Проверяем, появилась ли первая форма заказа
        assertTrue(scooterPage.isOrderForm(), "Первая форма заказа не отображается");

        scooterPage.fillOrderForm(name, surname, address, phoneNumber); // Заполним данные о покупатере на первой форме заказа
        scooterPage.selectMetroStation("Комсомольская"); // Выбираем станцию метро
        scooterPage.furtherButtonClick(); // кликаем на кнопку Далее


        aboutRentPage = new AboutRentFormPage(driver);
        // Проверяем, появилась ли вторая форма заказа
        assertTrue(aboutRentPage.isRentalForm(), "Вторая форма заказа не отображается");

        aboutRentPage.selectGreyColor(); // Кликаем на чек-бокс серого
        assertTrue(aboutRentPage.isGreyChecked(), "Серы йцвет не отмечен"); // Проверим что черный чек-бокс отжат

        aboutRentPage.setDeliveryDate("2025-06-10"); // Вручную заполняем дату доставки
        aboutRentPage.rentalPeriod(new String[]{"два дня"}); // Заполняем срок аренды


        aboutRentPage.enterComment("Охота покататься"); // Заполняем комментарий

        aboutRentPage.orderСontinuationStep(); // Завершаем оформление заказа
        aboutRentPage.placeOrder(); // Подтверждаем оформление заказа

        assertTrue(aboutRentPage.isSuccessMessageDisplayed(), "Сообщение об успешной отправке заказа не появилось.");
    }

    // Завершаем работу с браузером
    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
