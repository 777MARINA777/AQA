import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.MtsMainPage;
import org.example.PaymentModalPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestMtsTests {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static MtsMainPage mainPage;

    @BeforeAll
    static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, (10));

        driver.get("https://www.mts.by/");
        mainPage = new MtsMainPage(driver, wait);

    }

    @Test
    @DisplayName("Закрыть окно cookie")
    @Order(1)
    public void cookieTest() {
        mainPage.cookieButtonTest();
    }

    @Test
    @DisplayName("Проверка названия блока")
    @Order(2)
    void testBlockTitle() {
        String title = mainPage.getBlockTitleText().replace("\n", " ");
        assertTrue(title.equalsIgnoreCase("Онлайн пополнение без комиссии"), "Неверный заголовок блока: " + title);
    }

    @Test
    @DisplayName("Проверка наличия логотипов платёжных систем")
    @Order(3)
    void testPaymentLogos() {
        List<WebElement> logos = mainPage.getPaymentLogos();
        assertEquals(6, logos.size(), "Должно быть 6 логотипов");
        for (WebElement logo : logos) {
            assertTrue(logo.isDisplayed(), "Не отображается логотип: " + logo.getAttribute("alt"));
        }
    }

    @Test
    @DisplayName("Проверка ссылки: Подробнее о сервисе")
    @Order(4)
    void testLink() {
        mainPage.clickServiceLink();
        String currentUrl = driver.getCurrentUrl();
        assertTrue(
                currentUrl.contains("/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/"),
                "Открылась неверная страница: " + currentUrl
        );
        driver.navigate().back();
        mainPage.getBlockTitleText();
    }

    @Test
    @DisplayName("Проверка плейсхолдеров для всех вариантов оплаты")
    @Order(5)
    void testPlaceholders() {
        //Услуги связи
        mainPage.selectService("Услуги связи");
        assertArrayEquals(
                new String[]{"Номер телефона", "Сумма", "E-mail для отправки чека"},
                mainPage.getPlaceholders(),
                "Неверные плейсхолдеры для блока «Услуги связи»"
        );

        //Домашний интернет
        mainPage.selectService("Домашний интернет");
        assertArrayEquals(
                new String[]{"Номер абонента", "Сумма", "E-mail для отправки чека"},
                mainPage.getInternetPlaceholders(),
                "Неверные плейсхолдеры для блока «Домашний интернет»"
        );

        // Рассроча
        mainPage.selectService("Рассрочка");
        assertArrayEquals(
                new String[]{"Номер счета на 44", "Сумма", "E-mail для отправки чека"},
                mainPage.getInstalmentPlaceholders(),
                "Неверные плейсхолдеры для блока «Рассрочка»"
        );

        //Задолженность
        mainPage.selectService("Задолженность");
        assertArrayEquals(
                new String[]{"Номер счета на 2073", "Сумма", "E-mail для отправки чека"},
                mainPage.getDebtPlaceholders(),
                "Неверные плейсхолдеры для блока «Задолженность»"
        );
    }

    @Test
    @DisplayName("Заполнение полей и проверка окна оплаты")
    @Order(6)
    void testPaymentForm() {
        String phone = "297777777";
        String sum = "5";
        String email = "marina_bratikova@mail.ru";

        PaymentModalPage modalPage = mainPage.fillAndSubmitConnectionForm(phone, sum, email);

        // Проверка суммы в шапке и на кнопке
        assertTrue(modalPage.getPaymentAmountText().contains("5.00 BYN"),
                "Неверная сумма в заголовке: " + modalPage.getPaymentAmountText());
        assertTrue(modalPage.getPayButtonText().contains("5.00 BYN"),
                "Неверная сумма на кнопке: " + modalPage.getPayButtonText());

        // Проверка номера телефона
        assertTrue(modalPage.getPaymentDescriptionText().contains("375" + phone),
                "Неверный номер телефона: " + modalPage.getPaymentDescriptionText());

        // Проверка надписей в полях карты
        assertEquals("Номер карты", modalPage.getNumberCardText());
        assertEquals("Срок действия", modalPage.getCardValidityPeriodText());
        assertEquals("CVC", modalPage.getCvcCardText());
        assertEquals("Имя и фамилия на карте", modalPage.getFirstnameLastnameCardText());

        // Проверка наличия иконок платёжных систем
        assertTrue(modalPage.iconsPaymentSystems(), "Иконки платёжных систем не отображаются");

        modalPage.closeModalAndReturnToMain();
    }

    @AfterAll
    static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

